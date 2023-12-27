package com.thd.cartoon.entity;

import com.thd.cartoon.type.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author DatNuclear 25/12/2023
 * @project cartoon-movie
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_token")
public class Token extends BaseEntity{
    @Column(name="token",unique = true)
    public String token;
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    public TokenType type = TokenType.BEARER;
    @Column(name="revoked")
    public boolean revoked;
    @Column(name="expired")
    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
