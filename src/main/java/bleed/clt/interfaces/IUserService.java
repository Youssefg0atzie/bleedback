package bleed.clt.interfaces;

import bleed.clt.dto.LoginDTO;
import bleed.clt.dto.jwtDTO;
import bleed.clt.entities.User;

public interface IUserService {
    User decodeToken(String jwtToken);
    jwtDTO addUser(User user);
    //Boolean editUser(User user);
    //Boolean ForgotPassword(LoginDTO loginDTO);
    Boolean validateAccount(String jwt);
}
