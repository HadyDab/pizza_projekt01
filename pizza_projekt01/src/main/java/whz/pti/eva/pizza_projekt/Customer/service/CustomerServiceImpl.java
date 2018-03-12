package whz.pti.eva.pizza_projekt.Customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whz.pti.eva.pizza_projekt.Customer.domain.Address;
import whz.pti.eva.pizza_projekt.Customer.domain.AddressRepository;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.domain.CustomerRepository;
import whz.pti.eva.pizza_projekt.security.domain.User;
import whz.pti.eva.pizza_projekt.security.domain.UserCreateForm;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;

    private AddressRepository addressRepository;


    /**
     *  A simple Construction Injection of Control
     * @param customerRepository
     * @param addressRepository
     */
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;

    }


    /**
     * A simple getter to return all the customers from the DB
     * @return the list of all the customers
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Create a customer from a user
     * @param user the user object to extract information from
     */
    @Override
    public void createCustomer(User user) {
        Customer customer = new Customer();
        customer.setId(user.getId());
        customer.setFirstName(user.getFirstName());
        customer.setLastName(user.getLastName());
        customer.setLoginName(user.getLoginName());
        customerRepository.save(customer);

    }

    /**
     * Save already created Customer
     * @param customer to be saved
     */
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    /**
     * This method returns the customer object base on the given login name
     * @param loginName the login name of the customer
     * @return the customer object
     */
    @Override
    public Customer getCustomerByLoginName(String loginName) {
        return customerRepository.findByLoginName(loginName);
    }

    /**
     * This method will register the given address to the given customer, by first saving the address into the DB
     * @param loginName the login name of the customer
     * @param street    the street name of the address
     * @param housenumber   the house number of the address
     * @param town the name of the town
     * @param zipcode the zipcode of the address
     */
    @javax.transaction.Transactional
    @Override
    public void addCustomerAdress(String loginName, String street, String housenumber, String town, String zipcode) {
        Address address = new Address(street, housenumber, town, zipcode);
        addressRepository.save(address);
        Customer customer;
        customer = customerRepository.findByLoginName(loginName);
        customer.addAddress(address);
        address.addCustomer(customer);


    }

    /**
     * This method will return the list of all addresses register to a given customer login name
     * @param loginName the loginName of the customer
     * @return the list of all the addresses associated to this customer
     */
    @Override
    public List<Address> getAdressesForCustomer(String loginName) {
        Customer customer;
        customer = customerRepository.findByLoginName(loginName);
        return addressRepository.findAdressesByCustomerList(customer);
    }


    /**
     * This method will edit the the first or last name of a customer
     * @param firstName the firstname to be changed to
     * @param lastName
     * @param loginName
     * @param passwordHash
     */
    @Override
    public void editCustomer(String firstName, String lastName, String loginName, String passwordHash) {
        Customer customer = customerRepository.findByLoginName(loginName);
         customer.setFirstName(firstName);
         customer.setLastName(lastName);
         customerRepository.save(customer);
    }



}
