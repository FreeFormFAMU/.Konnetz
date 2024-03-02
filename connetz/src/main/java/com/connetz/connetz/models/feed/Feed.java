package com.connetz.connetz.models.feed;

import com.connetz.connetz.models.post.Post;
import com.google.cloud.Timestamp;
import com.google.firebase.remoteconfig.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feed extends AFeed{

    private Feed feedId;
    private User userId;
    private Post postId;
    public Feed(Integer comments_count, Timestamp created_at, String feed_id, Integer likes_count, String user_id, Feed feedId, User userId, Post postId) {
        super(comments_count, created_at, feed_id, likes_count, user_id);
        this.feedId = feedId;
        this.userId = userId;
        this.postId = postId;
    }
}

