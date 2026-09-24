package com.example.demo.repository;

import com.example.demo.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAssignmentRepository extends JpaRepository<Assignment, Integer> {

    List<Assignment> findDistinctByClassroom_Teacher_UsernameAndRepositories_PullRequests_Reviewer_UsernameAndRepositories_PullRequests_Status(String teacherUsername, String reviewerUsername, String status);

}
