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
public class APost {
    protected String content;
    protected Timestamp created_at;
    protected String post_id;
    protected Timestamp updated_at;
    protected String user_id;
    protected String comment_text;
    protected Timestamp commented_at;
    protected String comments_id;


}
