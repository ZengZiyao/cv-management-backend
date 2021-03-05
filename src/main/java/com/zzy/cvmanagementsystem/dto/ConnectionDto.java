package com.zzy.cvmanagementsystem.dto;

import com.zzy.cvmanagementsystem.model.ConnectionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionDto {
    private String id;

    private String username;

    private String followerUsername;

    private ConnectionStatus connectionStatus;

    private Date updateTime;

//    public ConnectionDto(String id, String username, String followerUsername, int connectionStatus, Date updateTime) {
//        this.id = id;
//        this.username = username;
//        this.followerUsername = followerUsername;
//        this.connectionStatus = connectionStatus;
//        this.updateTime = updateTime;
//    }

}
