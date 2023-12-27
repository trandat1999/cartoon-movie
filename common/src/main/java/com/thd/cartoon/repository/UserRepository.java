package com.thd.cartoon.repository;

import com.thd.cartoon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndEmail(String username, String email);
    long countUserByEmail(String email);
    long countUserByUsername(String username);
    @Query(value = "select count(u.id) from User u where (:id is null or u.id <> :id) " +
            " and (:username is null or u.username = :username) " +
            " and (:email is null or u.email = :email)")
    long checkExistEmailOrUsername(String username,String email, Long id);
}
