package com.examforge.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String icon;
    private String color;

    private int marksEasy   = 1;
    private int marksMedium = 3;
    private int marksHard   = 5;

    private boolean easyEnabled   = true;
    private boolean mediumEnabled = true;
    private boolean hardEnabled   = true;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private boolean active = true;

    @OneToMany(mappedBy = "subject",
            cascade = CascadeType.ALL)
    private List<Topic> topics;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}