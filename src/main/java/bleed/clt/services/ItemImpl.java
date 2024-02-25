package bleed.clt.services;

import bleed.clt.entities.*;
import bleed.clt.interfaces.ICartService;
import bleed.clt.interfaces.IItemService;
import bleed.clt.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ItemImpl implements IItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item addItem(Item item) {
        // Check if there's a discount, calculate the discounted price, and set it in the item
        if (item.getDiscountPercentage() > 0) {
            float discountedPrice = item.getItemPrice() - ((item.getDiscountPercentage() / 100.0f) * item.getItemPrice());
            item.setDiscountedPrice(discountedPrice);
        }
        return itemRepository.save(item);
    }

    @Override
    public Boolean deleteItem(Long idItem) {
        return null;
    }

    @Override
    public Item getItem(Long idItem) {
        return itemRepository.findById(idItem).orElse(null);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item applyDiscountToItem(Long itemId, Float discountPercentage) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            if (discountPercentage > 0) {
                float discountedPrice = item.getItemPrice() - ((discountPercentage / 100.0f) * item.getItemPrice());
                item.setDiscountPercentage(discountPercentage);
                item.setDiscountedPrice(discountedPrice);
                return itemRepository.save(item);
            }
        }
        return null; // Or handle as needed if item or discountPercentage is invalid
    }
}
