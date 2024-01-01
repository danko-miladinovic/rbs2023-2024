package com.zuehlke.securesoftwaredevelopment.repository;

import com.zuehlke.securesoftwaredevelopment.domain.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TagRepository {

    private static final Logger LOG = LoggerFactory.getLogger(TagRepository.class);


    private DataSource dataSource;

    public TagRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Tag> getAll() {
        List<Tag> tagList = new ArrayList<>();
        String query = "SELECT id, name FROM tags";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                tagList.add(new Tag(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagList;
    }
}
