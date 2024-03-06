package com.connetz.connetz.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import com.google.firebase.remoteconfig.User;

import java.util.*;
import java.util.concurrent.ExecutionException;

// In the User control should have the follow_user, following_user, and skills service .
@Service
public class UserServices {
    private Firestore firestore;

    //Constructor to get all users
    public UserServices() {this.firestore = FirestoreClient.getFirestore(); }

    //Converting firebase into a class and getting back
    public User documentSnapshotToUserService(DocumentSnapshot document)
    {
        if(document.exists())
            return document.toObject(User.class);
        return null;
    }


    //Get all the Users in the list
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("User");
        ApiFuture<QuerySnapshot> future = userCollection.get();
        List<User> userList = new ArrayList<>();
        for (DocumentSnapshot docment : future.get().getDocuments()) {
            User user = documentSnapshotToUserService(docment);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    public User getUserById(String userId) throws ExecutionException, InterruptedException{
        CollectionReference userCollection = firestore.collection("User");
        ApiFuture<DocumentSnapshot> future = userCollection.document(userId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUserService(document);
    }

    public WriteResult updateUserinformation(String id, Map<String, Object> updateFields) throws ExecutionException, InterruptedException
    {
        String[] notAllowed = {"created_at", "user_id", "username"};

        List<String> notAllowedFields = Arrays.asList(notAllowed);

        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : updateFields.entrySet()) {
            String key = entry.getKey();
            if(!notAllowedFields.contains(key)) {
                formattedValues.put(key, entry.getValue());

            }
        }
        DocumentReference userDoc = firestore.collection("Users").document(id);
        ApiFuture<WriteResult> result = userDoc.update(formattedValues);
        return result.get();
    }

    // Getting all the followers that Follow this user
    public void getFollowUser (String follower_id) throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("User");
    }

    // Get all the Following users following the follower

    public void getFollowinguser(String followers_id) throws ExecutionException, InterruptedException{
        CollectionReference userCollection = firestore.collection("User");

    }


}
