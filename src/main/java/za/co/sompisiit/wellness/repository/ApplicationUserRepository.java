package za.co.sompisiit.wellness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sompisiit.wellness.model.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}