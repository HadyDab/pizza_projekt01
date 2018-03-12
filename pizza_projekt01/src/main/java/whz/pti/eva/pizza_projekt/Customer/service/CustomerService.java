package whz.pti.eva.pizza_projekt.Customer.service;

import whz.pti.eva.pizza_projekt.Customer.domain.Address;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.security.domain.User;
import whz.pti.eva.pizza_projekt.security.domain.UserCreateForm;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();  // return the List of all customers

    void createCustomer(User user);  // create a Customer

    void saveCustomer(Customer customer); // save already created customer

    Customer getCustomerByLoginName(String loginName); // return the customer object with the given name

    void addCustomerAdress(String loginName, String street, String
            housenumber, String town, String zipcode);    // add customer address

    List<Address> getAdressesForCustomer(String loginName); // return all the addresses associated with the given customer

    void editCustomer(String firstName, String lastName, String loginName, String passwordHash); // edit customer informations

}