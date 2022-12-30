package tacos;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;



import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("orders") //maps to the orders table
public class TacoOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey //declare primary key
    private UUID id = Uuids.timeBased();
    private Date placedAt;

    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message="street is required")
    private String deliveryStreet;
    @NotBlank(message="city is required")
    private String deliveryCity;
    @NotBlank(message="state is required")
    private String deliveryState;
    @NotBlank(message="zip is required")
    private String deliveryZip;

    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2]) ([\\/]) ([2-9] [0-9])$", message = "must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @Column("tacos")
    private List<TacoUDT> tacos = new ArrayList<>();
    public void addTaco (TacoUDT taco){
        this.tacos.add(taco);
    }
}
