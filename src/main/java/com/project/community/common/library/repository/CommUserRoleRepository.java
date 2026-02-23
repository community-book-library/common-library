package com.project.community.common.library.repository;


import com.project.community.common.library.entity.UserCommunityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommUserRoleRepository extends JpaRepository<UserCommunityRole,Integer> {
    Optional<UserCommunityRole> findByCommunityIdAndRoleId(int communityId, int roleId);

}
