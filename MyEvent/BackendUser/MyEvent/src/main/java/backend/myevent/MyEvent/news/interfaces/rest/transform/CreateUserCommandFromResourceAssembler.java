package backend.myevent.MyEvent.news.interfaces.rest.transform;

import backend.myevent.MyEvent.news.domain.model.commands.CreateUserCommand;
import backend.myevent.MyEvent.news.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {

    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
            resource.name(),
            resource.surname(),
            resource.address(),
            resource.phone(),
            resource.correo(),
            resource.password(),
            resource.newsApiKey());
    }
}
