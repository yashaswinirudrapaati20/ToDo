package com.Yashaswini.MyWebApp.security;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguraiton {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails user = createNewUser("yashu", "abcdef");
        UserDetails user1=createNewUser("Yash","yashu");
        return new InMemoryUserDetailsManager(user,user1);
    }

    private @NonNull UserDetails createNewUser(String username, String password) {
        Function<String,String> passwordEncoder=
                input->passwordEnc().encode(input);

        UserDetails user = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return user;
    }

    @Bean
    public PasswordEncoder passwordEnc(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.anyRequest().authenticated()
        );
        http.formLogin(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.headers(headers ->
                headers.frameOptions(frame -> frame.disable())
        );
        return http.build();
    }
}