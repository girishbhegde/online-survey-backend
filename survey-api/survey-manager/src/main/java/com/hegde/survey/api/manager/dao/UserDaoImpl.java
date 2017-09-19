package com.hegde.survey.api.manager.dao;

import com.hegde.survey.api.manager.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

/**
 * Created by girish hegde on 9/16/17.
 */
@Service
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insertNewUser(User user){
        long start = System.currentTimeMillis();
        String sql = "INSERT INTO users (username, name, email, created_on) VALUES (?, ?, ?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                user.getUsername(), user.getName(), user.getEmail(), new Timestamp(System.currentTimeMillis())});
        LOGGER.info("processed insert user in {} ms for user {}", start, user.getUsername());
    }

    @Override
    public User getUserDetails(String username) {
        long start = System.currentTimeMillis();
        String sql = "SELECT username, name, email, created_on FROM users WHERE username=(?)";
        User user = getJdbcTemplate().queryForObject(sql, new Object[]{username}, (resultSet, i) -> {
            User user1 = new User();
            user1.setUsername(resultSet.getString("username"));
            user1.setName(resultSet.getString("name"));
            user1.setEmail(resultSet.getString("email"));
            user1.setCreateOn(resultSet.getString("created_on"));
            return user1;
        });
        LOGGER.info("processed insert user in {} ms for user {}", start, user.getUsername());
        return user;
    }
}
