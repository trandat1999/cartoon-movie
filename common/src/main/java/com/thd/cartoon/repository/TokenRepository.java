package com.thd.cartoon.repository;

import com.thd.cartoon.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author DatNuclear 25/12/2023
 * @project cartoon-movie
 */
@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    @Query(value = "select t from Token t " +
            "where t.user.id = :id and (t.expired is false or t.revoked is false)"
      )
    List<Token> findAllValidTokenByUser(Long id);
    Optional<Token> findByToken(String token);
}
