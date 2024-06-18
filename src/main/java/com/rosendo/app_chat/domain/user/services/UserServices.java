package com.rosendo.app_chat.domain.user.services;

import com.rosendo.app_chat.domain.profile.dtos.UserProfileDto;
import com.rosendo.app_chat.domain.profile.repositories.UserProfileRepository;
import com.rosendo.app_chat.domain.profile.services.UserProfileServices;
import com.rosendo.app_chat.domain.user.dtos.CreateAccountCredentialsDto;
import com.rosendo.app_chat.domain.user.models.User;
import com.rosendo.app_chat.domain.user.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
public class UserServices implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserServices(UserRepository repository) {
        this.userRepository = repository;
    }

    @Transactional
    public UserProfileDto createUser(CreateAccountCredentialsDto credentials) {
        var passwordEncoded = passwordEncoder(credentials.password());

        var user = new User(
                credentials.username(),
                credentials.fullName(),
                passwordEncoded,
                true,
                true,
                true,
                true
        );
        userRepository.save(user);

        setUserPermissions(user.getId(), credentials.permission());

        var userProfileService = new UserProfileServices();
        userProfileService.createUserProfile(
                credentials.profileName(),
                credentials.email(),
                0L,
                0L,
                Date.from(Instant.now()),
                user
        );
        return new UserProfileDto(
                credentials.username(),
                credentials.fullName(),
                credentials.email(),
                credentials.email()
        );
    }

    @Transactional
    public void setUserPermissions(UUID userId, Long permissionId) {
        String sql = "INSERT INTO tb_user_permission (id_user, id_permission) VALUES (:userId, :permissionId)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId", userId);
        query.setParameter("permissionId", Objects.requireNonNullElse(permissionId, 3));
        query.executeUpdate();
    }

    public String passwordEncoder(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        Pbkdf2PasswordEncoder pbkdf2Encoder =
                new Pbkdf2PasswordEncoder(
                        "", 8, 185000,
                        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        return passwordEncoder.encode(password).substring(8);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
    }
}
