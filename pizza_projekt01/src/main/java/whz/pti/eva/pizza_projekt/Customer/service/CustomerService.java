package whz.pti.eva.pizza_projekt.Customer.service;

import whz.pti.eva.pizza_projekt.Customer.domain.Address;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    void addCustomer(String firstName, String lastName, String loginName,
                     String passwordHash);

    Customer getCustomerByLoginName(String loginName);

    void addCustomerAdress(String loginName, String street, String
            housenumber, String town, String zipcode);

    List<Address> getAdressesForCustomer(String loginName);
}
