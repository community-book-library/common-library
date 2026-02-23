package com.project.community.common.library.repository;

import com.project.community.common.library.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommUserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(String email);
}
