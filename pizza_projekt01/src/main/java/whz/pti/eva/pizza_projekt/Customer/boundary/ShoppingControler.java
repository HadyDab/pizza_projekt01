package whz.pti.eva.pizza_projekt.Customer.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.domain.Item;
import whz.pti.eva.pizza_projekt.Customer.domain.Pizza;
import whz.pti.eva.pizza_projekt.Customer.service.CustomerService;
import whz.pti.eva.pizza_projekt.Customer.service.ItemService;
import whz.pti.eva.pizza_projekt.Customer.service.PizzaService;
import whz.pti.eva.pizza_projekt.Customer.service.ShoppingCartService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingControler {


    private PizzaService pizzaService;
    private CustomerService customerService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingControler(PizzaService pizzaService,CustomerService customerService,ShoppingCartService shoppingCartService) {
        this.pizzaService = pizzaService;
        this.customerService =customerService;
        this.shoppingCartService = shoppingCartService;
    }







    @RequestMapping("/allpizza")
    public String showAllPizza(Model model){
        Customer currentUser = customerService.getCustomerByLoginName("smithD");
        model.addAttribute("listOfPizzas",pizzaService.getAllPizzas());
        model.addAttribute("currentUser",currentUser);
        return "allpizza";
    }



    @RequestMapping(value = "/addtocart", method = RequestMethod.POST)
    public String additemtocart(@RequestParam("id") int id,
                                @RequestParam String quantity, @RequestParam String loginName,Model model){


        int quantity2 = Integer.parseInt(quantity);
        Pizza pizza = pizzaService.find(id);

        shoppingCartService.addItemToCart(loginName,pizza,quantity2);
        Customer currentUser = customerService.getCustomerByLoginName(loginName);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("listOfPizzas",pizzaService.getAllPizzas());
        model.addAttribute("quantity",quantity);
        //model.addAttribute("customer",customerService.getCustomerByLoginName("smithD"));

        return "allpizza";
    }





    @RequestMapping(value = "/showCart", method = RequestMethod.POST)
    public String showcart(@RequestParam String loginName, Model model){
        List<Item> itemsInCart = shoppingCartService.getItemsinShoppingCart(loginName);
        double total = 0.0;
        for(Item i :itemsInCart){
            total += i.getPizza().getPrice() * i.getQuantity();
        }
        Customer currentUser = customerService.getCustomerByLoginName(loginName);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("itemsincart",itemsInCart);
        model.addAttribute("total",total);
        //modelMap.put("listOfPizzas",pizzaService.getAllPizzas());
        return "cart";
    }



    @RequestMapping(value = "updateQuantity", method = RequestMethod.POST)
    public String updateQuantity(@RequestParam int id ,@RequestParam String quantity, @RequestParam String loginName,Model model){

        int quantity2 = Integer.parseInt(quantity);
        shoppingCartService.updateQuantity(loginName,id,quantity2);
        List<Item> itemsInCart = shoppingCartService.getItemsinShoppingCart(loginName);
        double total = 0.0;
        for(Item i :itemsInCart){
            total += i.getPizza().getPrice() * i.getQuantity();
        }
        Customer currentUser = customerService.getCustomerByLoginName(loginName);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("itemsincart",itemsInCart);
        model.addAttribute("total",total);

        return "cart";
    }




    @RequestMapping(value = "removeItem", method = RequestMethod.POST)
    public String updateQuantity(@RequestParam int id , @RequestParam String loginName,Model model){

        shoppingCartService.removethisItem(loginName,id);

        List<Item> itemsInCart = shoppingCartService.getItemsinShoppingCart(loginName);

        double total = 0.0;
        for(Item i :itemsInCart){
            total += i.getPizza().getPrice() * i.getQuantity();
        }
        Customer currentUser = customerService.getCustomerByLoginName(loginName);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("itemsincart",itemsInCart);
        model.addAttribute("total",total);

        return "cart";
    }



    @RequestMapping(value = "/Offers",method = RequestMethod.POST)
    public String showOffers(@RequestParam String loginName, Model model){
        Customer currentUser = customerService.getCustomerByLoginName(loginName);
        model.addAttribute("listOfPizzas",pizzaService.getAllPizzas());
        model.addAttribute("currentUser",currentUser);

        return "allpizza";
    }




    @RequestMapping(value = "/buynow",method = RequestMethod.POST)
    public String orderComfirm(@RequestParam String loginName, Model model){

        Customer currentUser = customerService.getCustomerByLoginName(loginName);
        model.addAttribute("currentUser",currentUser);

        return "odercomfirm";
    }





}
