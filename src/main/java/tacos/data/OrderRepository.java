package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Taco;
import tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    TacoOrder save(TacoOrder order);
}
