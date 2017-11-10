package whz.pti.eva.pizza_projekt.Customer.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Pizza implements Serializable {


    @Id @GeneratedValue
    private int id;

    private  String name;
    private String description;

    /**
     *
     * @param name
     * @param description
     */
    public Pizza(String name, String description) {
        this.name = name;
        this.description = description;
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
