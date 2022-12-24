package tacos;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table //it can work w/o annotation bcs it will be mapped automatically to table with name = Ingredient_Ref.
public class IngredientRef {
    private final String ingredient;
}
