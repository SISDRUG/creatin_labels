package nastya.proj.NastyaProj.repositories;

import nastya.proj.NastyaProj.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Метод для поиска пользователя по email.
     * @param email {@link String}.
     * @return объект {@link User}.
     */
    User findByEmail(String email);
}
