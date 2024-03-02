package com.connetz.connetz.models.post;

import com.connetz.connetz.models.User;
import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends APost{

    private Post postId;
    private User userId;

    public Post(String content, Timestamp created_at, String post_id, Timestamp updated_at, String user_id, String comment_text, Timestamp commented_at, String comments_id, Post postId, User userId) {
        super(content, created_at, post_id, updated_at, user_id, comment_text, commented_at, comments_id);
        this.postId = postId;
        this.userId = userId;
    }

}

