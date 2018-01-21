package whz.pti.eva.pizza_projekt.Customer.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue
    private long id;


    private Date createdDate;


    @OneToMany
    private List<Item> itemsList;


    @OneToOne
    @MapsId
    private Customer customer;


    public ShoppingCart() {
    }

    public ShoppingCart(Date createdDate) {
        this.createdDate = createdDate;
        this.itemsList = new ArrayList<>();
    }


    public long getId() {
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
     * @param item
     * @return
     */
    public ShoppingCart addItems(Item item) {
        this.itemsList.add(item);
        return this;
    }

    /**
     * Delete this Item from the ShoppingCart
     *
     * @param item
     */
    public void deleteItem(Item item) {
        this.itemsList.remove(item);
    }


    /**
     * @param customer
     * @return
     */
    public ShoppingCart addCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }


    public Item findthisItem(Item item){
        for(Item i: itemsList){
            if(i.equals(item)){
                return i;
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCart that = (ShoppingCart) o;

        if (getId() != that.getId()) return false;
        if (getCreatedDate() != null ? !getCreatedDate().equals(that.getCreatedDate()) : that.getCreatedDate() != null)
            return false;
        if (getItemsList() != null ? !getItemsList().equals(that.getItemsList()) : that.getItemsList() != null)
            return false;
        return getCustomer() != null ? getCustomer().equals(that.getCustomer()) : that.getCustomer() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getCreatedDate() != null ? getCreatedDate().hashCode() : 0);
        result = 31 * result + (getItemsList() != null ? getItemsList().hashCode() : 0);
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", itemsList=" + itemsList +
                ", customer=" + customer +
                '}';
    }
}
