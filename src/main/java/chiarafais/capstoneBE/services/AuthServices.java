package chiarafais.capstoneBE.services;

import chiarafais.capstoneBE.entites.User;
import chiarafais.capstoneBE.exceptions.UnauthorizedException;
import chiarafais.capstoneBE.payloads.User.UserLoginDTO;
import chiarafais.capstoneBE.security.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    @Autowired
    private UserServices utenteServices;

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String generateToken(UserLoginDTO uld) {
        User utente = utenteServices.findByEmail(uld.email());

        if (bcrypt.matches(uld.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali errate");
        }
    }
}
