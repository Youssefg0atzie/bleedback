package bleed.clt.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartItemsDisplayDTO implements Serializable {
    private Long idCartItem;
    private LocalDate dateAdded;
    private Integer quantity;
    private String nomItem;
    private Float itemPrice;
    private Long idItem;
    private String size;
    private Float discountedPrice;



}

