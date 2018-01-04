package whz.pti.eva.pizza_projekt.Customer.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    Customer customer = customerService.getCustomerByLoginName("smithD");
    List<Address> customerAddress = customerService.getAdressesForCustomer(customer.getLoginName());
        model.addAttribute(customer);
        model.addAttribute("customerAddress",customerAddress);
        //System.out.println(customerAddress.get(0).getStreet());
        return "user_details";
    }

    @RequestMapping(value = "/start_edit_customer_data", method = RequestMethod.POST)
    public String startEditCustomerData(Model model, @RequestParam String loginName){
        Customer currentCustomer = customerService.getCustomerByLoginName(loginName);
        model.addAttribute(currentCustomer);
        return "edit_customer_data";
    }


    @RequestMapping(value = "finishEditCustomerData", method = RequestMethod.POST)
    public String finishEditCustomerData(Model model,@RequestParam String firstName, @RequestParam String lastName,
                                         @RequestParam String loginName, @RequestParam String passwordHash){
        customerService.editCustomer(firstName,lastName,loginName,passwordHash);


        Customer customer = customerService.getCustomerByLoginName(loginName);
        List<Address> customerAddress = customerService.getAdressesForCustomer(customer.getLoginName());
        model.addAttribute(customer);
        model.addAttribute("customerAddress",customerAddress);
        return "user_details";
    }


    @RequestMapping(value = "start_edit_this_address", method = RequestMethod.POST)
    public String addnewAddress(Model model, @RequestParam String street, @RequestParam String houseNumber,@RequestParam
                                String town, @RequestParam String zipcode,@RequestParam String loginName){
        Customer customer = customerService.getCustomerByLoginName(loginName);
        Address newAddress = new Address(street,houseNumber,town,zipcode);
        model.addAttribute("address",newAddress);


        return"edit_address_data";
    }


    @RequestMapping(value = "finishEditingAddress", method = RequestMethod.POST)
    public String finishEditingAddressData (Model model, @RequestParam String street, @RequestParam String houseNumber,@RequestParam
            String town, @RequestParam String zipcode,@RequestParam String loginName){




        Customer customer = customerService.getCustomerByLoginName(loginName);
        List<Address> customerAddress = customerService.getAdressesForCustomer(customer.getLoginName());
        model.addAttribute(customer);
        model.addAttribute("customerAddress",customerAddress);

        return"user_details";
    }
}
