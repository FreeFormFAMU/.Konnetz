package com.connetz.connetz.models.post;


import com.connetz.connetz.util.Utility;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Document Referenece's only go in here
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestPost extends APost{
    private DocumentReference postId;
    private DocumentReference userId;

    public RestPost(String content, Timestamp created_at, String post_id, Timestamp updated_at, String user_id, String comment_text, Timestamp commented_at, String comments_id, DocumentReference postId, DocumentReference userId) {
        super(content, created_at, post_id, updated_at, user_id, comment_text, commented_at, comments_id);
        this.postId = postId;
        this.userId = userId;
    }
    public void setPostIDBy(String postId){
        this.postId = Utility.retrieveDocumentReference("Post", postId);
    }

    public void setUserIDBy(String userId){
        this.userId = Utility.retrieveDocumentReference("User", userId);
    }

}
