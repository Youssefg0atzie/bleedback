package bleed.clt.interfaces;

import bleed.clt.entities.Cart;
import bleed.clt.entities.Commande;
import bleed.clt.entities.User;

public interface ICommandeService {
    Commande getCommande(Long idCommande);
}
