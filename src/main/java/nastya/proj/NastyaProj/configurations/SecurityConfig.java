package nastya.proj.NastyaProj.configurations;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import nastya.proj.NastyaProj.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Класс для настройки авторизации.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    /**
     * Настраивает правила авторизации для HTTP запросов, включая разрешение доступа
     * к определенным URL, настройку страницы входа и разрешение выхода из системы.
     * @param http {@link HttpSecurity} объект, используемый для настройки безопасности приложения.
     * @return настроенный {@link SecurityFilterChain}, который управляет фильтрацией безопасности.
     * @throws Exception, если возникают ошибки при настройке.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(getPermitAllUrls()).permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll())
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    /**
     * Метод, который позволяет настраивать параметры аутентификации.
     * @param http {@link HttpSecurity} объект, используемый для настройки безопасности приложения.
     * @return {@link AuthenticationManager}, который управляет процессом аутентификации.
     * @throws Exception, если возникают ошибки при настройке.
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http
                        .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    /**
     * Метод возвращает экземпляр PasswordEncoder,
     * который является интерфейсом для кодирования паролей.
     * @return объект {@link BCryptPasswordEncoder }.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    /**
     * Метод, который возвращает массив разрешённых ссылок для неавторизованных пользователей.
     * @return массив {@link String} разрешённых ссылок для неавторизованных пользователей.
     */
    private String[] getPermitAllUrls() {
        return new String[] {
                "/",
                "/registration",
                "/css/**",
                "/js/**",
                "/img/**",
                "/fonts/**",
        };
    }
}