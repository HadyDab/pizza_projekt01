package whz.pti.eva.pizza_projekt.Customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedItemsRepository extends JpaRepository<OrderedItems, Integer> {
   List<OrderedItems>  findOrderedItemsByCustomer(Customer customer); // get all items ordered by the given Customer
   OrderedItems findById(long id); // return the ordered item with this id
}
