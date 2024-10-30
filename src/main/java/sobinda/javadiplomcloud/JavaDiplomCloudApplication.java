package sobinda.javadiplomcloud;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import sobinda.javadiplomcloud.entity.UserEntity;
import sobinda.javadiplomcloud.repository.UsersRepository;

import static sobinda.javadiplomcloud.model.Role.ROLE_ADMIN;
import static sobinda.javadiplomcloud.model.Role.ROLE_READ;

@SpringBootApplication
@RequiredArgsConstructor
public class JavaDiplomCloudApplication implements CommandLineRunner {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(JavaDiplomCloudApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("Alex");
        userEntity.setPassword(passwordEncoder.encode("123"));
        userEntity.setRole(ROLE_ADMIN);
        System.out.println(userEntity);

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setLogin("set");
        userEntity1.setPassword(passwordEncoder.encode("123"));
        userEntity1.setRole(ROLE_ADMIN);
        System.out.println(userEntity1);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setLogin("rot");
        userEntity2.setPassword(passwordEncoder.encode("123"));
        userEntity2.setRole(ROLE_READ);
        System.out.println(userEntity2);

        usersRepository.save(userEntity);
        usersRepository.save(userEntity1);
        usersRepository.save(userEntity2);
    }

}
