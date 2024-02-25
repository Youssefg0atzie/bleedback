package bleed.clt.services;

import bleed.clt.dto.CartItemsDisplayDTO;
import bleed.clt.dto.CheckoutDTO;
import bleed.clt.entities.*;
import bleed.clt.interfaces.ICartService;
import bleed.clt.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CartImpl implements ICartService {
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
    @Autowired
    JwtService jwtService;

    @Override
    public Cart addCart(User user) {
        Cart cart = new Cart();
        cart.setDateCreation(LocalDate.now());
        cart.setOwner(user);
        cart.setTotalPrice(0F);
        cart.setDateLastUpdate(LocalDate.now());

        cart = cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart emptyCart(Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        Cart cart = cartRepository.findByOwner(user);
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        for(CartItem cartItem : cartItems){
            cartItemRepository.delete(cartItem);
        }
        cart.setDateLastUpdate(LocalDate.now());
        cart.setTotalPrice(0F);
        cart = cartRepository.save(cart);
        return cart;
    }

    @Transactional
    @Override
    public Commande validateCart(CheckoutDTO checkoutDTO, Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        Cart cart = cartRepository.findByOwner(user);
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        cartItems = cartItems.stream()
                .filter(c -> c.getState()==0)
                .collect(Collectors.toList());
        Commande commande = new Commande();
        commande.setDateCommande(LocalDate.now());
        commande.setName(checkoutDTO.getName());
        commande.setPhone(checkoutDTO.getPhone());
        commande.setGouvernorat(checkoutDTO.getGouvernorat());
        commande.setVille(checkoutDTO.getVille());
        commande = commandeRepository.save(commande);
        Float sum=0F;
        for(CartItem cartItem : cartItems){
            Float discountedPrice = cartItem.getItem().getDiscountedPrice();

            if (discountedPrice != null && discountedPrice > 0) {
                sum += (cartItem.getQuantity() * discountedPrice);
            } else {
                sum += (cartItem.getQuantity() * cartItem.getItem().getItemPrice());
            }
            Item item=cartItem.getItem();
            item.setStock(cartItem.getItem().getStock()-cartItem.getQuantity());
            itemRepository.save(item);
            cartItem.setState(commande.getIdCommande());
            cartItemRepository.save(cartItem);
            cartItem.setCommande(commande); // Associate the cartItem with the Commande
        }
        cart.setDateLastUpdate(LocalDate.now());
        cart.setOwner(user);
        cart.setTotalPrice(0F);
        cartRepository.save(cart);

        commande.setTotalPrice(sum);
        commande.setUser(user);
        commande = commandeRepository.save(commande);
        return commande;
    }

    @Override
    public List<CartItemsDisplayDTO> getCartItems(Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        Cart cart = cartRepository.findByOwner(user);
        List<CartItemsDisplayDTO> finalList = new ArrayList<>();
        List<CartItem> listCartItems = cartItemRepository.findByCart(cart);
        listCartItems = listCartItems.stream()
                .filter( ci -> ci.getState()==0)
                .collect(Collectors.toList());
        for(CartItem ci : listCartItems){
            CartItemsDisplayDTO cdto = new CartItemsDisplayDTO();
            cdto.setIdCartItem(ci.getIdCartItem());
            cdto.setNomItem(ci.getItem().getNomItem());
            cdto.setQuantity(ci.getQuantity());
            cdto.setDateAdded(ci.getDateAdded());
            cdto.setIdItem(ci.getItem().getIdItem());
            cdto.setItemPrice(ci.getItem().getItemPrice());
            cdto.setDiscountedPrice(ci.getItem().getDiscountedPrice());
            cdto.setSize(ci.getSize());
            finalList.add(cdto);
        }
        return finalList;
    }

    @Override
    public Cart getCart(Long idUser) {
       User user = userRepository.findById(idUser).orElse(null);
       Cart cart = cartRepository.findByOwner(user);
       return cart;
    }

    @Override
    public Integer getItemsInMyCart(Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        Cart cart = cartRepository.findByOwner(user);
        List<CartItem> cartItemList = cartItemRepository.findByCart(cart);
        cartItemList = cartItemList.stream()
                .filter( c -> c.getState()==0)
                .collect(Collectors.toList());
        return cartItemList.size();
    }
}
