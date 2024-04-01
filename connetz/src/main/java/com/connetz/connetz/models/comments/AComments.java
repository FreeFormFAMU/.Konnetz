package com.connetz.connetz.models.comments;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AComments {

    @DocumentId
    protected @Nullable String id;
    protected String comment_text;

    protected Timestamp commented_at;


}
