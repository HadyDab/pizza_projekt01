package whz.pti.eva.pizza_projekt.Customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findAdressesByCustomerList(Customer customer);

}
