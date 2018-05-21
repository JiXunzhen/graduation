package com.gayson.beans;

import com.gayson.model.Role;
import com.gayson.repos.RoleRepository;
import com.gayson.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan(value = "com.gayson.repos")
public class GatewayConfig {
    @Autowired
    private RoleRepository roleRepository;

    @Bean(initMethod = "init")
    public ServiceMap serviceMap() {
        return new ServiceMap();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public Set<Role> defaultRoles() {
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.getByName(Role.RoleName.ROLE_USER);
        roles.add(userRole);
        return roles;
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
