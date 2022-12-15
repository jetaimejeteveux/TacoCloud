package tacos.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.Ingredient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import tacos.TacoOrder;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j //simple logging facade for Java, slf4J, logger static property in the class
@Controller // to identify this class as a controller and to mark it as a candidate for component scanning
//so that Spring will discover it and automatically create an instance of DesignTacoController as a bean in the
//Spring application context

@RequestMapping("/design") //when applied at the class level, specifies the kind of request that this controller handle
//request mapping will handle request whose path begins with /design

@SessionAttributes("tacoOrder") //Model tacoOrder (di line 53) harus tetap di session
public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "CORN TORTILLA", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jck", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = Ingredient.Type.values();
        for (Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping //when an HTTP GET request is received for /design, spring mvc will call showDesignForm() to handle the request.
    public String showDesignForm() {
        return "design"; //return view name

    }

    @PostMapping//when an HTTP POST request is received for /design, spring mvc will call showDesignForm() to handle the request
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){

        if(errors.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}