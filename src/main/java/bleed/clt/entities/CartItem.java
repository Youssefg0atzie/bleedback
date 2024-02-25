package bleed.clt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idCartItem;
    private LocalDate dateAdded;
    private Integer quantity;
    private Long state;
    private String size;

    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Item item;

    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cart cart;

    @ManyToOne(cascade = CascadeType.PERSIST) // Define the relationship to Commande
    private Commande commande; // Reference to Commande

}
