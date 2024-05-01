package com.connetz.connetz.services;

import com.connetz.connetz.models.post.Post;
import com.connetz.connetz.models.user.User;
import com.connetz.connetz.util.Utility;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class PostServices {

    private final Firestore firestore;
    protected final Log logger = LogFactory.getLog(this.getClass());

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

    @Nullable
    private List<Post> getPostList(Query query) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Post> postList = documentSnapshots.size() == 0 ? null : new ArrayList<>();

        for(DocumentSnapshot document : documentSnapshots)
        {
            postList.add(documentSnapshotToPost(document));
        }

        return postList;
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
        CollectionReference PostCollection = firestore.collection("Post");

        ApiFuture<DocumentReference> future = PostCollection.add(post);

        DocumentReference docRef = future.get();

        return docRef.getId();
    }

    public List<Post> getPostsByUser (String userId) throws ExecutionException, InterruptedException
    {
        //DocumentReference userRef = Utility.retrieveDocumentReference("Posts", follower_id);

        Query query = firestore.collection("Post").whereEqualTo("user_id", userId);

        return getPostList(query);
    }

    public WriteResult removePost(String id) throws ExecutionException, InterruptedException
    {
        DocumentReference postRef = firestore.collection("Post").document(id);

        ApiFuture<WriteResult> result = postRef.delete();

        return result.get();
    }

    public WriteResult updatePost(String id, Map<String, Object> updateFields) throws ExecutionException, InterruptedException
    {
        String[] allowed = {"content", "title"};

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

    public List<Post> getPostsByCategory (String slug) throws ExecutionException, InterruptedException
    {
        //DocumentReference userRef = Utility.retrieveDocumentReference("Posts", follower_id);

        Query query = firestore.collection("Post").whereEqualTo("slug", slug);

        return getPostList(query);
    }



}
