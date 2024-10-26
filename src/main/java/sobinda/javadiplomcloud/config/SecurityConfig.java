package sobinda.javadiplomcloud.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import sobinda.javadiplomcloud.security.JWTFilter;

//import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    //todo Сделать свой
    //private final JWTFilter jwtFilter;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


//    @SuppressWarnings("removal")
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.cors().and()
//                .httpBasic().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests(
//                        authz -> authz
//                                .requestMatchers("/login").permitAll()
//                                .anyRequest().authenticated()
//                                .and()
//                                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                ).logout(
//                        logout -> logout.permitAll()
//                                .logoutSuccessHandler((request, response, authentication) -> {
//                                    response.setStatus(HttpServletResponse.SC_OK);
//                                })
//                ).csrf().disable().build();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SneakyThrows
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) {
        //noinspection removal
        return http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .cors().and()
                .logout().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().requestMatchers(HttpMethod.POST, "/login").permitAll()
                .and()
                //todo поменять обратно, когда получится корректно настроить доступы
                .authorizeRequests().anyRequest().authenticated()
//                .authorizeRequests().anyRequest().permitAll()

                .and()
                //.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
