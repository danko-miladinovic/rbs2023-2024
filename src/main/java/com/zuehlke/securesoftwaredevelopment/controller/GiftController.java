package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.config.AuditLogger;
import com.zuehlke.securesoftwaredevelopment.domain.*;
import com.zuehlke.securesoftwaredevelopment.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class GiftController {

    private static final Logger LOG = LoggerFactory.getLogger(GiftController.class);
    private static final AuditLogger auditLogger = AuditLogger.getAuditLogger(GiftController.class);

    private GiftRepository giftRepository;
    private CommentRepository commentRepository;
    private RatingRepository ratingRepository;
    private PersonRepository userRepository;

    private TagRepository tagRepository;

    public GiftController(GiftRepository giftRepository, CommentRepository commentRepository, RatingRepository ratingRepository, PersonRepository userRepository, TagRepository tagRepository) {
        this.giftRepository = giftRepository;
        this.commentRepository = commentRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/")
    public String showSearch(Model model) {
        model.addAttribute("gifts", giftRepository.getAll());
        return "gifts";
    }

    @GetMapping("/create-form")
    public String CreateForm(Model model) {
        model.addAttribute("tags", tagRepository.getAll());
        return "create-form";
    }

    @GetMapping(value = "/api/gifts/search", produces = "application/json")
    @ResponseBody
    public List<Gift> search(@RequestParam("query") String query) throws SQLException {
        return giftRepository.search(query);
    }

    @GetMapping("/gifts")
    public String showGift(@RequestParam(name = "id", required = false) String id, Model model, Authentication authentication) {
        if (id == null) {
            model.addAttribute("gifts", giftRepository.getAll());
            return "gifts";
        }

        User user = (User) authentication.getPrincipal();
        List<Tag> tagList = this.tagRepository.getAll();

        model.addAttribute("gift", giftRepository.get(Integer.parseInt(id), tagList));
        List<Comment> comments = commentRepository.getAll(id);
        List<Rating> ratings = ratingRepository.getAll(id);
        Optional<Rating> userRating = ratings.stream().filter(rating -> rating.getUserId() == user.getId()).findFirst();
        if (userRating.isPresent()) {
            model.addAttribute("userRating", userRating.get().getRating());
        }
        if (ratings.size() > 0) {
            Integer sumRating = ratings.stream().map(rating -> rating.getRating()).reduce(0, (total, rating) -> total + rating);
            Double avgRating = (double)sumRating/ratings.size();
            model.addAttribute("averageRating", avgRating);
        }

        List<ViewComment> commentList = new ArrayList<>();

        for (Comment comment : comments) {
            Person person = userRepository.get("" + comment.getUserId());
            commentList.add(new ViewComment(person.getFirstName() + " " + person.getLastName(), comment.getComment()));
        }

        model.addAttribute("comments", commentList);

        return "gift";
    }

    @PostMapping("/gifts")
    public String createGift(NewGift newGift) throws SQLException {
        List<Tag> tagList = this.tagRepository.getAll();
        List<Tag> tagsToInsert = newGift.getTags().stream().map(tagId -> tagList.stream().filter(tag -> tag.getId() == tagId).findFirst().get()).collect(Collectors.toList());
        Long id = giftRepository.create(newGift, tagsToInsert);
        return "redirect:/gifts?id=" + id;
    }

    @GetMapping("/buy-gift/{id}")
    public String showBuyCar(
            @PathVariable("id") int id,
            @RequestParam(required = false) boolean addressError,
            @RequestParam(required = false) boolean bought,
            Model model) {

        model.addAttribute("id", id);

        if (addressError) {
            model.addAttribute("addressError", true);
        } else if (bought) {
            model.addAttribute("bought", true);
        }

        return "buy-gift";
    }

    @PostMapping("/buy-gift/{id}")
    public String buyCar(@PathVariable("id") int id, @RequestParam(name = "count", required = true) int count, Address address, Model model) {
        if (address.getAddress().length() < 10) {
            return String.format("redirect:/buy-gift/%s?addressError=true", id);
        }

        if (count <= 0) {
            return String.format("redirect:/buy-gift/%s", id);
        }

        return String.format("redirect:/buy-gift/%s?bought=true", id);
    }
}
