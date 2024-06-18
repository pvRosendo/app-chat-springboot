package com.rosendo.app_chat.domain.guild.models;

import com.rosendo.app_chat.domain.channel.models.Channel;
import com.rosendo.app_chat.domain.guild.enums.GuildType;
import com.rosendo.app_chat.domain.profile.models.UserProfile;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tb_guilds")
public class Guild implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID guildId;

    @Column(name="guild_name", unique=true, nullable=false, length=32)
    private String guildName;

    @Column(name="guild_description", unique=true, nullable=false, length=180)
    private String guildDescription;

    private GuildType type;

    @OneToMany(mappedBy = "guild")
    private List<Channel> channels;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserProfile owner;
}
