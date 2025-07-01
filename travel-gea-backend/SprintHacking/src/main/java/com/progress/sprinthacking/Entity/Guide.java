package com.progress.sprinthacking.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name ="guides")
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guide_name", length = 100, nullable = false)
    private String guideName;

    @Column(name = "guide_phone", length = 15, nullable = false, unique = true)
    private String guidePhone;

    @Column(name = "guide_image_url", length = 255, nullable = false)
    private String guideImageUrl;

    @Column(name = "guide_description", length = 500, nullable = false)
    private String guideDescription;

    @Column(name = "specialities",nullable = false)
    private String specialities;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Reservation> reservations = new HashSet<>();
}
