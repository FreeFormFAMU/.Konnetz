package com.connetz.connetz.models.followers;

import com.connetz.connetz.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Followers extends AFollowers{

    protected User FollowerUserId;
    protected User UserId;


}

