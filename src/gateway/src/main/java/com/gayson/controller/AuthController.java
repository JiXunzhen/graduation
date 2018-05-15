package com.gayson.controller;

import com.gayson.models.Role;
import com.gayson.models.User;
import com.gayson.repos.RoleRepository;
import com.gayson.repos.UserRepository;
import com.gayson.security.JwtTokenUtil;
import com.gayson.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by jixunzhen on 2018/5/12.
 */
@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    Set<Role> defaultRoles;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    @Transactional
    public Result register(@Validated @RequestBody User user) {
        if (userRepository.findByTelephone(user.getTelephone()) != null) {
            return Result.createStatus(Result.ResultStatus.DATA_EXIST);
        }

        // authentication
        user.setSalt(UUID.randomUUID().toString());
        user.setPassword(encoder.encode(user.getPassword() + user.getSalt()));

        // set user default value
        Date now = new Date();
        user.setEnabled(true);
        user.setLastLoginDate(now);
        user.setLastPasswordResetDate(now);
        user.setRoles(defaultRoles);

        System.out.println(user);

        userRepository.save(user);
        return Result.createResult(user);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam(name = "telephone") String telephone,
                        @RequestParam(name = "password") String password) {

        User user = userRepository.findByTelephone(telephone);
        if (user != null) {
            authenticate(telephone, password + user.getSalt());
            final String token = jwtTokenUtil.generateToken(JwtUser.create(user));
            return Result.createResult(token);
        }

        return Result.createStatus(Result.ResultStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/refresh", method = RequestMethod.GET)
    @Transactional
    public Result refreshAuthenticationToken(HttpServletRequest request) {
        final String token = jwtTokenUtil.getToken(request);
        String telephone = jwtTokenUtil.getUsernameFromToken(token);

        User user = userRepository.findByTelephone(telephone);
        JwtUser jwtUser = JwtUser.create(user);

        Date resetDate = new Date();

        if (resetDate.after(jwtUser.getLastPasswordResetDate()) && jwtTokenUtil.canTokenBeRefreshed(token, jwtUser.getLastPasswordResetDate())) {
            user.setLastPasswordResetDate(resetDate);
            userRepository.save(user);
            return Result.createResult(jwtTokenUtil.refreshToken(token));
        }
        return Result.createResult(Result.ResultStatus.EXPIRED_TOKEN, token);
    }

    @RequestMapping(path = "/get")
    public Result getUser(HttpServletRequest request) {
        Long id = jwtTokenUtil.getUserId(request);
        return Result.createResult(userRepository.getOne(id));
    }


    @RequestMapping(path = "/get_users")
    @PreAuthorize("hasRole('ADMIN')")
    public Result getUsers(HttpServletRequest request) {
        return null;
    }


    private void authenticate(String telephone, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(telephone, password));
        } catch (DisabledException e) {
            throw new AuthenticationServiceException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationServiceException("Bad credentials!", e);
        }

    }

}
