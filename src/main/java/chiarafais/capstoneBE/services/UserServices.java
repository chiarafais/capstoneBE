package chiarafais.capstoneBE.services;

import chiarafais.capstoneBE.entites.User;
import chiarafais.capstoneBE.exceptions.BadRequestException;
import chiarafais.capstoneBE.exceptions.NotFoundException;
import chiarafais.capstoneBE.payloads.User.UserRequiredDTO;
import chiarafais.capstoneBE.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserRequiredDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(user ->
        {
            throw new BadRequestException("Credenziali già in uso");
        });

        this.userRepository.findByUsername(body.username()).ifPresent(user -> {
            throw new BadRequestException("Esiste già un utente con questo username: " + body.username());
        });

        User newUser = new User(body.username(), body.name(), body.surname() , body.email(), passwordEncoder.encode(body.password()));
        User saved = userRepository.save(newUser);

        return saved;
    }


    public Page<User> getUsers(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize = 10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }

    public User getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new NotFoundException("Utente con questo id non trovato");
        }
    }

    public User findByIdAndUpdate(long userId, UserRequiredDTO body) {
        User found = findById(userId);
        found.setEmail(body.email());
        found.setUsername(body.username());
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setPassword(passwordEncoder.encode(body.password()));
        return userRepository.save(found);
    }

    public void findByIdAndDelete(long userId) {
        User found = findById(userId);
        this.userRepository.delete(found);
    }

    public User findById(long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("utente con questa email non trovato"));
    }

}
