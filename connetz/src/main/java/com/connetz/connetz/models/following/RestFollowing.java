package com.connetz.connetz.models.following;

import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestFollowing extends AFollowing{
    protected DocumentReference follower_user_id;
    protected DocumentReference user_id;
}
