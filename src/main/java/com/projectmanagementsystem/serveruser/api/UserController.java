package com.projectmanagementsystem.serveruser.api;


import com.projectmanagementsystem.serveruser.api.dto.User;
import com.projectmanagementsystem.serveruser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public final class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<com.projectmanagementsystem.serveruser.repo.model.User>> index() {
        final List<com.projectmanagementsystem.serveruser.repo.model.User> users = userService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.projectmanagementsystem.serveruser.repo.model.User> show(@PathVariable long id) {
        try {
            final com.projectmanagementsystem.serveruser.repo.model.User user = userService.fetchById(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(path="/exist", method = RequestMethod.HEAD)
    public ResponseEntity<Void> exist(@RequestParam long id) {
        try{
            userService.ifExistById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody User user) {
        final String username = user.getUsername();
        final String email = user.getEmail();
        final String passwordHash = user.getPasswordHash();
        final String firstName = user.getFirstName();
        final String lastName = user.getLastName();
        final String bio = user.getBio();
        try {
            final long id = userService.create(username, email, passwordHash, firstName, lastName, bio);
            final String location = String.format("/users/%d", id);
            return ResponseEntity.created(URI.create(location)).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody User user) {
        final String email = user.getEmail();
        final String passwordHash = user.getPasswordHash();
        final String firstName = user.getFirstName();
        final String lastName = user.getLastName();
        final String bio = user.getBio();
        try {
            userService.update(id, email, passwordHash, firstName, lastName, bio);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
