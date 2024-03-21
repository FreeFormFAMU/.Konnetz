package com.connetz.connetz.models.post;

import com.google.cloud.Timestamp;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import java.text.ParseException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class APost {
    @DocumentId
    protected @Nullable String id;
    protected String content;
    protected Timestamp created_at;
    protected Timestamp updated_at;

}
