package com.connetz.connetz.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skills {
    @DocumentId
    private @Nullable String skills_Id;
    private Timestamp created_at;
    private String skills_description;
    private String skills_name;
    private @Nullable Timestamp updated_at;
    private String user_Id;
}
