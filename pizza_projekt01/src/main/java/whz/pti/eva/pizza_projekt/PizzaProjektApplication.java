package whz.pti.eva.pizza_projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import whz.pti.eva.pizza_projekt.Customer.service.CustomerService;
import whz.pti.eva.pizza_projekt.Customer.service.ItemService;
import whz.pti.eva.pizza_projekt.Customer.service.PizzaService;
import whz.pti.eva.pizza_projekt.Customer.service.ShoppingCartService;

@SpringBootApplication
public class PizzaProjektApplication {



    @Autowired
    CustomerService customerService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ItemService itemService;

    @Autowired
    PizzaService pizzaService;

    public static void main(String[] args) {
        SpringApplication.run(PizzaProjektApplication.class, args);
    }


    @Bean
    CommandLineRunner init() {
        return (evt) -> {

            customerService.addCustomer("Adam", "Smith", "smithD", "123abc");

            customerService.addCustomerAdress("smithD", "Oxfordstreet", "5", "London", "DT345W");
            customerService.addCustomerAdress("smithD","Queen Hellen ave","246","London","W569D8");

            pizzaService.createPizza("Pizza Magarita","Pizza with double Cheese",2.00);
            pizzaService.createPizza("Pizza Tonno","Thunfish with Onions and Mushroom",3.00);

            shoppingCartService.createShoppingcart("smithD");

           // itemService.createItem("PizzaMagarita",2.50);
            //itemService.createItem("Pizzathurnfish",3.50);


           // shoppingCartService.addItemToCart("smithD","PizzaMagarita",2);
           // shoppingCartService.addItemToCart("smithD","Pizzathurnfish",2);



            System.out.println("Hello Welt");

        }


                ;
    }


}






