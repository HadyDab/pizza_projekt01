package whz.pti.eva.pizza_projekt.Customer.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Address implements Serializable {


    @Id
    @GeneratedValue
    private int id;


    private String street;
    private String houseNumber;
    private String town;
    private String zipcode;


    @ManyToMany(mappedBy = "addressList")
    private List<Customer> customerList;


    public Address() {
    }


    /**
     * @param street
     * @param houseNumber
     * @param town
     * @param zipcode
     */
    public Address(String street, String houseNumber, String town, String zipcode) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.town = town;
        this.zipcode = zipcode;
        this.customerList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getTown() {
        return town;
    }

    public String getZipcode() {
        return zipcode;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }


    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * @param customer
     */
    public Address addCustomer(Customer customer) {
        this.customerList.add(customer);
        return this;
    }

    /**
     * @param customer
     */
    public void deleteCustomer(Customer customer) {
        this.customerList.remove(customer);
    }


    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", town='" + town + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return getId() == address.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
