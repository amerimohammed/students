package nl.miwgroningen.cohort11.ameri.Students;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.cohort11.ameri.Students.model.Role;
import nl.miwgroningen.cohort11.ameri.Students.model.RoleType;
import nl.miwgroningen.cohort11.ameri.Students.model.User;
import nl.miwgroningen.cohort11.ameri.Students.repository.RoleRepository;
import nl.miwgroningen.cohort11.ameri.Students.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammed Alameri on 07/06/2023.
 * @project Creating admin account for the first time the application initialized
 */

@SpringBootApplication
@RequiredArgsConstructor
public class KickStarter implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));

            for (RoleType roleType : RoleType.values()){
                Role role = new Role();
                role.setRoleType(roleType);
                roleRepository.save(role);
            }

            Role adminRole = new Role();
            adminRole.setRoleType(RoleType.ADMIN);
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);
            userRepository.save(admin);

            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            userRepository.save(user);
            System.out.println(admin.getAuthorities().toString());
            System.err.println("admin with password admin has been created. Remember to change the password");
        } else {
            System.err.println("Admin already exists nothing to do here");
        }
    }
}
