package bleed.clt.repositories;

import bleed.clt.entities.Commande;
import bleed.clt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {


    @Query("SELECT c FROM Commande c WHERE c.user = :user ORDER BY c.dateCommande DESC")
    Commande findLatestCommandeByUser(User user);
}