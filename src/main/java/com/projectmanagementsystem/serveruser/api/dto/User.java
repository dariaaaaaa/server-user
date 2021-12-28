package com.projectmanagementsystem.serveruser.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class User {
    private String username;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String bio;

}
