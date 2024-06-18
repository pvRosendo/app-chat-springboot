package com.rosendo.app_chat.domain.profile.models;

import com.rosendo.app_chat.domain.guild.models.Guild;
import com.rosendo.app_chat.domain.user.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tb_user_profiles")
public class UserProfile implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(name="profile_name", unique=true, nullable=false, length=32)
    private String profileName;

    @Email
    @Column(name="email", unique=true, nullable=false)
    private String email;

    @Column(name="count_sent_messages")
    private Long messageCount;

    @Column(name="count_servers_owner")
    private Long serverCount;

    @Column(name="creation_date")
    private Date creationDate;

    @OneToMany(mappedBy = "owner")
    private List<Guild> guildList;

    @OneToOne
    private User user;

    public UserProfile() {}

    public UserProfile(
            String profileName,
            String email,
            Long messageCount,
            Long serverCount,
            Date creationDate,
            User user
    ) {
        this.profileName = profileName;
        this.email = email;
        this.messageCount = messageCount;
        this.serverCount = serverCount;
        this.creationDate = creationDate;
        this.user = user;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Long messageCount) {
        this.messageCount = messageCount;
    }

    public Long getServerCount() {
        return serverCount;
    }

    public void setServerCount(Long serverCount) {
        this.serverCount = serverCount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Guild> getGuildList() {
        return guildList;
    }

    public void setGuildList(List<Guild> guildList) {
        this.guildList = guildList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
