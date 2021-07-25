package org.example.repository;

import org.example.domain.User;
import org.example.utils.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class PostgresUserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper;

    public PostgresUserRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        rowMapper = (rs, i) -> User.builder()
                .id(rs.getString("id"))
                .userName(rs.getString("userName"))
                .password(rs.getString("password"))
                .build();
    }

    @Override
    public Optional<User> getByName(String userName) {
        //language=PostgreSQL
        final String queryString =
                "SELECT id, userName, password " +
                        "FROM users " +
                        "WHERE userName = ?";

        return JdbcUtils.queryForOptional(jdbcTemplate, queryString, rowMapper, userName);
    }
}
