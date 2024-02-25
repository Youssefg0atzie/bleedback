package bleed.clt.controllers;

import bleed.clt.dto.DiscountRequest;
import bleed.clt.entities.Item;
import bleed.clt.entities.User;
import bleed.clt.entities.jwt.JwtRequest;
import bleed.clt.interfaces.IItemService;
import bleed.clt.interfaces.IUserService;
import bleed.clt.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/item")
public class ItemRC {
    @Autowired
    JwtService jwtService;
    @Autowired
    IItemService itemService;


    @PostMapping("/add")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @PostMapping("/applyDiscount")
    public ResponseEntity<String> applyDiscountToItem(@RequestBody DiscountRequest discountRequest) {
        Long itemId = discountRequest.getItemId();
        Float discountPercentage = discountRequest.getDiscountPercentage();

        Item item = itemService.applyDiscountToItem(itemId, discountPercentage);
        if (item != null) {
            return ResponseEntity.ok("Discount applied successfully to item ID: " + itemId);
        } else {
            return ResponseEntity.badRequest().body("Item ID: " + itemId + " not found");
        }
    }

    @GetMapping("/browse")
    public List<Item> getItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/detail/{idItem}")
    public Item getItem(@PathVariable("idItem") Long idItem){
        return itemService.getItem(idItem);
    }
}
