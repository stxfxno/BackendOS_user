package backend.myevent.MyEvent.news.infraestructure.persistence.jpa;

import backend.myevent.MyEvent.news.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSourceRepository extends JpaRepository<User, Long>{
    List<User> findAllByNewsApiKey(String newsApiKey);
    boolean existsByNameAndSurname(String name, String surname );
    Optional<User> findByNameAndSurname(String name, String surname);
    Optional<User> findByCorreo(String correo);
    Optional<User> findByNameAndPassword(String name, String password);
    Optional<User> findByIdAndCorreo(Long id, String correo);
}
