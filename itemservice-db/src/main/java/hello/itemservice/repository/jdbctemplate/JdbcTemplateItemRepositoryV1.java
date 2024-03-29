package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC Template 사용해서 구현
 */
@Slf4j
public class JdbcTemplateItemRepositoryV1 implements ItemRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcTemplateItemRepositoryV1(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public Item save(Item item) {
    String sql = "insert into item(item_name, price, quantity) values (?,?,?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(con -> {
      // 자동 증가 키
      PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
      ps.setString(1, item.getItemName());
      ps.setInt(2, item.getPrice());
      ps.setInt(3, item.getQuantity());
      return ps;
    }, keyHolder);

    //DB에 data를 넣고 나서 설정된 ID 값을 가져와서 Item 객체에 set
    long key = keyHolder.getKey().longValue();
    item.setId(key);

    return item;
  }

  @Override
  public void update(Long itemId, ItemUpdateDto updateParam) {
    String sql = "update item set item_name=?, price=?, quantity=? where id=?";
    jdbcTemplate.update(sql,
            updateParam.getItemName(),
            updateParam.getPrice(),
            updateParam.getPrice(), itemId);
  }

  @Override
  public Optional<Item> findById(Long id) {
    String sql = "select id, item_name, price, quantity from item where id=?";
    try {
      Item item = jdbcTemplate.queryForObject(sql, itemRowMapper(), id);
      return Optional.of(item);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  private RowMapper<Item> itemRowMapper() {
    return ((rs, rowNum) -> {
      Item item = new Item();
      item.setId(rs.getLong("id"));
      item.setItemName(rs.getString("item_name"));
      item.setPrice(rs.getInt("price"));
      item.setQuantity(rs.getInt("quantity"));
      return item;
    });
  }

  @Override
  public List<Item> findAll(ItemSearchCond cond) {
    String itemName = cond.getItemName();
    Integer maxPrice = cond.getMaxPrice();

    String sql = "select id, item_name, price, quantity from item";

    /**
     * 사용자가 검색하는 값에 따라서 실행하는 SQL이 동적으로 변경되어야함
     */
    if (StringUtils.hasText(itemName) || maxPrice != null) {
      sql += " where";
    }
    boolean andFlag = false;
    List<Object> param = new ArrayList<>();
    if (StringUtils.hasText(itemName)) {
      sql += " item_name like concat('%',?,'%')";
      param.add(itemName);
      andFlag = true;
    }
    if (maxPrice != null) {
      if (andFlag) {
        sql += " and";
      }
      sql += " price <= ?";
      param.add(maxPrice);
    }
    log.info("sql = {}", sql);
    return jdbcTemplate.query(sql, itemRowMapper());
  }
}
