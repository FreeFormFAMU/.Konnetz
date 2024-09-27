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
    protected @Nullable String id;
    protected String username;
    protected String email;
    protected String password;
}
