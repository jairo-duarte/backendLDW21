package br.pucrio.ldw.aloLdw21.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


/**
 *  Esta classe usa o padrão DAO para acessar o banco de dados. Juntamente com JdbcTemplate, uma forma antiga e menos automatizada de acessar o banco de dados.
 */
@Service
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT id,name,username,email  FROM user",new UserMapper());
    }
    public List<Post> getPostsFromUser(Long userId) {
        return jdbcTemplate.query("SELECT id,user_id,title,body FROM post WHERE user_id = ?",new PostMapper(), userId);
    }

    public User getUserById(Long userId) {
        return jdbcTemplate.queryForObject("SELECT id,name,username,email FROM user WHERE id = ?",new UserMapper(), userId);
    }

    public User getUserByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT id,name,username,email FROM user WHERE username = ?",new UserMapper(), username);
    }

    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE user SET name = ?, username = ?, email = ? WHERE id = ?", user.getName(), user.getUsername(), user.getEmail(), user.getId());
    }

    public User updateUserOldFashion(User user) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("UPDATE user SET name = ?, username = ?, email = ? WHERE id = ?", new String[]{"id"});
                ps.setString(1, user.getName());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getEmail());
                ps.setLong(4, user.getId());
                return ps;
            }
        });
        return user;
    }

    public void deleteUser(Long userId) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", userId);
    }

    public User createUser(User user) {
        final String sql = "INSERT INTO user (name,username,email) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, user.getName());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getEmail());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    class UserMapper implements org.springframework.jdbc.core.RowMapper<User> {
        public User mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));

            return user;
        }
    }

    class PostMapper implements org.springframework.jdbc.core.RowMapper<Post> {
        public Post mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post(rs.getLong("id"),rs.getString("title"),rs.getString("body"));
            return post;
        }
    }
}
