package com.connetz.connetz.models.post;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.google.firebase.database.annotations.Nullable;

import java.sql.Time;
import java.text.ParseException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class APost {
    @DocumentId
    protected @Nullable String id;


    protected String content;
    protected Timestamp created_at;
    protected @Nullable Timestamp updated_at;

    public void setCreatedAt(String createdAt) throws ParseException
    {
        this.created_at = Timestamp.fromProto(Timestamps.parse(createdAt));
    }

    public void setUpdatedAt(String updatedAt) throws ParseException
    {
        this.updated_at = Timestamp.fromProto(Timestamps.parse(updatedAt));
    }

    public Timestamp getCreatedAt(String createdAt) throws ParseException
    {
        return this.created_at;
    }

    public Timestamp getUpdatedAt(String updatedAt) throws ParseException
    {
        return this.updated_at;
    }

    public Timestamp getTimestamp()
    {
        return this.updated_at;
    }

}
