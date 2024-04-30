package com.connetz.connetz.models.comments;

import com.connetz.connetz.models.user.User;
import com.connetz.connetz.models.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments extends AComments{

    protected Post postId;
    protected User userId;//Lets get rid of this later
}
