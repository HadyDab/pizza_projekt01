package whz.pti.eva.pizza_projekt.Customer.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.eva.pizza_projekt.Customer.domain.Address;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.domain.Item;
import whz.pti.eva.pizza_projekt.Customer.domain.OrderedItems;
import whz.pti.eva.pizza_projekt.Customer.service.CustomerService;
import whz.pti.eva.pizza_projekt.Customer.service.OrderedItemsService;
import whz.pti.eva.pizza_projekt.security.domain.CurrentUser;

import java.util.List;

@Controller
public class CustomerController {


   private CustomerService customerService;
    private OrderedItemsService orderedItemsService;

    @Autowired
    public CustomerController(CustomerService customerService,OrderedItemsService orderedItemsService
                ){
        this.customerService = customerService;
        this.orderedItemsService = orderedItemsService;
    }


    @RequestMapping(value = "/first")
    public String showUserDetails(Model model){
        String customer = getCurrentUser(model);
         List<Address> currentUserAddress = customerService.getAdressesForCustomer(customer);
        model.addAttribute("currentUserAddress",currentUserAddress);
        return "user_details";
    }

    @RequestMapping(value = "finishEditCustomerData", method = RequestMethod.POST)
    public String finishEditCustomerData(Model model,@RequestParam String firstName, @RequestParam String lastName,
                                         @RequestParam String loginName, @RequestParam String passwordHash){

        customerService.editCustomer(firstName,lastName,loginName,passwordHash);
        Customer currentUser = customerService.getCustomerByLoginName(loginName);
        List<Address> customerAddress = customerService.getAdressesForCustomer(currentUser.getLoginName());
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("customerAddress",customerAddress);
        return "user_details";
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



    @RequestMapping(value = "/order/orderHistory",method = RequestMethod.GET)
    public String viewShoppingHistory(Model model , @RequestParam String loginName){
        List<OrderedItems> orderHistory  = orderedItemsService.findOrdersFromCustomer(loginName);
        model.addAttribute("orderhistory",orderHistory);
        return "orderhistory";
    }


    @RequestMapping(value = "/order/view",method = RequestMethod.GET)
    public String viewOrder(@RequestParam("id") long id, Model model){
        OrderedItems Order = orderedItemsService.findbyID(id);
        List<Item>  itemsOrdered = Order.getItemsordered();
        double total = 0.0;
        for(Item i :itemsOrdered){
            total += i.getPizza().getPrice() * i.getQuantity();
        }

        model.addAttribute("itemsOrdered",itemsOrdered);
        model.addAttribute("Orders",Order);
        model.addAttribute("total",total);

        return "viewOrderedItems";
    }



    @RequestMapping(value = "addnewAddress", method = RequestMethod.POST)
    public String getaddaddressform(Model model){

        return"addnewaddress";
    }


    private String getCurrentUser(Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        String currentUserLoginName = currentUser.getLoginName();
        model.addAttribute("currentUserLoginName", currentUserLoginName);
        return currentUserLoginName;
    }







}
