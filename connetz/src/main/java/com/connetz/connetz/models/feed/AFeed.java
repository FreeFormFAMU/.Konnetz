package com.connetz.connetz.models.feed;

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
public abstract class AFeed {
    protected Integer comments_count;
    protected Timestamp created_at;
    protected String feed_id;
    protected Integer likes_count;
    protected String user_id;
}
