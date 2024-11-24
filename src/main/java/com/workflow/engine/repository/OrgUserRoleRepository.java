package com.workflow.engine.repository;

import com.workflow.engine.entity.OrgRole;
import com.workflow.engine.entity.OrgUserRole;
import com.workflow.engine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgUserRoleRepository extends JpaRepository<OrgUserRole, Long> {
    @Query("SELECT our FROM OrgUserRole our WHERE our.user = ?1")
    Optional<OrgUserRole> findByLoginId(String assigneeLoginId);

    Optional<OrgUserRole> findOrgUserRoleByRole(OrgRole role);
}