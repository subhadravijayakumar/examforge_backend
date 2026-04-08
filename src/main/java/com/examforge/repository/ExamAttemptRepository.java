package com.examforge.repository;

import com.examforge.model.ExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExamAttemptRepository
        extends JpaRepository<ExamAttempt, Long> {

    List<ExamAttempt> findByUserIdOrderByCompletedAtDesc(Long userId);

    List<ExamAttempt> findBySubjectIdOrderByPercentageDesc(Long subjectId);

    List<ExamAttempt> findTop10ByOrderByPercentageDesc();
}