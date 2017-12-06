package whz.pti.eva.pizza_projekt.Customer.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.pti.eva.pizza_projekt.Customer.domain.Address;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {


    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }


    @RequestMapping("user_details")
    public String showUserDetails(Model model){
    Customer customer = customerService.getCustomerByLoginName("letss");
    List<Address> customerAddress = customerService.getAdressesForCustomer(customer.getLoginName());
        model.addAttribute(customer);
        model.addAttribute("customerAddress",customerAddress);
        System.out.println(customerAddress.get(0).getStreet());
        return "user_details";
    }

}
