package whz.pti.eva.pizza_projekt.Customer.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ShoppingCart implements Serializable{

    @Id @GeneratedValue
    private int id;



    private Date createdDate;


    @OneToMany
    private List<Item> itemsList;


    @ManyToOne
    private Customer customer;




    public ShoppingCart(Date createdDate) {
        this.createdDate = createdDate;
        this.itemsList = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }


    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param item
     * @return
     */
    public ShoppingCart addItems(Item item){
        this.itemsList.add(item);
        return this;
    }

    /**
     * Delete this Item from the ShoppingCart
     * @param item
     */
    public void deleteItem(Item item){
        this.itemsList.remove(item);
    }


    /**
     *
     * @param customer
     * @return
     */
    public ShoppingCart addCustomer(Customer customer){
        this.customer = customer;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCart that = (ShoppingCart) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
