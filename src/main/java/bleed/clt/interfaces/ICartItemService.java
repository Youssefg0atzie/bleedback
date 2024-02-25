package bleed.clt.interfaces;

import bleed.clt.dto.ItemToCartDTO;
import bleed.clt.entities.Cart;
import bleed.clt.entities.CartItem;
import bleed.clt.entities.User;

import java.util.List;

public interface ICartItemService {
    Cart addItemToCart(ItemToCartDTO itemToCartDTO);
    void validateItem(Long idCartItem);
    List<CartItem> getAllItems(Long idUser);
}
