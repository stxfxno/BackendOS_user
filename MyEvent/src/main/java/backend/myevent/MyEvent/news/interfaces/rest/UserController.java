package backend.myevent.MyEvent.news.interfaces.rest;

import backend.myevent.MyEvent.news.domain.model.aggregates.User;
import backend.myevent.MyEvent.news.domain.model.queries.*;


import backend.myevent.MyEvent.news.domain.services.UserCommandService;
import backend.myevent.MyEvent.news.domain.services.UserQueryService;
import backend.myevent.MyEvent.news.interfaces.rest.resources.CreateUserResource;
import backend.myevent.MyEvent.news.interfaces.rest.resources.UserResource;
import backend.myevent.MyEvent.news.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import backend.myevent.MyEvent.news.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }


    //crear un nuevo usuario con el método post
    //url: http://localhost:8080/api/v1/users/create
    @PostMapping("/create")
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        Optional<User> userSource = userCommandService.handle(CreateUserCommandFromResourceAssembler.toCommandFromResource(resource));
        return userSource.map(source -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //obtener datos del usuario según su ID
    //url: http://localhost:8080/api/v1/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getFavoriteSourceById(@PathVariable Long id) {
        Optional<User> userSource = userQueryService.handle(new GetUserByIdQuery(id));
        return userSource.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    //obtener datos de un ususario según su correo electrónico
    //url: http://localhost:8080/api/v1/users/correo/{correo}
    @GetMapping("/correo/{correo}")
    public ResponseEntity<UserResource> getFavoriteSourceByCorreo(@PathVariable String correo) {
        Optional<User> userSource = userQueryService.handle(new GetUserByCorreoQuery(correo));
        return userSource.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //getmapping por nombre y apellido
    //url: http://localhost:8080/api/v1/users/name/{name}/surname/{surname}
    @GetMapping("/name/{name}/surname/{surname}")
    public ResponseEntity<UserResource> getFavoriteSourceByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
        Optional<User> userSource = userQueryService.handle(new GetUserByNameAndSurnameQuery(name, surname));
        return userSource.map(source -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(source))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //getmapping de todos los usuarios
    //url: http://localhost:8080/api/v1/users/all
    @GetMapping("/all")
    public ResponseEntity<List<UserResource>> getAllUsers() {
        List<User> users = userQueryService.handle(new GetAllUsersQuery());
        List<UserResource> userResources = UserResourceFromEntityAssembler.toResourceFromEntityList(users);
        return ResponseEntity.ok(userResources);
    }

    //delete mapping para eliminar un usuario
    //url: http://localhost:8080/api/v1/users/delete/{name}/{password}
    @DeleteMapping("/delete/{name}/{password}")
    public ResponseEntity<Void> deleteUser(@PathVariable String name, @PathVariable String password) {
        userCommandService.handleDeleteUser(name, password);
        return ResponseEntity.noContent().build();
    }

    //putmapping para cambiar la contraseña de un usuario
    //url: http://localhost:8080/api/v1/users/change-password/{id}/{correo}
    @PutMapping("/change-password/{id}/{correo}")
    public ResponseEntity<Void> changeUserPassword(@PathVariable Long id, @PathVariable String correo, @RequestBody Map<String, String> password) {
        userCommandService.handleChangeUserPassword(id, correo, password.get("password"));
        return ResponseEntity.noContent().build();
    }

    //putmapping para cambiar el nombre de un usuario
    //url: http://localhost:8080/api/v1/users/change-name/{id}/{correo}
    @PutMapping("/change-name/{id}/{correo}")
    public ResponseEntity<Void> changeUserName(@PathVariable Long id, @PathVariable String correo, @RequestBody Map<String, String> name) {
        userCommandService.handleChangeUserName(id, correo, name.get("password"), name.get("name"));
        return ResponseEntity.noContent().build();
    }
}
