package whz.pti.eva.pizza_projekt.Customer.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class OrderedItems implements Serializable {

    @Id
    @GeneratedValue
    private long id;


    private Date orderedDate;

    @ManyToMany
    private List<Item> itemsordered;

    @ManyToOne
    private Customer customer;



    public OrderedItems(){
        this.itemsordered = new ArrayList<>();
    }


    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public List<Item> getItemsordered() {
        return itemsordered;
    }

    public void setItemsordered(List<Item> itemsordered) {
        this.itemsordered = itemsordered;
    }

    public void addordereditem(Item item){
        this.itemsordered.add(item);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if(!customer.getOrderHistorys().contains(this)){
            customer.getOrderHistorys().add(this);
        }
    }


    public long getId() {
        return id;
    }
}
