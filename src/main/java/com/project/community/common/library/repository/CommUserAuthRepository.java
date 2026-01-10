package com.project.community.common.library.repository;

import com.project.community.common.library.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommUserAuthRepository extends JpaRepository<UserAuth,Integer> {

    UserAuth findByUsername(String username);
}