package com.examforge.repository;

import com.examforge.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubjectRepository
        extends JpaRepository<Subject, Long> {

    List<Subject> findByActiveTrue();
    List<Subject> findByCreatedByIdAndActiveTrue(
            Long teacherId);
}