package com.rosendo.app_chat.domain.channel.services;

import com.rosendo.app_chat.domain.channel.enums.ChannelType;
import com.rosendo.app_chat.domain.channel.models.Channel;
import com.rosendo.app_chat.domain.channel.repositories.ChannelRepository;
import com.rosendo.app_chat.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChannelServices {

    @Autowired
    private ChannelRepository channelRepository;

    public Channel getChannelById(Long channelId) {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new ResourceNotFoundException("Channel not found"));
    }

    public Page<Channel> getAllChannels(Pageable pageable) {
        return channelRepository.findAll(pageable);
    }

    public Channel createChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public void deleteChannel(Long channelId) {
        channelRepository.deleteById(channelId);
    }

    @Transactional
    public Channel updateChannelName(Long channelId, String channelName){
        var channel = channelRepository.findById(channelId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find channel with id: " + channelId));

        channel.setChannelName(channelName);
        return channelRepository.save(channel);
    }

    @Transactional
    public Channel updateChannelDescription(Long channelId, String description){
        var channel = channelRepository.findById(channelId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find channel with id: " + channelId));

        channel.setChannelDescription(description);
        return channelRepository.save(channel);
    }

    @Transactional
    public Channel updateChannelType(Long channelId, ChannelType channelType){
        var channel = channelRepository.findById(channelId)
                .orElseThrow(()-> new ResourceNotFoundException("Don't find channel with id: " + channelId));

        channel.setType(channelType);
        return channelRepository.save(channel);
    }
}
