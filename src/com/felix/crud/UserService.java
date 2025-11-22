package com.felix.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    // CREATE
    public User createUser(String name, String email) {
        User user = new User(nextId++, name, email);
        users.add(user);
        return user;
    }

    // READ - listar todos
    public List<User> getAllUsers() {
        return users;
    }

    // READ - buscar por id
    public User getUserById(int id) {
        Optional<User> optionalUser = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
        return optionalUser.orElse(null);
    }

    // UPDATE
    public boolean updateUser(int id, String newName, String newEmail) {
        User user = getUserById(id);
        if (user == null) {
            return false;
        }
        user.setName(newName);
        user.setEmail(newEmail);
        return true;
    }

    // DELETE
    public boolean deleteUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }
}
