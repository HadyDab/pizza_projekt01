package whz.pti.eva.pizza_projekt.security.boundary;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControler {

    @RequestMapping("/home")
    public String getHomePage() {
        return "home";
    }

}
