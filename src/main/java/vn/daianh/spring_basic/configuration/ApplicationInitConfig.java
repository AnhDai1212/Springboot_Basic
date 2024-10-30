package vn.daianh.spring_basic.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.daianh.spring_basic.entity.User;
import vn.daianh.spring_basic.enums.Role;
import vn.daianh.spring_basic.repository.UserRepository;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor  // tu tao constructor khong cần autowired
@Slf4j  // Sinh ra log
public class ApplicationInitConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {  // Add một USER_ADMIN khi chạy lên lần dầu
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()){
                HashSet<String> role = new HashSet<>();
                role.add(Role.ADMIN.name());
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
//                        .roles(role)
                        .build();

                userRepository.save(user);
                log.warn("Admin user has bean created with default password: admin, please change it!");
            }
        };
    }
}
