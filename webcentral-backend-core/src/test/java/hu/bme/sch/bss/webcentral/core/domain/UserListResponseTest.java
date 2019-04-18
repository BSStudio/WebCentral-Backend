package hu.bme.sch.bss.webcentral.core.domain;

import hu.bme.sch.bss.webcentral.core.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

class UserListResponseTest {

    @Test
    void testBuilderWithList() {
        //GIVEN
        List<User> userList = new ArrayList<>();

        User user1 = User.builder().build();
        User user2 = User.builder().build();

        userList.add(user1);
        userList.add(user2);

        //WHEN
        UserListResponse response = UserListResponse.builder()
            .withUsers(userList)
            .build();

        //THEN
        assertEquals(userList, Arrays.asList(response.getUsers()));
    }

    @Test
    void testBuilderWithArray() {
        //GIVEN
        User[] userList = new User[2];

        User user1 = User.builder().build();
        User user2 = User.builder().build();

        userList[0] = user1;
        userList[1] = user2;

        //WHEN
        UserListResponse response = UserListResponse.builder()
            .withUsers(userList)
            .build();

        //THEN
        assertEquals(userList, Arrays.asList(response.getUsers()));
    }
}
