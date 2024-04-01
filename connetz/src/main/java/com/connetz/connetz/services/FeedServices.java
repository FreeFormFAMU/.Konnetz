package com.connetz.connetz.services;

import com.connetz.connetz.models.feed.Feed;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
public class FeedServices {
    private final Firestore firestore;

    public FeedServices() {this.firestore = FirestoreClient.getFirestore();}

    public Feed documentSnapshotToFeed (DocumentSnapshot documentSnapshot)
    {
        if(documentSnapshot.exists())
        {
            return documentSnapshot.toObject(Feed.class);
        }

        return null;
    }

    /*public WriteResult addComment(String id, String comment)
    {
        //Increase comments_count by 1

        //Write what the comment is
        List<String> updatingFields = Arrays.asList("comments");

        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : comment.entrySet()) {
            String key = entry.getKey();
            if(!updatingFields.contains(key)) {
                formattedValues.put(key, entry.getValue());

            }
        }


    }*/

    public WriteResult addLike(String id) throws ExecutionException, InterruptedException
    {
        Map<String, Object> incrementValue = new HashMap<>();
        incrementValue.put("likes", FieldValue.increment(1));
        DocumentReference feedDoc = firestore.collection("Feed").document(id);
        ApiFuture<WriteResult> result = feedDoc.update(incrementValue);
        return result.get();
    }


    /*public WriteResult updateFeed(String id, Map<String, Object> updateFields) throws ExecutionException, InterruptedException
    {
        String[] notAllowed = {"createdAt", "feed_id"};

        List<String> notAllowedFields = Arrays.asList(notAllowed);

        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : updateFields.entrySet()) {
            String key = entry.getKey();
            if(!notAllowedFields.contains(key)) {
                formattedValues.put(key, entry.getValue());

            }
        }
        DocumentReference userDoc = firestore.collection("Feed").document(id);
        ApiFuture<WriteResult> result = userDoc.update(formattedValues);
        return result.get();
    }*/
}
