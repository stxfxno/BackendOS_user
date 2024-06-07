package backend.myevent.MyEvent.news.domain.services;
import backend.myevent.MyEvent.news.domain.model.aggregates.User;
import backend.myevent.MyEvent.news.domain.model.commands.CreateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
    void handleDeleteUser(String name, String password);
    void handleChangeUserPassword(Long id, String correo, String newPassword);
    void handleChangeUserName(Long id, String correo, String currentPassword, String newName);

}
