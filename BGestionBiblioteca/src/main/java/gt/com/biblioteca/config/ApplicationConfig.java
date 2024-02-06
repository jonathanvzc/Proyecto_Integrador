package gt.com.biblioteca.config;

import gt.com.biblioteca.model.mongo.UsuarioDocument;
import gt.com.biblioteca.model.postgre.UsuarioEntity;
import gt.com.biblioteca.repository.mongo.IUsuarioRepositoryMongoDB;
import gt.com.biblioteca.repository.postgre.IUsuarioRepositoryPostgreSQL;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final IUsuarioRepositoryPostgreSQL iUsuarioRepositoryPostgreSQL;
    private final IUsuarioRepositoryMongoDB iUsuarioRepositoryMongoDB;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UsuarioEntity postgresUser = iUsuarioRepositoryPostgreSQL.findByCorreoElectronicoAndEstadoIsTrue(username)
                    .orElse(null);
            UsuarioDocument mongoUser = iUsuarioRepositoryMongoDB.findByCorreoElectronicoAndEstadoIsTrue(username)
                    .orElse(null);
            if (postgresUser != null) {
                return postgresUser;
            } else {
                return mongoUser;
            }
        };
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
