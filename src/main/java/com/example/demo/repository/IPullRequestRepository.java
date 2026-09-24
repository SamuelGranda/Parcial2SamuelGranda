package com.example.demo.repository;

import com.example.demo.model.PullRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPullRequestRepository extends JpaRepository<PullRequest, Integer> {

    List<PullRequest> findByRepository_Assignment_Classroom_NameAndStatusOrderByCreatedAtDesc(String classroomName, String status);

    List<PullRequest> findByReviewer_RoleAndAuthor_UsernameAndRepository_Assignment_Classroom_Semester(String role, String username, String semester);

}
