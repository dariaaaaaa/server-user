package com.projectmanagementsystem.serveruser.service;

import com.projectmanagementsystem.serveruser.repo.UserRepo;
import com.projectmanagementsystem.serveruser.repo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UserService {
    private final UserRepo userRepo;

    public List<User> fetchAll() {
        return userRepo.findAll();
    }

    public User fetchById(long id) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        else return maybeUser.get();
    }

    public void ifExistById(long id) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
    }

    public Long create(String username, String email, String passwordHash, String firstName, String lastName, String bio) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findByUsername(username);
        if (maybeUser.isPresent()) throw new IllegalArgumentException("Username already taken found");

        final User user = new User(username, email, passwordHash, firstName, lastName, bio);
        final User savedUser = userRepo.save(user);
        return savedUser.getId();
    }

    public void update(long id, String email, String passwordHash, String firstName, String lastName, String bio) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");

        final User user = maybeUser.get();
        if (email != null && !email.isBlank()) user.setEmail(email);
        if (passwordHash != null && !passwordHash.isBlank()) user.setPasswordHash(passwordHash);
        if (firstName != null && !firstName.isBlank()) user.setFirstName(firstName);
        if (lastName != null && !lastName.isBlank()) user.setLastName(lastName);
        if (bio != null && !bio.isBlank()) user.setBio(bio);
        userRepo.save(user);
    }

    public void delete(long id){
        userRepo.deleteById(id);
    }
}
