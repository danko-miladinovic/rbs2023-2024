package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.domain.Comment;
import com.zuehlke.securesoftwaredevelopment.domain.Rating;
import com.zuehlke.securesoftwaredevelopment.domain.User;
import com.zuehlke.securesoftwaredevelopment.repository.CommentRepository;
import com.zuehlke.securesoftwaredevelopment.repository.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RatingsController {
    private static final Logger LOG = LoggerFactory.getLogger(RatingsController.class);

    private RatingRepository ratingRepository;

    public RatingsController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @PostMapping(value = "/ratings", consumes = "application/json")
    public String createOrUpdateRating(@RequestBody Rating rating, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        rating.setUserId(user.getId());
        ratingRepository.createOrUpdate(rating);

        return "redirect:/gifts?id=" + rating.getGiftId();
    }
}
