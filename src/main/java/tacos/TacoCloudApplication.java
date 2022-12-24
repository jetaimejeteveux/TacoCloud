package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tacos.data.IngredientRepository;
import tacos.Ingredient.Type;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) { //a way to preload data
		//when the application starts up, any beans in the application context will have their run() method invoked after
		// the application context and all beans are wired up, but it happens before anything else happens
		// this will be a convenient place for data to be loaded into the database
		return args -> {
			repo.deleteAll(); // Quick hack to avoid tests from stepping on each other with constraint violations
			repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
		};
	}

}

// another way to preload data
//@Bean
//public ApplicationRunner dataLoader(IngredientRepository repo) {
//	return args -> {
//		repo.deleteAll(); // Quick hack to avoid tests from stepping on each other with constraint violations
//		repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
//		repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
//		repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
//		repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
//		repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
//		repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
//		repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
//		repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
//		repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
//		repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//	};
//}


