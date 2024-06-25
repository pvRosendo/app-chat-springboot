package com.rosendo.app_chat.domain.channel.repositories;

import com.rosendo.app_chat.domain.channel.models.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
