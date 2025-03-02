package nastya.proj.NastyaProj.models.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Перечисление содержащее возможные роли пользователя.
 * Так же реализует интерфейс {@link GrantedAuthority} нужный для авторизации.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
