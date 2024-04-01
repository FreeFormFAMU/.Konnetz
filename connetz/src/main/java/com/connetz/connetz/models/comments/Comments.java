package com.connetz.connetz.models.comments;

import com.connetz.connetz.models.User;
import com.connetz.connetz.models.post.Post;
import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments extends AComments{

    protected Post postId;
    protected User userId;
    protected Comments commentsId;//Lets get rid of this later
}
