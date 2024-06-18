package com.rosendo.app_chat.domain.profile.repositories;

import com.rosendo.app_chat.domain.profile.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> { }
