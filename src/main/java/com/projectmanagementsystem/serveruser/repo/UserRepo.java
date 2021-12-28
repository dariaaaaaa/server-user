package com.projectmanagementsystem.serveruser.repo;

import com.projectmanagementsystem.serveruser.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query
    Optional<User> findByUsername(String username);
}
