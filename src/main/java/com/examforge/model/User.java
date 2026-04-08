package com.examforge.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String className;

    // 🔥 Existing fields
    private int xp = 0;
    private int level = 1;
    private int streak = 0;

    // 🔥 NEW fields (VERY IMPORTANT)
    private int examsTaken = 0;
    private double avgScore = 0;
    private int badges = 0;

    private LocalDateTime lastActive;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    // ✅ Automatically set created time
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum Role {
        STUDENT, TEACHER, ADMIN
    }
}