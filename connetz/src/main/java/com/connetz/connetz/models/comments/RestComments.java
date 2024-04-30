package com.connetz.connetz.models.comments;

import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestComments extends AComments {

    protected DocumentReference post_id;
    protected DocumentReference user_id;
}
