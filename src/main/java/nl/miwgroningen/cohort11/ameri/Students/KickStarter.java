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
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Mohammed Alameri on 07/06/2023.
 * Creating admin account for the first time the application initialized
 */

@SpringBootApplication
@RequiredArgsConstructor
public class KickStarter implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));

            Set<Role> adminRoles = new HashSet<>();

            for (RoleType roleType : RoleType.values()) {
                Role role = new Role();
                role.setRoleType(roleType);
                adminRoles.add(role);
            }

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
            String password = new Random().ints(10, 33, 122).mapToObj(i -> String.valueOf((char) i)).collect(Collectors.joining());
            System.out.println(password);
            String email = "mark1!@gmail.com";
            System.out.println(email.substring(0, email.indexOf('@')));

        }
    }

}
