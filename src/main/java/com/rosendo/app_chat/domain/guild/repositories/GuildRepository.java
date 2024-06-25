package com.rosendo.app_chat.domain.guild.repositories;

import com.rosendo.app_chat.domain.guild.models.Guild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildRepository extends JpaRepository<Guild, Long> {}
