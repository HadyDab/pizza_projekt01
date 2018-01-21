package whz.pti.eva.pizza_projekt.security.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class PizzaCreateForm {

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String description = "";

    @NotEmpty
    private double price = 0.0;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
