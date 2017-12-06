package whz.pti.eva.pizza_projekt.Customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByLoginName(String loginName);
}
