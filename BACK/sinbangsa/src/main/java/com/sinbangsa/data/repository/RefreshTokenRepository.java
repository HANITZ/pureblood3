package com.sinbangsa.data.repository;

import com.sinbangsa.data.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByEmail(String email);

    Optional<RefreshToken> findRefreshTokenByRefreshToken(String refreshToken);
}
