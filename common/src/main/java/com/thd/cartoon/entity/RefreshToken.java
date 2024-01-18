package com.thd.cartoon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author DatNuclear 18/01/2024
 * @project cartoon-movie
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_token")
public class RefreshToken extends BaseEntity{
    @Column(name = "token")
    private String token;
    @Column(name = "username")
    private String username;
    @Column(name="expired")
    private boolean expired;
    @Column(name="revoked")
    private boolean revoked;
}
