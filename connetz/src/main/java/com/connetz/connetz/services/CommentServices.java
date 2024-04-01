package com.connetz.connetz.services;

import com.connetz.connetz.models.comments.Comments;
import com.connetz.connetz.models.post.Post;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CommentServices {

    private final Firestore firestore;

    public CommentServices(){this.firestore = FirestoreClient.getFirestore();}

    public Comments documentSnapshotToComment (DocumentSnapshot documentSnapshot)
    {
        if(documentSnapshot.exists())
        {
            return documentSnapshot.toObject(Comments.class);
        }

        return null;
    }

    public List<Comments> getAllComments() throws ExecutionException, InterruptedException
    {
        CollectionReference commentCollection = firestore.collection("Comments");

        ApiFuture<QuerySnapshot> future = commentCollection.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Comments> commentList = new ArrayList<>();

        for(DocumentSnapshot document: documents)
        {
            commentList.add(documentSnapshotToComment(document));
        }

        return commentList;
    }


}
