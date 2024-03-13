package com.connetz.connetz.models.messages;

import com.connetz.connetz.models.User;
import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Messages extends AMessages {

    private User senderId;
    private User receiverId;
    public Messages(String messageId, String content, Timestamp timestamp, User senderId, User receiverId) {
        super(messageId, content, timestamp);
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

}

