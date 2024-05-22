package org.raul.receipesweb.config;

import org.raul.receipesweb.filter.JwtRequestFilter;
import org.raul.receipesweb.service.JwtUserDetailsService;
import org.raul.receipesweb.utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

/*    @Bean
    public JwtUserDetailsService jwtUserDetailsService(PasswordEncoder passwordEncoder) {
        return new JwtUserDetailsService(passwordEncoder);
    }*/

/*    @Bean
    public JwtRequestFilter jwtRequestFilter(JwtUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();
        jwtRequestFilter.setJwtUserDetailsService(jwtUserDetailsService);
        jwtRequestFilter.setJwtTokenUtil(jwtTokenUtil);
        return jwtRequestFilter;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}