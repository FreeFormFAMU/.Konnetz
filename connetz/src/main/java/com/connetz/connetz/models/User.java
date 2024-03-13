package com.connetz.connetz.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import jakarta.annotation.Nullable;
import java.text.ParseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @DocumentId
    private @Nullable String userId;
    private String username;
    private String email;
    private String password;
    private Timestamp created_at;
    private @Nullable Timestamp updated_at;
    private String followersId;
    private String followingId;
}
