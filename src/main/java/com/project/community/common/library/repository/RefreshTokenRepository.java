package com.project.community.common.library.repository;

import com.project.community.common.library.entity.RefreshToken;
import com.project.community.common.library.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(Users user);

    void deleteByUser(Users user); // For logout
    void deleteByExpiryDateBefore(LocalDateTime date); // Cleanup expired tokens
}
