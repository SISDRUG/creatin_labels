package nastya.proj.NastyaProj.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import nastya.proj.NastyaProj.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Класс реализующий интерфейс {@link UserDetailsService},
 * для загрузки пользователя по email.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Метод, который ищет пользователя в базе данных по email.
     * @param email {@link String}.
     * @return объект {@link nastya.proj.NastyaProj.models.User}, реализующий интерфейс {@link UserDetails}.
     * @throws UsernameNotFoundException, если пользователя с таким email не существует в базе данных.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
