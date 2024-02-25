package bleed.clt.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRequest implements Serializable {
    private Long itemId;
    private Float discountPercentage;

    // Getters and setters
}

