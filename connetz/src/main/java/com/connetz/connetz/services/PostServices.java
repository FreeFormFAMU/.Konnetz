package com.connetz.connetz.services;

import com.connetz.connetz.models.post.Post;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public List<Post> getPostById (String postId) throws ExecutionException, InterruptedException {
        CollectionReference postCollection = firestore.collection("Post");
        ApiFuture<DocumentSnapshot> future = postCollection.document(postId).get();
        DocumentSnapshot document = future.get();
        return (List<Post>) documentSnapshotToUserService(document);
    }

    public WriteResult updatePost(String id, Map<String, Object> updateFields) throws ExecutionException, InterruptedException
    {
        String[] notAllowed = {"createdAt", "post_id"};

        List<String> notAllowedFields = Arrays.asList(notAllowed);

        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : updateFields.entrySet()) {
            String key = entry.getKey();
            if(!notAllowedFields.contains(key)) {
                formattedValues.put(key, entry.getValue());

            }
        }
        DocumentReference userDoc = firestore.collection("Tasks").document(id);
        ApiFuture<WriteResult> result = userDoc.update(formattedValues);
        return result.get();
    }


}
