package com.connetz.connetz.models.post;

import com.connetz.connetz.models.User;
import com.google.firebase.database.annotations.Nullable;
import com.connetz.connetz.models.post.APost;
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



}

