package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;


import java.util.Optional;

@Repository // Spring will inject it with Jdbc-Template. When there's only 1 constructorm spring applies autowiring of
// dependencies through that constructor's parameter
public interface IngredientRepository  {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);

}


