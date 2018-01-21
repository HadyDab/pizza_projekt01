package whz.pti.eva.pizza_projekt.Customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedItemsRepository extends JpaRepository<OrderedItems, Integer> {
   List<OrderedItems>  findOrderedItemsByCustomer(Customer customer);
}
