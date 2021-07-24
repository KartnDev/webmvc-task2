package org.example.utils;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public class JdbcUtils {
    public static <T> Optional<T> queryForOptional(JdbcTemplate template, String queryString, RowMapper<T> rowMapper, Object... args) {
        return Optional.ofNullable(DataAccessUtils.singleResult(template.query(
                queryString, rowMapper, args
        )));
    }
}
