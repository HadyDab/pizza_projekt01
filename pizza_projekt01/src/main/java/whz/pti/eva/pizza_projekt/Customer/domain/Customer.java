package whz.pti.eva.pizza_projekt.Customer.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer implements Serializable {

    @Id
    private long id;


    private String firstName;
    private String lastName;
    private String loginName;


    @ManyToMany
    private List<Address> addressList;

    @OneToOne(fetch = FetchType.LAZY)
    private ShoppingCart shoppingCart;


    @OneToMany(mappedBy = "customer")
    private List<OrderedItems> orderHistorys;



    public Customer() {

    }

    public Customer(String firstName, String lastName, String loginName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.addressList = new ArrayList<>();
        this.orderHistorys = new ArrayList<>();
    }

    public long getId() {
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

    public List<OrderedItems> getOrderHistorys() {
        return orderHistorys;
    }

    public void addOrderedItems(OrderedItems orderedItems){
        this.orderHistorys.add(orderedItems);
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setId(long id) {
        this.id = id;
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

        if (id != customer.id) return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
        if (loginName != null ? !loginName.equals(customer.loginName) : customer.loginName != null) return false;
        if (addressList != null ? !addressList.equals(customer.addressList) : customer.addressList != null)
            return false;
        if (shoppingCart != null ? !shoppingCart.equals(customer.shoppingCart) : customer.shoppingCart != null)
            return false;
        return orderHistorys != null ? orderHistorys.equals(customer.orderHistorys) : customer.orderHistorys == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (addressList != null ? addressList.hashCode() : 0);
        result = 31 * result + (shoppingCart != null ? shoppingCart.hashCode() : 0);
        result = 31 * result + (orderHistorys != null ? orderHistorys.hashCode() : 0);
        return result;
    }
}

