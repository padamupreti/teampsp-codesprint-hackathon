package com.progress.sprinthacking.DTO.Event;

import com.progress.sprinthacking.Entity.Municipality;
import com.progress.sprinthacking.Entity.PartnerOrg;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class EventDTO {
    private Long id;

    private String name;

    private boolean isSponsored;

    private ZonedDateTime startTime;

    private int durationHours;

    private Float price;

    private String partnerOrgName;

    private int partnerOrgId;

    private String municipalityName;

    private int municipalityId;
}
