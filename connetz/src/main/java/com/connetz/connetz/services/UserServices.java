package com.connetz.connetz.services;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import com.connetz.connetz.models.User;
import java.util.*;
import java.util.concurrent.ExecutionException;

// In the User control should have the follow_user, following_user, and skills service .
@Service
public class UserServices {
    private final Firestore firestore;

    //Constructor to get all users
    public UserServices() {this.firestore = FirestoreClient.getFirestore(); }

    //Converting firebase into a class and getting back
    public User documentSnapshotToUser(DocumentSnapshot document)
    {
        if(document.exists())
            return document.toObject(User.class);
        return null;
    }


    //Get all the Users in the list
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = userCollection.get();
        List<User> userList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            User user = documentSnapshotToUser(document);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    // Get all the users by their Id

    public User getUserById(String userId) throws ExecutionException, InterruptedException{
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = userCollection.document(userId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUser(document);
    }

    // Updating the user information
    public WriteResult updateUserInformation(String id, Map<String, Object> updateFields) throws ExecutionException, InterruptedException
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
    public List<User> getAllFollower() throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = userCollection.get();
        List<User> userList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            User user = documentSnapshotToUser(document);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }


    // Get follower by their id
    public User getFollowUserId (String follower_id) throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = userCollection.document(follower_id).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUser(document);
    }

    // Get all the Following users following the follower
    public List<User> getAllFollowing() throws ExecutionException, InterruptedException {
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = userCollection.get();
        List<User> userList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            User user = documentSnapshotToUser(document);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }

    // Get following by their Id
    public User getFollowingUserId(String followers_id) throws ExecutionException, InterruptedException{
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = userCollection.document(followers_id).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUser(document);
    }


    // Create a user
    public String createUser(User users) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<DocumentReference> future = usersCollection.add(users);
        DocumentReference docRef = future.get();
        return docRef.getId() ;
    }


}
