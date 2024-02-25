package bleed.clt.controllers;

import bleed.clt.dto.CartItemsDisplayDTO;
import bleed.clt.dto.CheckoutDTO;
import bleed.clt.entities.*;
import bleed.clt.interfaces.ICartService;
import bleed.clt.interfaces.IItemService;
import bleed.clt.services.JwtService;
import bleed.clt.services.UserImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartRC {
    @Autowired
    JwtService jwtService;
    @Autowired
    ICartService cartService;
    @Autowired
    UserImpl userService;


    @PostMapping("/validate")
    public Commande validateCart(@RequestBody CheckoutDTO checkoutDTO,
                                @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        User user = userService.decodeToken(jwtToken);
        return cartService.validateCart(checkoutDTO,user.getIdUser());
    }

    @GetMapping("/emptycart")
    public Cart emptyCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken){
        User user = userService.decodeToken(jwtToken);
        return cartService.emptyCart(user.getIdUser());
    }

    @GetMapping("/items")
    public List<CartItemsDisplayDTO> getCartItems(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken){
        User user = userService.decodeToken(jwtToken);
        return cartService.getCartItems(user.getIdUser());
    }

    @GetMapping("/mycart")
    public Cart getMyCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken){
        User user = userService.decodeToken(jwtToken);
        return cartService.getCart(user.getIdUser());
    }

    @GetMapping("/itemsincart")
    public Integer getItemsCountInMyCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken){
        User user = userService.decodeToken(jwtToken);
        return cartService.getItemsInMyCart(user.getIdUser());
    }
}
