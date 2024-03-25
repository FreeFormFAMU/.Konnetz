package com.connetz.connetz.models.post;

import com.connetz.connetz.models.User;
import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends APost{

    private Post postId;
    private User userId;

    public Timestamp getTimestamp(){//Need a getter to serialize for update method
        return this.created_at;
    }

    public Timestamp getTimestamp()//Need to serialize timestamp
    {
        return this.updated_at;
    }

}

