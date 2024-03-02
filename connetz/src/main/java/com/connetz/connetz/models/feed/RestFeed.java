package com.connetz.connetz.models.feed;


import com.connetz.connetz.models.post.Post;
import com.connetz.connetz.util.Utility;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.remoteconfig.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Document Referenece's only go in here
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestFeed extends AFeed {

    private DocumentReference feedId;
    private DocumentReference userId;
    private DocumentReference postId;

    public RestFeed(Integer comments_count, Timestamp created_at, String feed_id, Integer likes_count, String user_id, DocumentReference feedId, DocumentReference userId, DocumentReference postId) {
        super(comments_count, created_at, feed_id, likes_count, user_id);
        this.feedId = feedId;
        this.userId = userId;
        this.postId = postId;
    }

    public void setFeedIDBy(String feedId){
        this.postId = Utility.retrieveDocumentReference("Feed", feedId);
    }

    public void setUserIDBy(String userId){
        this.userId = Utility.retrieveDocumentReference("User", userId);
    }

    public void setPostIDBy(String postId){
        this.userId = Utility.retrieveDocumentReference("Post", postId);
    }

}
