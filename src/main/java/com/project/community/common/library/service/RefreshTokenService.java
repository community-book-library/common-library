package com.project.community.common.library.service;

import com.project.community.common.library.entity.RefreshToken;
import com.project.community.common.library.repository.CommUserRepository;
import com.project.community.common.library.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenService
{
    @Value("${jwt.refresh-token.expiration}")
    private Long refreshTokenDurationMs;

    @Value("${app.title}")
    private String appTitle;

    private final RefreshTokenRepository refreshTokenRepository;
    private final CommUserRepository userRepository;

    public RefreshToken createRefreshToken(String refToken, String username) {
        var token = new RefreshToken();
        token.setUser(userRepository.findByEmail(username).get());
        token.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenDurationMs/1000));
        token.setToken(refToken);
        token.setCreatedBy(appTitle);
        return refreshTokenRepository.save(token);
    }


    /**
     * Verify refresh token
     */
    public RefreshToken verifyRefreshToken(String token) throws  Exception{
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).get();

        if (refreshToken.isRevoked()) {
            throw new Exception("Refresh token has been revoked");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new Exception("Refresh token expired. Please login again");
        }

        return refreshToken;
    }

    /**
     * Revoke refresh token (for logout)
     */
    public void revokeRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .ifPresent(refreshToken -> {
                    refreshToken.setRevoked(true);
                    refreshTokenRepository.save(refreshToken);
                });
    }


}
