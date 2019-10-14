package hu.bme.sch.bss.webcentral.core.domain;

import hu.bme.sch.bss.webcentral.core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

final class UserListResponseTest {

    private User user1;
    private User user2;

    private UserListResponse underTest;

    @BeforeEach
    void init() {
        user1 = User.builder().build();
        user2 = User.builder().build();
    }

    @Test
    void testBuilderWithList() {
        //GIVEN
        final List<User> userList = List.of(user1, user2);

        //WHEN
        underTest = UserListResponse.builder()
                .withUsers(userList)
                .build();

        //THEN
        assertArrayEquals(userList.toArray(), underTest.getUsers());
    }

    @Test
    void testBuilderWithArray() {
        //GIVEN
        final User[] userList = new User[2];
        userList[0] = user1;
        userList[1] = user2;

        //WHEN
        underTest = UserListResponse.builder()
                .withUsers(userList)
                .build();

        //THEN
        assertArrayEquals(userList, underTest.getUsers());
    }

}
