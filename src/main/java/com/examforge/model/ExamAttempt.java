package com.examforge.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exam_attempts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private int totalMarks    = 0;
    private int maxMarks      = 27;
    private double percentage = 0.0;

    private int easyCorrect   = 0;
    private int mediumCorrect = 0;
    private int hardCorrect   = 0;

    private int easyMarks   = 0;
    private int mediumMarks = 0;
    private int hardMarks   = 0;

    @Enumerated(EnumType.STRING)
    private LevelReached levelReached = LevelReached.EASY;

    @Column(columnDefinition = "TEXT")
    private String weaknessData;

    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    public enum LevelReached {
        EASY, MEDIUM, HARD
    }
}