package com.example.demo.repository;

import com.example.demo.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IRepositoryRepository extends JpaRepository<Repository, Integer> {

    List<Repository> findByParentRepoIsNotNullAndAssignment_Classroom_Teacher_EmailAndAssignment_DeadlineAfterOrderByNameAsc(String email, LocalDateTime date);
}
