package com.connetz.connetz.models.user;

import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestUser extends AUser {
    private DocumentReference followers_id;
    private DocumentReference following_id;
    private DocumentReference user_id;
}
