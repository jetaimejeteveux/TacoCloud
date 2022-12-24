package tacos;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table //with table annotation, all other properties will be mapped automatically to column based on their property names.
public class TacoOrder {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
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

    private List<Taco> tacos = new ArrayList<>();
    public void addTaco (Taco taco){
        this.tacos.add(taco);
    }
}
