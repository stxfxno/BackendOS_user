package backend.myevent.MyEvent.news.interfaces.rest.transform;

import backend.myevent.MyEvent.news.domain.model.aggregates.User;
import backend.myevent.MyEvent.news.interfaces.rest.resources.UserResource;
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
}
