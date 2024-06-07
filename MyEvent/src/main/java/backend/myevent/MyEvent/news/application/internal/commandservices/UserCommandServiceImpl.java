package backend.myevent.MyEvent.news.application.internal.commandservices;

import backend.myevent.MyEvent.news.domain.model.aggregates.User;
import backend.myevent.MyEvent.news.domain.model.commands.CreateUserCommand;
import backend.myevent.MyEvent.news.domain.services.UserCommandService;
import backend.myevent.MyEvent.news.infraestructure.persistence.jpa.UserSourceRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserSourceRepository userSourceRepository;

    public UserCommandServiceImpl(UserSourceRepository userSourceRepository) {
        this.userSourceRepository = userSourceRepository;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        if (userSourceRepository.existsByNameAndSurname(command.correo(), command.surname())) {
            throw new IllegalArgumentException("User with same name and surname already exists");
        }
        var user = new User(command);
        var createdUser = userSourceRepository.save(user);
        return Optional.of(createdUser);
    }

    @Override
    public void handleDeleteUser(String name, String password) {
        Optional<User> user = userSourceRepository.findByNameAndPassword(name, password);
        user.ifPresent(userSourceRepository::delete);
    }

    @Override
    public void handleChangeUserPassword(Long id, String correo, String newPassword) {
        Optional<User> user = userSourceRepository.findByIdAndCorreo(id, correo);
        if(user.isPresent()){
            User existingUser = user.get();
            existingUser.setPassword(newPassword);
            userSourceRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public void handleChangeUserName(Long id, String correo, String currentPassword, String newName) {
        Optional<User> user = userSourceRepository.findByIdAndCorreo(id, correo);
        if(user.isPresent()){
            User existingUser = user.get();
            if(existingUser.getPassword().equals(currentPassword)) {
                existingUser.setName(newName);
                userSourceRepository.save(existingUser);
            } else {
                throw new IllegalArgumentException("Incorrect password");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
