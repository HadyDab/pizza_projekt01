package whz.pti.eva.pizza_projekt.Customer.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.domain.Item;
import whz.pti.eva.pizza_projekt.Customer.domain.Pizza;
import whz.pti.eva.pizza_projekt.Customer.domain.ShoppingCart;
import whz.pti.eva.pizza_projekt.Customer.service.CustomerService;
import whz.pti.eva.pizza_projekt.Customer.service.ItemService;
import whz.pti.eva.pizza_projekt.Customer.service.PizzaService;
import whz.pti.eva.pizza_projekt.Customer.service.ShoppingCartService;
import whz.pti.eva.pizza_projekt.security.domain.CurrentUser;
import whz.pti.eva.pizza_projekt.security.domain.PizzaCreateForm;
import whz.pti.eva.pizza_projekt.security.domain.UserCreateForm;
import whz.pti.eva.pizza_projekt.security.service.validator.PizzaCreateFormValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller

public class ShoppingControler {


    private PizzaService pizzaService;
    private CustomerService customerService;
    private ShoppingCartService shoppingCartService;
    private PizzaCreateFormValidator pizzaCreateFormValidator;

    @Autowired
    public ShoppingControler(PizzaService pizzaService,CustomerService customerService,PizzaCreateFormValidator pizzaCreateFormValidator,ShoppingCartService shoppingCartService) {
        this.pizzaService = pizzaService;
        this.customerService =customerService;
        this.shoppingCartService = shoppingCartService;
        this.pizzaCreateFormValidator = pizzaCreateFormValidator;
    }


    @InitBinder("myform")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(pizzaCreateFormValidator);
    }

    @RequestMapping(value = "/allpizza")
    public String showAllPizza(Model model, @RequestParam String loginName){
        Customer currentUser = customerService.getCustomerByLoginName(loginName);
        model.addAttribute("listOfPizzas",pizzaService.getAllPizzas());
        model.addAttribute("currentUser",currentUser);
        return "offers";
    }

    @RequestMapping(value = "/addtocart", method = RequestMethod.POST)
    public String additemtocart(@RequestParam("id") int id,
                                @RequestParam String quantity, @RequestParam String loginName,Model model){
        int quantity2;
        Pizza pizza = pizzaService.find(id);
        String succefull ="";

        Customer currentUser = customerService.getCustomerByLoginName(loginName);
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("listOfPizzas",pizzaService.getAllPizzas());
        model.addAttribute("quantity",quantity);

        try {
            quantity2 = Integer.parseInt(quantity);
        } catch (NumberFormatException nr){
            model.addAttribute("error",nr.getMessage());
            return "offers";
        }

        if(quantity2 <= 0){
            succefull = " Wrong Quantity! Please add a valid quantity";
            model.addAttribute("added",succefull);
            return "offers";
        } else {
            shoppingCartService.addItemToCart(loginName,pizza,quantity2);
            succefull = pizza.getName() + " has been added to the shoppingcart sucessfully" ;
            model.addAttribute("added",succefull);
            return "offers";
        }

    }


    @RequestMapping(value = "/showCart", method = RequestMethod.GET)
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


    @RequestMapping(value = "/buynow",method = RequestMethod.POST)
    public String orderComfirm(@RequestParam String loginName, Model model){
        Customer currentUser = customerService.getCustomerByLoginName(loginName);
        model.addAttribute("currentUser",currentUser);
        return "odercomfirm";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/pizza/managed",method = {RequestMethod.POST,RequestMethod.GET})
    public String handlecreatePizza(Model model){
        model.addAttribute("myform", new PizzaCreateForm() );
        model.addAttribute("listOfPizza", pizzaService.getAllPizzas());
        return "pizza_create";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/pizza/create", method = RequestMethod.GET)
    public String getPizzaForm(@Valid @ModelAttribute("myform") PizzaCreateForm form, BindingResult bindingResult, Model model){
        model.addAttribute("listOfPizza", pizzaService.getAllPizzas());
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            return "pizza_create";
        }

        try {
            pizzaService.creatPizza(form);
        } catch (NumberFormatException nr){

        }
        return "redirect:/pizza/managed";
    }



}
