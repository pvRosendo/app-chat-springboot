package com.rosendo.app_chat.domain.profile.services;

import com.rosendo.app_chat.domain.profile.models.UserProfile;
import com.rosendo.app_chat.domain.profile.repositories.UserProfileRepository;
import com.rosendo.app_chat.domain.user.models.User;
import com.rosendo.app_chat.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserProfileServices {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Transactional
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

    public UserProfile getUserProfile(Long id) {
        return userProfileRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find profile with id: " + id));
    }

    public Page<UserProfile> getAllUserProfiles(Pageable pageable) {
        return userProfileRepository.findAll(pageable);
    }

    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }

    @Transactional
    public UserProfile updateProfileName(Long profileId, String profileName){
        var profile = userProfileRepository.findById(profileId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find profile with id: " + profileId));

        profile.setProfileName(profileName);
        return userProfileRepository.save(profile);
    }

    @Transactional
    public UserProfile updateEmail(Long profileId, String email){
        var profile = userProfileRepository.findById(profileId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find profile with id: " + profileId));

        profile.setEmail(email);
        return userProfileRepository.save(profile);
    }

    @Transactional
    public UserProfile updateMessageCount(Long profileId, Long messageCount){
        var profile = userProfileRepository.findById(profileId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find profile with id: " + profileId));

        profile.setMessageCount(messageCount);
        return userProfileRepository.save(profile);
    }

    @Transactional
    public UserProfile updateServerCount(Long profileId, Long serverCount){
        var profile = userProfileRepository.findById(profileId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find profile with id: " + profileId));

        profile.setServerCount(serverCount);
        return userProfileRepository.save(profile);
    }
}
