package com.rosendo.app_chat.domain.guild.services;

import com.rosendo.app_chat.domain.guild.enums.GuildType;
import com.rosendo.app_chat.domain.guild.models.Guild;
import com.rosendo.app_chat.domain.guild.repositories.GuildRepository;
import com.rosendo.app_chat.domain.profile.models.UserProfile;
import com.rosendo.app_chat.domain.profile.repositories.UserProfileRepository;
import com.rosendo.app_chat.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuildServices {

    @Autowired
    private GuildRepository guildRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    public Guild getGuildById(Long guildId) {
        return guildRepository.findById(guildId)
                .orElseThrow(() -> new ResourceNotFoundException("Guild not found"));
    }

    public Page<Guild> getAllGuilds(Pageable pageable) {
        return guildRepository.findAll(pageable);
    }

    public Guild createGuild(Guild guild) {
        return guildRepository.save(guild);
    }

    public Guild updateGuild(Guild guild) {
        //TODO: changes
        return guildRepository.save(guild);
    }

    public void deleteGuild(Long guildId) {
        guildRepository.deleteById(guildId);
    }

    @Transactional
    public Guild updateGuildName(Long guildId, String guildName){
        var guild = guildRepository.findById(guildId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find guild with id: " + guildId));

        guild.setGuildName(guildName);
        return guildRepository.save(guild);
    }

    @Transactional
    public Guild updateGuildDescription(Long guildId, String description){
        var guild = guildRepository.findById(guildId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find guild with id: " + guildId));

        guild.setGuildDescription(description);
        return guildRepository.save(guild);
    }

    @Transactional
    public Guild updateGuildType(Long guildId, GuildType guildType){
        var guild = guildRepository.findById(guildId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find guild with id: " + guildId));

        guild.setType(guildType);
        return guildRepository.save(guild);
    }

    @Transactional
    public Guild updateGuildOwner(Long guildId, Long idOwner){
        var guild = guildRepository.findById(guildId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find guild with id: " + guildId));

        var owner = userProfileRepository.findById(idOwner)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find profile with id: " + guildId));

        guild.setOwner(owner);
        return guildRepository.save(guild);
    }
}
