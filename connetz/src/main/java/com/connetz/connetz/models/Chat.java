package com.connetz.connetz.models;
import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.google.protobuf.util.Timestamps;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private String content;
    private String senderId;
    private String sender;
    private String receiverId;
    private String receiver;
    private Timestamp timestamp;
    private boolean isUserSender;


    public void setIsUserSender(boolean isUserSender) {
        this.isUserSender = isUserSender;
    }

    public boolean isUserSender() {
        return isUserSender;
    }
}
