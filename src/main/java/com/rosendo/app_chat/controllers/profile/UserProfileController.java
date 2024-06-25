package com.rosendo.app_chat.controllers.profile;

import com.rosendo.app_chat.domain.profile.models.UserProfile;
import com.rosendo.app_chat.domain.profile.services.UserProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileServices profileServices;

    @Autowired
    private UserProfileServices userProfileServices;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserProfile>> getUserProfiles(@PageableDefault(size=10, sort = {"creationDate"}) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(profileServices.getAllUserProfiles(pageable));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserProfile(@PathVariable(value = "id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(profileServices.getUserProfile(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") Long profileId) {
        profileServices.deleteUserProfile(profileId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfile> updateProfileName(@PathVariable(value="id") Long id, @RequestBody String profileName) {
        return ResponseEntity.status(HttpStatus.OK).body(userProfileServices.updateProfileName(id, profileName));
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfile> updateEmail(@PathVariable(value="id") Long id, @RequestBody String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userProfileServices.updateEmail(id, email));
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfile> updateMessageCount(@PathVariable(value="id") Long id, @RequestBody Long messageCount) {
        return ResponseEntity.status(HttpStatus.OK).body(userProfileServices.updateMessageCount(id, messageCount));
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfile> updateServerCount(@PathVariable(value="id") Long id, @RequestBody Long serverCount) {
        return ResponseEntity.status(HttpStatus.OK).body(userProfileServices.updateServerCount(id, serverCount));
    }
}
