package com.connetz.connetz.services;

import com.connetz.connetz.models.post.Post;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PostServices {
    private Firestore firestore;

    public PostServices() {this.firestore = FirestoreClient.getFirestore(); }

    public Post documentSnapshotToUserService(DocumentSnapshot document)
    {
        if(document.exists())
            return document.toObject(Post.class);
        return null;
    }

    //Get all the Post
    public List<Post> getAllPost() throws ExecutionException, InterruptedException {
        CollectionReference postCollection = firestore.collection("User");
        ApiFuture<QuerySnapshot> future = postCollection.get();
        List<Post> userList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            Post post = documentSnapshotToUserService(document);
            if (post != null) {
                userList.add(post);
            }
        }
        return userList;
    }

    public List<Post> getPostById () throws ExecutionException, InterruptedException {
        CollectionReference postCollection = firestore.collection("Post");
        ApiFuture<DocumentSnapshot> future = postCollection.document(postId).get();
        DocumentSnapshot document = future.get();
        return (List<Post>) documentSnapshotToUserService(document);
    }


}
