package com.connetz.connetz.models.messages;


import com.connetz.connetz.util.Utility;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.annotations.Nullable;

// Document Referenece's only go in here
public class RestMessage extends AMessages{
    private DocumentReference senderID;
    private DocumentReference receiverID;

    public RestMessage (@Nullable String messageId, String content, Timestamp timestamp, DocumentReference senderId, DocumentReference receiverId) {
        super(messageId, content, timestamp);
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    // Creating Setters and Getters to Get access to firebase

    public void setSenderIDBy(String senderID){
        this.senderID = Utility.retrieveDocumentReference("Sender", senderID);
    }

    public void setReceiverID(String receiverID){
        this.receiverID = Utility.retrieveDocumentReference("Receiver", receiverID);
    }
}
