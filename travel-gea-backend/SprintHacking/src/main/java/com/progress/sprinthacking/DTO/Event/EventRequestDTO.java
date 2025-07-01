package com.progress.sprinthacking.DTO.Event;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class EventRequestDTO {
    private String name;
    private boolean isSponsored;
    private ZonedDateTime startTime;
    private int durationHours;
    private Float price;
}
