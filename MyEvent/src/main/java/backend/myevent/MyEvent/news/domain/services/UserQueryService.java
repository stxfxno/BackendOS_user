package backend.myevent.MyEvent.news.domain.services;

import backend.myevent.MyEvent.news.domain.model.aggregates.User;
import backend.myevent.MyEvent.news.domain.model.queries.GetUserByNameAndSurnameQuery;
import backend.myevent.MyEvent.news.domain.model.queries.GetUserByCorreoQuery;
import backend.myevent.MyEvent.news.domain.model.queries.GetUserByIdQuery;
import backend.myevent.MyEvent.news.domain.model.queries.GetAllUserByNewsApiKeyQuery;


import java.util.Optional;
import java.util.List;

public interface UserQueryService {
    List<User> handle(GetAllUserByNewsApiKeyQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByNameAndSurnameQuery query);
    Optional<User> handle(GetUserByCorreoQuery query);
}
