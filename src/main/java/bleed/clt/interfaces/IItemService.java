package bleed.clt.interfaces;

import bleed.clt.entities.Cart;
import bleed.clt.entities.Item;
import bleed.clt.entities.User;

import java.util.List;

public interface IItemService {
    Item addItem(Item item);
    Boolean deleteItem(Long idItem);
    Item getItem(Long idItem);
    List<Item> getAllItems();

    Item applyDiscountToItem(Long itemId, Float discountPercentage);
}
