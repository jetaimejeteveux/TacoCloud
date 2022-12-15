package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//the controller
@Controller
public class HomeController {

    //handle request for the root path -> /
    @GetMapping("/")
    public String home(){
        return "home"; //returns view name
    }
}
