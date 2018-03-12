package whz.pti.eva.pizza_projekt.Customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    Pizza findPizzaByName(String name);  // return the pizza object with the given name
    boolean existsByName(String name);  // check if the pizza with the given name exit
}
