package za.co.sompisiit.wellness.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "za.co.sompisiit.wellness")
public class PersistenceConfig {

    private static Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                User user = (User) principal;
                return Optional.of(user.getUsername());
            }
            if(principal instanceof String){

                return Optional.of((String) principal);
            }
        }
        return Optional.empty();
    }

    @Bean
    AuditorAware<String> auditorAware() {
        return PersistenceConfig::getCurrentAuditor;
    }
}
