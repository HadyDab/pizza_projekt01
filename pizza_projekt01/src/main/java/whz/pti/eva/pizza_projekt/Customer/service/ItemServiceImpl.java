package whz.pti.eva.pizza_projekt.Customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_projekt.Customer.domain.Item;
import whz.pti.eva.pizza_projekt.Customer.domain.ItemRepository;
import whz.pti.eva.pizza_projekt.Customer.domain.Pizza;
import whz.pti.eva.pizza_projekt.Customer.domain.PizzaRepository;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private ItemRepository itemRepository;
    private PizzaRepository pizzaRepository;

    /**
     *  A simple Construction Injection of Control
     * @param itemRepository
     * @param pizzaRepository
     */
    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,PizzaRepository pizzaRepository) {
        this.itemRepository = itemRepository;
        this.pizzaRepository = pizzaRepository;
    }

    /**
     * Get all the items listed
     * @return the list of all the items
     */
    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Find an item with the given id
     * @param id of the item to be found
     * @return item object with the given id
     */
    @Override
    public Item findItem(int id) {
        return itemRepository.findOne(id);
    }


}
