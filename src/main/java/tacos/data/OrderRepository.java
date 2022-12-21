package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Taco;
import tacos.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
