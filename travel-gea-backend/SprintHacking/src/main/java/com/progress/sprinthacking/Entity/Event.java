package com.progress.sprinthacking.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name ="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "is_sponsored", nullable = false)
    private boolean isSponsored;

    @Column(name = "start_time", nullable = false)
    private ZonedDateTime startTime;

    @Column(name = "duration", nullable = false)
    private int durationHours;

    @Column(name = "price", nullable = false)
    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_org_id")
    private PartnerOrg partnerOrg;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;
}
