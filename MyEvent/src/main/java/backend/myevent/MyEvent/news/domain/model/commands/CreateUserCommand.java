package backend.myevent.MyEvent.news.domain.model.commands;

public record CreateUserCommand(String name, String surname, String address, Integer phone, String correo, String password, String newsApiKey) {

    public CreateUserCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("surname cannot be null or empty");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("address cannot be null or empty");
        }
        if (phone == null) {
            throw new IllegalArgumentException("phone cannot be null");
        }
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("correo cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        if (newsApiKey == null || newsApiKey.isBlank()) {
            throw new IllegalArgumentException("newsApiKey cannot be null or empty");
        }
    }
}
