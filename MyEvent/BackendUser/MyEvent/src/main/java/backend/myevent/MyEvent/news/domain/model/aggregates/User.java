package backend.myevent.MyEvent.news.domain.model.aggregates;

import backend.myevent.MyEvent.news.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAggregateRoot<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String newsApiKey;

    protected User() {}

    public User(CreateUserCommand command) {
        this.name = command.name();
        this.surname = command.surname();
        this.correo = command.correo();
        this.password = command.password();
        this.address = command.address();
        this.phone = String.valueOf(command.phone());
        this.newsApiKey = command.newsApiKey();
    }
}