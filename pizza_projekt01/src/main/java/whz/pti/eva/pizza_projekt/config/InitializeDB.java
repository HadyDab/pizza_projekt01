package whz.pti.eva.pizza_projekt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.domain.CustomerRepository;
import whz.pti.eva.pizza_projekt.Customer.domain.ShoppingCartRepository;
import whz.pti.eva.pizza_projekt.Customer.service.CustomerService;
import whz.pti.eva.pizza_projekt.Customer.service.ItemService;
import whz.pti.eva.pizza_projekt.Customer.service.PizzaService;
import whz.pti.eva.pizza_projekt.Customer.service.ShoppingCartService;
import whz.pti.eva.pizza_projekt.security.domain.Role;
import whz.pti.eva.pizza_projekt.security.domain.User;
import whz.pti.eva.pizza_projekt.security.domain.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ofLocalizedTime;

@Component
public class InitializeDB {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializeDB.class);


    @Autowired
    CustomerService customerService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ItemService itemService;

    @Autowired
    PizzaService pizzaService;


    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @PostConstruct
    public void init()  {

        LOGGER.debug("Db initialized");

        User user = new User();
        user.setFirstName("Hady");
        user.setLastName("Dab");
        user.setLoginName("hadydab");
        user.setPasswordHash("$2a$10$BMqD7u9ctR.Qgzv1J7g7dOf7YZmSNUjYsm0729vBKWzK8bWOMKyMa");
        user.setRole(Role.ADMIN);
        userRepository.save(user);


        User user2 = new User();
        user2.setFirstName("Lo");
        user2.setLastName("John");
        user2.setLoginName("smithD");
        user2.setPasswordHash("$2a$10$MvWxw4PDgWYraTat7X7BJeO7Uo8D/rrnu99RjHAd49zCM/LBTAxPi");
        user2.setRole(Role.CUSTOMER);
        userRepository.save(user2);


        Customer customer = new Customer();
        customer.setId(user2.getId());
        customer.setLoginName(user2.getLoginName());
        customer.setLastName(user.getLastName());
        customer.setFirstName(user2.getFirstName());
        customerRepository.save(customer);



        customerService.addCustomerAdress(customer.getLoginName(), "Oxfordstreet", "5", "London", "DT345W");
        customerService.addCustomerAdress(customer.getLoginName(),"Queen Hellen ave","246","London","W569D8");

        pizzaService.createPizza("Pizza Magarita","Pizza with double Cheese",2.00);
        pizzaService.createPizza("Pizza Tonno","Thunfish with Onions and Mushroom",3.00);

        shoppingCartService.createShoppingcart(customer.getLoginName());




//        User user = new User();
//        user.setFirstName("elisa");
//        user.setLoginName("el@a");
//        user.setPasswordHash("$2a$10$ebyC4Z5WtCXXc.HGDc1Yoe6CLFzcntFmfse6/pTj7CeDY5I05w16C");
//        user.setRole(Role.ADMIN);
//        userRepository.save(user);

        DateTimeFormatter germanFormatter =
                ofLocalizedTime(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);
        String s = LocalTime.now().minusMinutes(10).format(germanFormatter);


    }
}
