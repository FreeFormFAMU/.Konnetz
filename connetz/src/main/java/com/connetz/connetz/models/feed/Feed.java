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

    protected User userId;
}

