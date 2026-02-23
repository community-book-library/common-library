package com.project.community.common.library.repository;


import com.project.community.common.library.entity.RegisterToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterTokenRepository extends JpaRepository<RegisterToken,Integer> {
    Optional<RegisterToken> findByUsername(String username);
}
