package com.connetz.connetz.models.comments;

import com.connetz.connetz.util.Debug.TimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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

    @JsonDeserialize(using = TimestampDeserializer.class)
    protected Timestamp commented_at;


}
