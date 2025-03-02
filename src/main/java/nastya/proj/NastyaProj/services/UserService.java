package nastya.proj.NastyaProj.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nastya.proj.NastyaProj.models.User;
import nastya.proj.NastyaProj.models.enums.Role;
import nastya.proj.NastyaProj.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Класс, который обрабатывает операции связанные с пользователем.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Метод, который создаёт запись о пользователе в базе данных, если ещё не существует.
     * @param user {@link User}
     * @return true, если удалось создать запись пользователя в базе данных. В ином случае false.
     */
    public boolean createUser(User user) {
        String email = user.getEmail();

        if(isUserExists(email)) return false;
        saveUser(user);

        log.info("Saving new User with email: {}", email);

        return true;
    }

    /**
     * Метод, который проверяет существование записи о пользователе в базе данных.
     * @param email {@link String}
     * @return true, если запись о пользователе с таким email уже существует. В ином случае false.
     */
    private boolean isUserExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    /**
     * Метод, который сохраняет данные о пользователе в базе данных.
     * @param user {@link User}
     */
    private void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.getRoles().add(Role.ROLE_ADMIN);
        userRepository.save(user);
    }
}
