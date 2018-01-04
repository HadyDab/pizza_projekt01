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


    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,PizzaRepository pizzaRepository) {
        this.itemRepository = itemRepository;
        this.pizzaRepository = pizzaRepository;
    }


    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item findItem(int id) {
        return itemRepository.findOne(id);
    }


}
