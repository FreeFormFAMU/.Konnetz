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

    private DocumentReference user_id;





}
