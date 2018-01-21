package whz.pti.eva.pizza_projekt.Customer.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Pizza implements Serializable {


    @Id
    @GeneratedValue
    private int id;




    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false, unique = false)
    private String description;

    @Column(name = "price", nullable = false, unique = false)
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
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
