package io.rently.userservice._service;

import io.rently.userservice.dto.ResponseContent;
import io.rently.userservice.dto.User;
import io.rently.userservice.error.NotFoundException;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static final List<User> users = new ArrayList<>(Arrays.asList(
            new User.Builder("1").setUsername("branlix2000").setFullName("Noah Greff").setEmail("something@gmail.com").setPhone("+31 06 41 53 14").build(),
            new User.Builder("2").setUsername("than00ber").setFullName("Chandler Greff").setEmail("something.other@yahoo.com").build(),
            new User.Builder("3").setUsername("chew kok").setFullName("Chew Kok").setEmail("chew.kok@hotmail.com").build()
    ));

    public static ResponseContent getUsers() { // add query param
        return new ResponseContent.Builder().setData(users).build();
    }

    public static ResponseContent getUserById(String id) { // add uuid check
        for (User user: users) {
            if (Objects.equals(user.getId(), id)) {
                return new ResponseContent.Builder().setData(user).build();
            }
        }
        throw new NotFoundException.UserByIdNotFound(id);
    }

    public static ResponseContent addUser(User user) { // check user id existence
        users.add(user);
        return new ResponseContent.Builder().setMessage("Successfully added user with ID " + user.getId()).build();
    }

    public static ResponseContent deleteUserById(String id) { // add uuid check
        for (User user: users.stream().toList()) {
            if (Objects.equals(user.getId(), id)) {
                users.remove(user);
                return new ResponseContent.Builder().setMessage("Successfully removed user with ID " + id).build();
            }
        }
        throw new NotFoundException.UserByIdNotFound(id);
    }

    public static ResponseContent updateUserById(String id) { // add uuid check, check info
        for (User user: users.stream().toList()) {
            if (Objects.equals(user.getId(), id)) {
                users.remove(user);
                users.add(new User.Builder(user.getId()).build());
                return new ResponseContent.Builder().setMessage("Successfully updated user with ID " + id).build();
            }
        }
        throw new NotFoundException.UserByIdNotFound(id);
    }
}