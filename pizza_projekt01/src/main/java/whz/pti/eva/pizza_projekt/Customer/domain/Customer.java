package whz.pti.eva.pizza_projekt.Customer.domain;

import javax.persistence.*;
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

    @OneToOne(fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;



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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

