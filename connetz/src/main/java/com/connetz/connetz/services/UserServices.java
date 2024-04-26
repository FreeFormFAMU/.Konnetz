package com.connetz.connetz.services;
import com.connetz.connetz.util.Utility;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import org.springframework.stereotype.Service;
import com.connetz.connetz.models.user.User;
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

    @Nullable
    private List<User> getUserList(Query query) throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<User> userList = documentSnapshots.size() == 0 ? null : new ArrayList<>();

        for(DocumentSnapshot document : documentSnapshots)
        {
             userList.add(documentSnapshotToUser(document));
        }

        return userList;
    }

    // Updating the user information
    public WriteResult updateUserInformation(String id, Map<String, String> updateFields) throws ExecutionException, InterruptedException
    {
        String[] notAllowed = {"created_at", "user_id", "username"};

        List<String> notAllowedFields = Arrays.asList(notAllowed);

        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, String> entry : updateFields.entrySet()) {
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
    public List<User> getUserByFollower (String follower_id) throws ExecutionException, InterruptedException {
        //CollectionReference userCollection = firestore.collection("Users");
        DocumentReference userRef = Utility.retrieveDocumentReference("Users", follower_id);

        Query query = firestore.collection("Users").whereEqualTo("followers_id", follower_id);

        return getUserList(query);
    }

    // Need to complete Followers class for this to work
    public List<User> getFollowersByUser() throws ExecutionException, InterruptedException {
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
