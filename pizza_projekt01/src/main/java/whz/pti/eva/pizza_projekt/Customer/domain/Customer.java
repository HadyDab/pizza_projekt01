package whz.pti.eva.pizza_projekt.Customer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    private int id;


    private String firstName;
    private String lastName;
    private String loginName;
    private String passwordHash;


    @ManyToMany
    private List<Address> addressList;


    public Customer() {

    }

    public Customer(String firstName, String lastName, String loginName, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.passwordHash = passwordHash;
        this.addressList = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    /**
     * @param address
     */
    public Customer addAddress(Address address) {
        this.addressList.add(address);
        return this;

    }

    /**
     * @param address
     */
    public void deleteAddress(Address address) {
        this.addressList.remove(address);
    }


    /**
     * @return
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", loginName='" + loginName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return getId() == customer.getId();
    }

    @Override

    public int hashCode() {
        return getId();
    }


}

