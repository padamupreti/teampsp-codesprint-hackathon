package com.progress.sprinthacking.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="tourists")
public class Tourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guide_name", length = 100, nullable = false)
    private String touristName;

    @Column(name = "guide_description", length = 500, nullable = false)
    private String touristDescription;

    @Column(name = "specialities",nullable = false)
    private String interests;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
