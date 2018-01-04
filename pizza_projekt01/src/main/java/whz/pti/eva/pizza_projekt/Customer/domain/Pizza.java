package whz.pti.eva.pizza_projekt.Customer.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Pizza implements Serializable {


    @Id
    @GeneratedValue
    private int id;


    private String name;
    private String description;
    private double price;


    public Pizza() {
    }

    /**
     * @param name
     * @param description
     */
    public Pizza(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        return getId() == pizza.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
