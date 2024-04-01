package com.connetz.connetz.models.following;

import com.connetz.connetz.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Following extends AFollowing{

    protected User FollowerUserId;
    protected User UserId;
}

