package me.songyeongpyo.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.songyeongpyo.springbootdeveloper.config.jwt.TokenProvider;
import me.songyeongpyo.springbootdeveloper.domain.RefreshToken;
import me.songyeongpyo.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }

    @Transactional
    public void delete() {
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Long userId = tokenProvider.getUserId(token);

        refreshTokenRepository.deleteByUserId(userId);
    }

}
