package tacos.data;

import org.springframework.jdbc.core.JdbcTemplate;
import tacos.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbcTemplate;
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Iterable<Ingredient> findAll() { //return a collection of objects
        return jdbcTemplate.query(
                "select id, name, type from Ingredient",
                this::mapRowToIngredient); //row mapper for mapping each row in the result set to an object
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query( //this method will compare the value of the id column will the value of the id parameter passed
                "select id, name, type from Ingredient where id =?",
                this::mapRowToIngredient, id); //when the query is performed, the ? will be replaced with the last parameter which is the id
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));

    }
    //the same method but the mapRowToIngredient not written with method reference
     /*
  @Override
  public Ingredient findById(String id) {
    return jdbcTemplate.queryForObject(
        "select id, name, type from Ingredient where id=?",
        new RowMapper<Ingredient>() {
          public Ingredient mapRow(ResultSet rs, int rowNum)
              throws SQLException {
            return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
          };
        }, id);
  }
   */

    //row insertion
    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum)  throws SQLException {

            return new Ingredient(
                    row.getString("id"),
                    row.getString("name"),
                    Ingredient.Type.valueOf(row.getString("type"))
            );

    }
}



