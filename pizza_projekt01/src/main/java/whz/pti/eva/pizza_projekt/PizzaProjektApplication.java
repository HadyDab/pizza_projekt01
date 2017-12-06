package whz.pti.eva.pizza_projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import whz.pti.eva.pizza_projekt.Customer.service.CustomerService;
import whz.pti.eva.pizza_projekt.Customer.service.ShoppingCartService;

@SpringBootApplication
public class PizzaProjektApplication {



    @Autowired
    CustomerService customerService;

    @Autowired
    ShoppingCartService shoppingCartService;

    public static void main(String[] args) {
        SpringApplication.run(PizzaProjektApplication.class, args);
    }


    @Bean
    CommandLineRunner init() {
        return (evt) -> {

            customerService.addCustomer("hadfs", "hjsd", "letss", "hzrut");

            //customerService.addCustomer("jkas","hdfs","utrzs","zthds");

            //customerService.getAllCustomers().forEach(customer -> System.out.println(customer.getFirstName()));

            //System.out.println(customerService.getCustomerByLoginName("letss"));

            customerService.addCustomerAdress("letss", "hallowe", "5", "zwickau", "5463");

            shoppingCartService.addItemToCart("letss", "PizzaMagarita", "mit käse ohne champinion", 2);
            shoppingCartService.addItemToCart("letss", "Pizzathurnfish", "mit Zwible", 3);

            //customerService.addCustomerAdress("letss","zetrgt","10","Zwickau","5463");

            //System.out.println(customerService.getAdressesForCustomer("letss"));


            System.out.println("Hello Welt");

        }


                ;
    }


}






