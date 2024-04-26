package com.connetz.connetz.models.user;

import com.google.cloud.firestore.annotation.DocumentId;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AUser {
    @DocumentId
    private @Nullable String id;
    private String username;
    private String email;
    private String password;
}
