package com.progress.sprinthacking.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="partner_orgs")
public class PartnerOrg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "contact", length = 15, nullable = false, unique = true)
    private String contact;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @Column(name = "photo", length = 255, nullable = false)
    private String photo;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "tags")
    private String tags;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
