package za.co.sompisiit.wellness.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.co.sompisiit.wellness.model.ApplicationUser;
import za.co.sompisiit.wellness.repository.ApplicationUserRepository;

import javax.annotation.PostConstruct;

@Component
@Transactional
public class Initializer {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private String resetPassword = "password";
    private String adminUsername = "admin";
    private String capturerUsername = "capturer";


    @PostConstruct
    private void init() {
        //since we are executing on startup, we need to use a TransactionTemplate directly as Spring may haven't setup transction capabilities yet
        buildAdmin();
    }

    private void buildAdmin() {
        //here I try to retrieve the Admin from my persistence layer
        ApplicationUser admin = applicationUserRepository.findByUsername(adminUsername);
        ApplicationUser user = applicationUserRepository.findByUsername(capturerUsername);

        try {
            //If the application is started for the first time (e.g., the admin is not in the DB)
            if (admin == null) {
                //create a user for the admin
                admin = new ApplicationUser();
                //and fill her attributes accordingly
                admin.setUsername(adminUsername);
                admin.setPassword(bCryptPasswordEncoder.encode(resetPassword));
//                admin.setAccountNonExpired(true);
//                admin.setAccountNonLocked(true);
//                admin.setCredentialsNonExpired(true);
//                admin.setEnabled(true);
//                admin.setEulaAccepted(true);

//                Authority authority = new Authority();
//                authority.setAuthority("ROLE_ADMIN");
//                admin.getAuthorities().add(authority);
            }
            //if the application has previously been started (e.g., the admin is already present in the DB)
            else {
                //reset admin's attributes
                admin.setPassword(bCryptPasswordEncoder.encode(resetPassword));
//                admin.getAuthorities().clear();
//                Authority authority = new Authority();
//                authority.setAuthority("ROLE_ADMIN");
//                admin.getAuthorities().add(authority);
//                admin.setAccountNonExpired(true);
//                admin.setAccountNonLocked(true);
//                admin.setCredentialsNonExpired(true);
//                admin.setEnabled(true);
            }
            if (user != null) {
                user.setPassword(bCryptPasswordEncoder.encode(resetPassword));
            } else {
                user= new ApplicationUser();
                user.setUsername(capturerUsername);
                user.setPassword(bCryptPasswordEncoder.encode(resetPassword));
            }
            applicationUserRepository.save(user);
            applicationUserRepository.save(admin);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errors occurred during initialization. System verification is required.");
        }
    }
}