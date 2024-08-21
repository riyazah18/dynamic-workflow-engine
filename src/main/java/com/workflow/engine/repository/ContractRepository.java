package com.workflow.engine.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.workflow.engine.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}

