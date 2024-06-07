package backend.myevent.MyEvent.news.application.internal.queryservices;

import backend.myevent.MyEvent.news.domain.model.aggregates.User;
import backend.myevent.MyEvent.news.domain.model.queries.*;

import backend.myevent.MyEvent.news.domain.services.UserQueryService;
import backend.myevent.MyEvent.news.infraestructure.persistence.jpa.UserSourceRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService{

    private final UserSourceRepository userSourceRepository;

    public UserQueryServiceImpl(UserSourceRepository userSourceRepository) {
        this.userSourceRepository = userSourceRepository;
    }

    @Override
    public List<User> handle(GetAllUserByNewsApiKeyQuery query) {
        return userSourceRepository.findAllByNewsApiKey(query.newsApiKey());
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userSourceRepository.findById(query.id());
    }

    @Override
    public Optional<User> handle(GetUserByCorreoQuery query) {
        return userSourceRepository.findByCorreo(query.correo());
    }

    @Override
    public Optional<User> handle(GetUserByNameAndSurnameQuery query) {
        return userSourceRepository.findByNameAndSurname(query.name(), query.surname());
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userSourceRepository.findAll();
    }

}
