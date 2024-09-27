package com.connetz.connetz.models.user;

import com.connetz.connetz.models.followers.Followers;
import com.connetz.connetz.models.following.Following;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AUser {

    protected User userId;
    protected Followers followerId;
    protected Following FollowingId;
}
