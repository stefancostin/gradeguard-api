package io.github.stefancostin.gradeguard.configurations;

import io.github.stefancostin.gradeguard.models.UserDTO;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public BasicTextEncryptor encryptor() {
        BasicTextEncryptor jasyptEncryptor = new BasicTextEncryptor();
        jasyptEncryptor.setPasswordCharArray("28121991".toCharArray());
        return jasyptEncryptor;
    }
    
}
