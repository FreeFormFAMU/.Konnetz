package com.connetz.connetz.models.post;


import com.connetz.connetz.models.User;
import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post extends  APost{

    protected User userId;

    protected Post postId;


}

