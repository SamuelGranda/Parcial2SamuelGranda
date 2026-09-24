package com.example.demo.controller;

import com.example.demo.model.Assignment;
import com.example.demo.model.Commit;
import com.example.demo.model.PullRequest;
import com.example.demo.model.Repository;
import com.example.demo.repository.IAssignmentRepository;
import com.example.demo.repository.ICommitRepository;
import com.example.demo.repository.IPullRequestRepository;
import com.example.demo.repository.IRepositoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/queries")
@RequiredArgsConstructor
public class QueryController {

    private final IPullRequestRepository pullRequestRepository;
    private final IRepositoryRepository repositoryRepository;
    private final ICommitRepository commitRepository;
    private final IAssignmentRepository assignmentRepository;

    @GetMapping("/1")
    public List<PullRequest> query1(@RequestParam String classroom, @RequestParam String status) {
        return pullRequestRepository.findByRepository_Assignment_Classroom_NameAndStatusOrderByCreatedAtDesc(classroom, status);
    }

    @GetMapping("/2")
    public List<Repository> query2(@RequestParam String email,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        return repositoryRepository.findByParentRepoIsNotNullAndAssignment_Classroom_Teacher_EmailAndAssignment_DeadlineAfterOrderByNameAsc(email, date);
    }

    @GetMapping("/3")
    public List<PullRequest> query3(@RequestParam String role, @RequestParam String username,@RequestParam String semester){
        return pullRequestRepository.findByReviewer_RoleAndAuthor_UsernameAndRepository_Assignment_Classroom_Semester(role,username,semester);
    }

    @GetMapping("/4")
    public List<Commit> query4(@RequestParam String templateName,@RequestParam String keyword,@RequestParam Integer lines){
        return commitRepository.findByRepository_ParentRepo_NameAndMessageContainingIgnoreCaseAndLinesAddedGreaterThan(templateName,keyword,lines);
    }

    @GetMapping("/5")
    public List<Assignment> query5(@RequestParam String teacher, @RequestParam String reviewer, @RequestParam String status) {
        return assignmentRepository.findDistinctByClassroom_Teacher_UsernameAndRepositories_PullRequests_Reviewer_UsernameAndRepositories_PullRequests_Status(teacher, reviewer, status);
    }



}

