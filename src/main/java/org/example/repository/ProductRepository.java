package org.example.repository;

import org.example.domain.Product;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
  private final JdbcTemplate template;
  private final RowMapper<Product> rowMapper = (rs, i) -> new Product(
      rs.getLong("id"),
      rs.getString("name"),
      rs.getInt("price")
  );

  public ProductRepository(DataSource dataSource) {
    template = new JdbcTemplate(dataSource);
  }

  public List<Product> getAll() {
    // language=PostgreSQL
    return template.query(
        "SELECT id, name, price FROM products",
        rowMapper
    );
  }

  public Optional<Product> getById(long id) {
    // language=PostgreSQL
    return queryForOptional(
        "SELECT id, name, price FROM products WHERE id = ?",
        rowMapper, id
    );
  }

  public Optional<Product> save(Product product) {
    if (product.getId() == 0) {
      // language=PostgreSQL
      return queryForOptional(
          "INSERT INTO products(name, price) VALUES(?, ?) RETURNING id, name, price",
          rowMapper, product.getName(), product.getPrice()
      );
    }

    // language=PostgreSQL
    return queryForOptional(
        "UPDATE products SET name = ?, price = ? WHERE id = ? RETURNING id, name, price",
        rowMapper, product.getName(), product.getPrice(), product.getId()
    );
  }

  public boolean removeById(long id) {
    // language=PostgreSQL
    return template.update(
        "DELETE FROM products WHERE id = ?",
        id
    ) != 0;
  }

  private <T> Optional<T> queryForOptional(String sql, RowMapper<T> rowMapper, Object ...args) {
    return Optional.ofNullable(DataAccessUtils.singleResult(template.query(
        sql, rowMapper, args
    )));
  }
}
