package com.connetz.connetz.services;
import com.connetz.connetz.models.Chat;
import com.connetz.connetz.models.User;
import com.connetz.connetz.models.messages.Messages;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class MessageServices {
    private Firestore firestore;

    public MessageServices () {this.firestore= FirestoreClient.getFirestore(); }

    public Messages documentSnapshotToMessage(DocumentSnapshot document) throws ExecutionException, InterruptedException
    {
        if(document.exists())
        {
            User sender = null, reciever = null;

            DocumentReference senderRef = (DocumentReference)  document.get("senderId");

            if(senderRef != null)
            {
                DocumentSnapshot userSnapshot = senderRef.get().get();
                if(userSnapshot.exists())
                {
                    sender = userSnapshot.toObject(User.class);
                }
            }

            DocumentReference receiverRef = (DocumentReference) document.get("receiverId");
            if(senderRef != null)
            {
                DocumentSnapshot userSnapshot = receiverRef.get().get();
                if(userSnapshot.exists())
                {
                    reciever = userSnapshot.toObject(User.class);
                }
            }


            //return new Messages( document.getId(), document.getString("message_content"), document.getTimestamp("timestamp"), sender, reciever);
            return  new Messages(document.getId(), document.getString("message_content"), document.getTimestamp("timestamp"), sender, reciever );


        }
        return  null;

    }

    public WriteResult updateMessages(String id, Map<String, Object> updateFields) throws ExecutionException, InterruptedException
    {
        String[] notAllowed = {"reciever_id", "message_id", "sender_id"};

        List<String> notAllowedFields = Arrays.asList(notAllowed);

        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : updateFields.entrySet()) {
            String key = entry.getKey();
            if(!notAllowedFields.contains(key)) {
                formattedValues.put(key, entry.getValue());

            }
        }
        DocumentReference userDoc = firestore.collection("Messages").document(id);
        ApiFuture<WriteResult> result = userDoc.update(formattedValues);
        return result.get();
    }

    public List<Chat> getMessageChats(String userId) throws ExecutionException, InterruptedException
    {
        Query querySender = firestore.collection("Messages").whereEqualTo("senderId", userId);
        Query queryReceiver = firestore.collection("Messages").whereEqualTo("recieverId", userId);

        Map<String, Chat> latestChatsMap = new HashMap<>();
        ApiFuture<QuerySnapshot> senderFuture = querySender.get();
        ApiFuture<QuerySnapshot> recieverFuture = queryReceiver.get();

        List<Chat> chats = new ArrayList<>();

        //Process sender chats
        processChats(senderFuture.get(), latestChatsMap, userId);

        processChats(recieverFuture.get(), latestChatsMap, userId);

        chats.addAll(latestChatsMap.values());

        return chats;
    }

    private void processChats(QuerySnapshot querySnapshot, Map<String, Chat> latestChatsMap, String userId) throws ExecutionException, InterruptedException
    {
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            DocumentReference senderRef = (DocumentReference) document.get("senderId");
            User sender = senderRef.get().get().toObject(User.class);

            DocumentReference receiverRef = (DocumentReference) document.get("senderId");
            User receiver = receiverRef.get().get().toObject(User.class);

            Chat chat = new Chat(document.getString("content"),
                    sender.getUserId(), sender.getUsername(),
                    receiver.getUserId(), receiver.getUsername(),
                    document.getTimestamp("timestamp"),
                    userId == sender.getUserId()
            );
            String otherUserId = chat.isUserSender() ? chat.getReceiverId() : chat.getSenderId();

            if (!latestChatsMap.containsKey(otherUserId) || chat.getTimestamp().compareTo(latestChatsMap.get(otherUserId).getTimestamp()) > 0) {
                chat.setIsUserSender(userId.equals(chat.getSenderId()));
                latestChatsMap.put(otherUserId, chat);
            }
        }
    }

    public  Messages getMessageById(String user_id) throws ExecutionException, InterruptedException{
        CollectionReference userCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = userCollection.document(user_id).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToMessage(document);
    }

}
