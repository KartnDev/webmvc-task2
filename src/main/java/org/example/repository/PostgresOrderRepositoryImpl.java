package org.example.repository;

import org.example.domain.Order;
import org.example.utils.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresOrderRepositoryImpl implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Order> rowMapper;

    public PostgresOrderRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        rowMapper = (rs, i) -> Order.builder()
                .id(rs.getString("id"))
                .userId(rs.getString("userId"))
                .orderNumber(rs.getString("orderNumber"))
                .amount(rs.getInt("amount"))
                .currency(rs.getInt("currency"))
                .returnUrl(rs.getString("returnUrl"))
                .failUrl(rs.getString("failUrl"))
                .status(rs.getString("status"))
                .isDeleted(rs.getBoolean("isDeleted"))
                .deletedAt(rs.getDate("deletedAt"))
                .build();
    }

    @Override
    public Optional<Order> getById(String id) {
        //language=PostgreSQL
        final String queryString =
                "SELECT id, userId, orderNumber, amount, currency, returnUrl, failUrl, status, isDeleted, deletedAt " +
                        "FROM orders " +
                        "WHERE id = ?";

        return JdbcUtils.queryForOptional(jdbcTemplate, queryString, rowMapper, id);
    }

    @Override
    public Order save(Order newOrUpdate) {
        return newOrUpdate.getId() == null ? create(newOrUpdate) : update(newOrUpdate);
    }

    private Order update(Order order) {
        //language=PostgreSQL
        final String queryString =
                "UPDATE orders SET " +
                        "id = ?, " +
                        "userId = ?, " +
                        "orderNumber = ?, " +
                        "amount = ?, " +
                        "currency = ?, " +
                        "returnUrl = ?, " +
                        "failUrl = ?, " +
                        "status = ?, " +
                        "isDeleted = ?, " +
                        "deletedAt = ?" +
                        "WHERE id = ?" +
                        "RETURNING id, userId, orderNumber, amount, currency, returnUrl, failUrl, status, isDeleted, deletedAt";

        final String errorMessage = "SQLUpdate should returns updated version of order, no results are given";
        return JdbcUtils.queryForOptional(jdbcTemplate, queryString, rowMapper,
                order.getId(),
                order.getUserId(),
                order.getOrderNumber(),
                order.getAmount(),
                order.getCurrency(),
                order.getReturnUrl(),
                order.getFailUrl(),
                order.getStatus(),
                order.getIsDeleted(),
                order.getDeletedAt(),
                order.getId())
                .orElseThrow(() -> new IllegalStateException(errorMessage));
    }

    private Order create(Order order) {
        //language=PostgreSQL
        final String queryString =
                "INSERT INTO orders(id, userId, orderNumber, amount, currency, returnUrl, failUrl, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                        "RETURNING id, userId, orderNumber, amount, currency, returnUrl, failUrl, status, isDeleted, deletedAt";

        final String errorMessage = "SQLInsert should returns new inserted order, no results are given";
        return JdbcUtils.queryForOptional(jdbcTemplate, queryString, rowMapper,
                newId(),
                order.getUserId(),
                order.getOrderNumber(),
                order.getAmount(),
                order.getCurrency(),
                order.getReturnUrl(),
                order.getFailUrl(),
                order.getStatus())
                .orElseThrow(() -> new IllegalStateException(errorMessage));
    }

    private String newId() {
        return UUID.randomUUID().toString();
    }
}
