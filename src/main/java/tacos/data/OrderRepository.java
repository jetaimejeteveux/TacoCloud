package tacos.data;



import org.springframework.data.repository.CrudRepository;
import tacos.Taco;
import tacos.TacoOrder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {
    //with spring data, don't have to implement, just inject  to the controller

}
