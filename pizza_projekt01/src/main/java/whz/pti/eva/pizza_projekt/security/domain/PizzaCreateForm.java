package whz.pti.eva.pizza_projekt.security.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class PizzaCreateForm {

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String description = "";

    @NotEmpty
    private String price = "";


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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
