package bleed.clt.controllers;

import bleed.clt.dto.ItemToCartDTO;
import bleed.clt.entities.Cart;
import bleed.clt.entities.Commande;
import bleed.clt.entities.Item;
import bleed.clt.interfaces.ICartItemService;
import bleed.clt.interfaces.IItemService;
import bleed.clt.services.CartItemImpl;
import bleed.clt.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/panier")
public class CartItemRC {
    @Autowired
    JwtService jwtService;
    @Autowired
    ICartItemService cartItemService;
    @Autowired
    private CartItemImpl CartItemImpl;


    @PostMapping("/add")
    public Cart addItemToCart(@RequestBody ItemToCartDTO itemToCartDTO) {
        return cartItemService.addItemToCart(itemToCartDTO);
    }


}
