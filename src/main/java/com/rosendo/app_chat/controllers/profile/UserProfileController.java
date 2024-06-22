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
@RequestMapping(name="/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileServices profileServices;
    

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserProfile>> getUserProfiles(@PageableDefault(size=10, sort = {"creationDate"}) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(profileServices.getAllUserProfiles(pageable));
    }

    @GetMapping(name = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserProfile(@PathVariable(value = "id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(profileServices.getUserProfile(id));
    }

    @DeleteMapping(name = "/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") Long profileId) {
        profileServices.deleteUserProfile(profileId);
        return ResponseEntity.noContent().build();
    }
}
