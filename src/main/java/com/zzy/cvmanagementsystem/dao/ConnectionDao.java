package com.zzy.cvmanagementsystem.dao;

import com.zzy.cvmanagementsystem.model.ConnectionStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Setter
public class ConnectionDao {
    @Id
    private String id;

    private String userId;

    private String followerId;

    private ConnectionStatus connectionStatus;

    private Date updateTime;
}
