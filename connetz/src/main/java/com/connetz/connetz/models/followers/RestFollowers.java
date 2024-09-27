package com.connetz.connetz.models.followers;

import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestFollowers extends AFollowers{

    protected DocumentReference follower_user_id;
    protected DocumentReference user_id;
}
