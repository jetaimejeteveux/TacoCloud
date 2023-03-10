package tacos;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table //persist the taco tables
public class Taco {

    @PrimaryKeyColumn(type= PrimaryKeyType.PARTITIONED) //define partition key
    private UUID id = Uuids.timeBased();

    @PrimaryKeyColumn(type= PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING) //define clustered key
    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")

    private String name;



    @Size(min=1, message="You must choose at least 1 ingredient")
    @Column("ingredients")
    private List<IngredientUDT> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
    }

}
