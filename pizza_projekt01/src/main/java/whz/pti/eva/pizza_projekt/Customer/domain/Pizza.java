package whz.pti.eva.pizza_projekt.Customer.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pizza {


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




}
