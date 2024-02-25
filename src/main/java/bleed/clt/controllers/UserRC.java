package bleed.clt.controllers;

import bleed.clt.dto.LoginDTO;
import bleed.clt.dto.jwtDTO;
import bleed.clt.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bleed.clt.entities.jwt.JwtRequest;
import bleed.clt.interfaces.IUserService;
import bleed.clt.services.JwtService;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserRC {
    @Autowired
    IUserService userService;
    @Autowired
    JwtService jwtService;



    @PostMapping("/add")
    public jwtDTO addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    @PostMapping({"/authenticate"})
    public jwtDTO createJwtToken(@RequestBody User user) throws Exception {
        return jwtService.createJwtToken(user);
    }

    @PostMapping("/confirm/{jwt}")
    public Boolean confirmMail(@PathVariable("jwt") String jwt){
        return userService.validateAccount(jwt);
    }

}
