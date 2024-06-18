package com.rosendo.app_chat.domain.channel.models;

import com.rosendo.app_chat.domain.channel.enums.ChannelType;
import com.rosendo.app_chat.domain.guild.models.Guild;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="tb_channels")
public class Channel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="channel_name", unique=true, nullable=false, length=32)
    private String channelName;

    @Column(name="channel_description", unique=true, nullable=false, length=180)
    private String channelDescription;

    private ChannelType type;

    @ManyToOne(fetch = FetchType.EAGER)
    private Guild guild;
}
