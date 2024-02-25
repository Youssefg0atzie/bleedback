package bleed.clt.services;

import bleed.clt.dto.ItemToCartDTO;
import bleed.clt.entities.*;
import bleed.clt.interfaces.ICartItemService;
import bleed.clt.interfaces.ICartService;
import bleed.clt.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class CartItemImpl implements ICartItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;



    @Override
    public Cart addItemToCart(ItemToCartDTO itemToCartDTO) {
        User user = userRepository.findById(itemToCartDTO.getIdUser()).orElse(null);
        Item item = itemRepository.findById(itemToCartDTO.getIdItem()).orElse(null);
        Cart cart = cartRepository.findByOwner(user);
        String selectedSize = itemToCartDTO.getSize(); // Size selected for this cart item
        CartItem cartItem = new CartItem();
        cartItem.setState(0L);
        cartItem.setQuantity(itemToCartDTO.getQuantity());
        cartItem.setItem(item);
        cartItem.setCart(cart);
        cartItem.setDateAdded(LocalDate.now());
        cartItem.setSize(itemToCartDTO.getSize());

        // Update the cart total price considering the discounted price
        Float discountedPrice = item.getDiscountedPrice();
        if (discountedPrice != null && discountedPrice > 0) {
            cart.setTotalPrice(cart.getTotalPrice() + (cartItem.getQuantity() * discountedPrice));
        } else {
            cart.setTotalPrice(cart.getTotalPrice() + (cartItem.getQuantity() * item.getItemPrice()));
        }

        cartItem = cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
    }


    @Override
    public void validateItem(Long idCartItem) {

    }


    @Override
    public List<CartItem> getAllItems(Long idUser) {
        return null;
    }
}
