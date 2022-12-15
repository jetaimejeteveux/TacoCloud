package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.TacoOrder;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/orders") //to Handle Request from /orders path
@SessionAttributes("tacoOrder") //models to be maintained in session
public class OrderController {
    @GetMapping("/current") //to Handle GET request from /orders/current path
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus){

        if(errors.hasErrors()){
            return "orderForm";
        }

        log.info("Order submitted: {}", tacoOrder);
        sessionStatus.setComplete(); //to make sure the session is cleared and set up for the next taco order
        return "redirect:/";
    }


}
