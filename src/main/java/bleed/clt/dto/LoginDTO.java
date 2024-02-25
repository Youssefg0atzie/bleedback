package bleed.clt.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {
    private String email;
    private String mdp;
}

