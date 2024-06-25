package com.rosendo.app_chat.controllers.channel;

import com.rosendo.app_chat.domain.channel.enums.ChannelType;
import com.rosendo.app_chat.domain.channel.models.Channel;
import com.rosendo.app_chat.domain.channel.services.ChannelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/channels")
public class ChannelControllers {

    @Autowired
    private ChannelServices channelServices;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Channel> getChannelById(@PathVariable(value="id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(channelServices.getChannelById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Channel>> getAllChannelsById(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(channelServices.getAllChannels(pageable));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Channel> createChannel(@RequestBody Channel Channel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(channelServices.createChannel(Channel));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteChannelById(@PathVariable(value="id") Long id) {
        channelServices.deleteChannel(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Channel> updateChannelName(@PathVariable(value="id") Long id, @RequestBody String ChannelName) {
        return ResponseEntity.status(HttpStatus.OK).body(channelServices.updateChannelName(id, ChannelName));
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Channel> updateChannelDescription(@PathVariable(value="id") Long id, @RequestBody String ChannelDescription) {
        return ResponseEntity.status(HttpStatus.OK).body(channelServices.updateChannelDescription(id, ChannelDescription));
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Channel> updateChannelType(@PathVariable(value="id") Long id, @RequestBody ChannelType ChannelType) {
        return ResponseEntity.status(HttpStatus.OK).body(channelServices.updateChannelType(id, ChannelType));
    }
}
