package com.rosendo.app_chat.domain.profile.services;

import com.rosendo.app_chat.domain.profile.models.UserProfile;
import com.rosendo.app_chat.domain.profile.repositories.UserProfileRepository;
import com.rosendo.app_chat.domain.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class UserProfileServices {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile createUserProfile(
            String profileName,
            String email,
            Long messageCount,
            Long serverCount,
            Date creationDate,
            User user
    ) {

        var profile = new UserProfile(
                profileName,
                email,
                messageCount,
                serverCount,
                creationDate,
                user
        );
        return userProfileRepository.save(profile);
    }
}
