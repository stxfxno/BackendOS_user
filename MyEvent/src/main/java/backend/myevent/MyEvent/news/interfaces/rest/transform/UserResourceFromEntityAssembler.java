package backend.myevent.MyEvent.news.interfaces.rest.transform;

import backend.myevent.MyEvent.news.domain.model.aggregates.User;
import backend.myevent.MyEvent.news.interfaces.rest.resources.UserResource;

import java.util.List;
import java.util.stream.Collectors;

public class UserResourceFromEntityAssembler{

    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
            entity.getName(),
            entity.getSurname(),
            entity.getAddress(),
            entity.getPhone(),
            entity.getCorreo(),
            entity.getPassword(),
            entity.getNewsApiKey());
    }

    public static List<UserResource> toResourceFromEntityList(List<User> entities) {
        return entities.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }
}
