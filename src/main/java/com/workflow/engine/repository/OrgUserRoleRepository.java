package com.workflow.engine.repository;

import com.workflow.engine.entity.OrgUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrgUserRoleRepository extends JpaRepository<OrgUserRole, Long> {
    @Query("SELECT our FROM OrgUserRole our WHERE our.login = ?1")
    Optional<OrgUserRole> findByLoginId(String assigneeLoginId);
}