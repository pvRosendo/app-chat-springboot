package com.rosendo.app_chat.controllers.guild;

import com.rosendo.app_chat.domain.guild.enums.GuildType;
import com.rosendo.app_chat.domain.guild.models.Guild;
import com.rosendo.app_chat.domain.guild.services.GuildServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/guilds")
public class GuildControllers {

    @Autowired
    private GuildServices guildServices;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guild> getGuildById(@PathVariable(value="id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(guildServices.getGuildById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Guild>> getAllGuildsById(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(guildServices.getAllGuilds(pageable));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guild> createGuild(@RequestBody Guild guild) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guildServices.createGuild(guild));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteGuildById(@PathVariable(value="id") Long id) {
        guildServices.deleteGuild(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guild> updateGuildName(@PathVariable(value="id") Long id, @RequestBody String guildName) {
        return ResponseEntity.status(HttpStatus.OK).body(guildServices.updateGuildName(id, guildName));
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guild> updateGuildDescription(@PathVariable(value="id") Long id, @RequestBody String guildDescription) {
        return ResponseEntity.status(HttpStatus.OK).body(guildServices.updateGuildDescription(id, guildDescription));
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guild> updateGuildType(@PathVariable(value="id") Long id, @RequestBody GuildType guildType) {
        return ResponseEntity.status(HttpStatus.OK).body(guildServices.updateGuildType(id, guildType));
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guild> updateGuildOwner(@PathVariable(value="id") Long id, @RequestBody Long guildOwner) {
        return ResponseEntity.status(HttpStatus.OK).body(guildServices.updateGuildOwner(id, guildOwner));
    }
}