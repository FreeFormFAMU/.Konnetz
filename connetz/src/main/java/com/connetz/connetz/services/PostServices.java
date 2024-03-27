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

    private final Firestore firestore;

    public PostServices()
    {
        this.firestore = FirestoreClient.getFirestore();
    }

    public Post documentSnapshotToPost (DocumentSnapshot documentSnapshot)
    {
        if(documentSnapshot.exists())
        {
            return documentSnapshot.toObject(Post.class);
        }

        return null;
    }

    public List<Post> getAllPost() throws ExecutionException, InterruptedException
    {
        CollectionReference postCollection = firestore.collection("Post");

        ApiFuture<QuerySnapshot> future = postCollection.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Post> postList = new ArrayList<>();

        for(DocumentSnapshot document: documents)
        {
            postList.add(documentSnapshotToPost(document));
        }

        return postList;
    }

    public Post getPostById (String postId) throws ExecutionException, InterruptedException {

        DocumentReference postRef = firestore.collection("Post").document(postId);

        ApiFuture<DocumentSnapshot> future = postRef.get();

        DocumentSnapshot document = future.get();

        return documentSnapshotToPost(document);
    }

    public String createPost(Post post) throws ExecutionException, InterruptedException
    {
        CollectionReference PostCollection = firestore.collection("Posts");

        ApiFuture<DocumentReference> future = PostCollection.add(post);

        DocumentReference docRef = future.get();

        return docRef.getId();
    }

    public WriteResult removePost(String id) throws ExecutionException, InterruptedException
    {
        DocumentReference postRef = firestore.collection("Posts").document(id);

        ApiFuture<WriteResult> result = postRef.delete();

        return result.get();
    }

    public WriteResult updatePost(String id, Map<String, Object> updateFields) throws ExecutionException, InterruptedException
    {
        String[] allowed = {"content", "updated_at"};

        List<String> AllowedFields = Arrays.asList(allowed);

        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : updateFields.entrySet()) {
            String key = entry.getKey();
            if(AllowedFields.contains(key)) {
                formattedValues.put(key, entry.getValue());
            }

        }
        DocumentReference userDoc = firestore.collection("Post").document(id);
        ApiFuture<WriteResult> result = userDoc.update(formattedValues);
        return result.get();
    }



}
