package com.brac.its.libraryManagement.model;

import javax.persistence.*;

@Entity
@Table(name = "youtube_channel")
public class YoutubeChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "channel_name")
    private String channelName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
