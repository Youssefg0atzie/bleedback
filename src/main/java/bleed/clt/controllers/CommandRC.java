package bleed.clt.controllers;

import bleed.clt.dto.CommandeDTO;
import bleed.clt.dto.ItemToCartDTO;
import bleed.clt.entities.CartItem;
import bleed.clt.entities.Commande;
import bleed.clt.entities.Item;
import bleed.clt.repositories.CommandeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandRC {

    private final CommandeRepository commandeRepository;

    // Constructor injection of CommandeRepository
    public CommandRC(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    // Method to get Commande by ID along with its CartItems
    @GetMapping
    public List<CommandeDTO> getAllCommandesWithCartItems() {
        List<Commande> commandes = commandeRepository.findAll(); // Fetch all commands from the repository
        List<CommandeDTO> commandeDTOs = new ArrayList<>();

        for (Commande commande : commandes) {
            CommandeDTO commandeDTO = new CommandeDTO();
            commandeDTO.setIdCommande(commande.getIdCommande());
            commandeDTO.setDateCommande(commande.getDateCommande());
            commandeDTO.setTotalPrice(commande.getTotalPrice());
            commandeDTO.setName(commande.getName());
            commandeDTO.setGouvernorat(commande.getGouvernorat());
            commandeDTO.setVille(commande.getVille());
            commandeDTO.setPhone(commande.getPhone());

            List<CartItem> cartItems = commande.getCartItems();
            List<ItemToCartDTO> cartItemDTOs = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                ItemToCartDTO cartItemDTO = new ItemToCartDTO();
                cartItemDTO.setIdCartItem(cartItem.getIdCartItem());
                cartItemDTO.setDateAdded(cartItem.getDateAdded());
                cartItemDTO.setQuantity(cartItem.getQuantity());
                cartItemDTO.setState(cartItem.getState());
                cartItemDTO.setSize(cartItem.getSize());

                Item item = cartItem.getItem();
                if (item != null) {
                    cartItemDTO.setItemId(item.getIdItem());
                    cartItemDTO.setItemName(item.getNomItem());
                }

                cartItemDTOs.add(cartItemDTO);
            }
            commandeDTO.setCartItems(cartItemDTOs);
            commandeDTOs.add(commandeDTO);
        }

        return commandeDTOs;
    }

}

