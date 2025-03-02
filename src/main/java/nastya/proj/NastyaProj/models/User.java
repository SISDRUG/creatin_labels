package nastya.proj.NastyaProj.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import nastya.proj.NastyaProj.models.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс описывающий сущность пользователя в базе данных.
 * Так же реализует интерфейс {@link UserDetails} нужный для авторизации.
 */
@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column(name = "password", nullable = false, length = 1000)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "date_of_created", nullable = false)
    private LocalDateTime dateOfCreated;

    @Column(name = "remember_token")
    private String rememberToken;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<Role>();

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    // Security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
