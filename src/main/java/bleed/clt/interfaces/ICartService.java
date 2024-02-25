package bleed.clt.interfaces;

import bleed.clt.dto.CartItemsDisplayDTO;
import bleed.clt.dto.CheckoutDTO;
import bleed.clt.entities.Cart;
import bleed.clt.entities.CartItem;
import bleed.clt.entities.Commande;
import bleed.clt.entities.User;

import java.util.List;

public interface ICartService {
    Cart addCart(User user);
    Cart emptyCart(Long idUser);
    Commande validateCart(CheckoutDTO checkoutDTO, Long idUser);
    List<CartItemsDisplayDTO> getCartItems(Long idUser);
    Cart getCart(Long idUser);
    Integer getItemsInMyCart(Long idUser);
}
