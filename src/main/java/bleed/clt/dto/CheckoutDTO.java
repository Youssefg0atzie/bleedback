package bleed.clt.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDTO implements Serializable {
    private String name;
    private String gouvernorat;
    private String ville;
    private String phone;
}

