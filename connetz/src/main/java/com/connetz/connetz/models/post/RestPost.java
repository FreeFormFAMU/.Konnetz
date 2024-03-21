package com.connetz.connetz.models.post;


import com.connetz.connetz.util.Utility;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Document Referenece's only go in here
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestPost extends APost{
    private DocumentReference post_id;
    private DocumentReference user_id;




}
