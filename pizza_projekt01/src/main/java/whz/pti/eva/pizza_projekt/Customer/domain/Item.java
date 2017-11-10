package whz.pti.eva.pizza_projekt.Customer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Item implements Serializable{

    @Id @GeneratedValue
    private int id;





    private int quantity;


    @ManyToOne
    private Pizza pizza;


    public Item(){

    }



    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Pizza getPizza() {
        return pizza;
    }





    public void addQuantity(){
        this.quantity++;
    }



    public void resetQuantity(){
        this.quantity = 0;
    }

    /**
     *
     * @param pizza
     * @return
     */
    public Item addPizza(Pizza pizza){
            this.pizza = pizza;
            return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return getId() == item.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }



}
