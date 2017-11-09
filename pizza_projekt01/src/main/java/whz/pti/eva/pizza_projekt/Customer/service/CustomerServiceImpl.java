package whz.pti.eva.pizza_projekt.Customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whz.pti.eva.pizza_projekt.Customer.domain.Address;
import whz.pti.eva.pizza_projekt.Customer.domain.AddressRepository;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.domain.CustomerRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService{


     private CustomerRepository customerRepository;

     private  AddressRepository addressRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository){
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;

    }


    /**
     *
     * @return
     */
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers = customerRepository.findAll();
        return customers;
    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param loginName
     * @param passwordHash
     */
    @Override
    public void addCustomer(String firstName, String lastName, String loginName, String passwordHash) {
        Customer customer = new Customer(firstName,lastName,loginName,passwordHash);
        customerRepository.save(customer);
    }

    /**
     *
     * @param loginName
     * @return
     */
    @Override
    public Customer getCustomerByLoginName(String loginName) {
        return customerRepository.findByLoginName(loginName);
    }

    /**
     *
     * @param loginName
     * @param street
     * @param housenumber
     * @param town
     * @param zipcode
     */
    @javax.transaction.Transactional
    @Override
    public void addCustomerAdress(String loginName, String street, String housenumber, String town, String zipcode) {
        Address address = new Address(street,housenumber,town,zipcode);
        addressRepository.save(address);
        Customer customer = new Customer();
        customer = customerRepository.findByLoginName(loginName);
        customer.addAddress(address);
        address.addCustomer(customer);


    }

    /**
     *
     * @param loginName
     * @return
     */
    @Override
    public List<Address> getAdressesForCustomer(String loginName) {

       List<Address> customerAddress = new ArrayList<>();
       Customer customer = new Customer();
       customer = customerRepository.findByLoginName(loginName);
        return customerAddress = addressRepository.findAdressesBycustomerList(customer);
    }




}
