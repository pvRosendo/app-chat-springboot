package com.rosendo.app_chat.domain.user.repositories;

import com.rosendo.app_chat.domain.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    User findByUsername(@Param("userName") String userName);
}
