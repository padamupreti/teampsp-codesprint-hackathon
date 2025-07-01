package com.progress.sprinthacking.DTO.tourist;

import lombok.Data;

import java.util.List;

@Data
public class TouristDTO {
    private Long id;
    private String touristName;
    private String touristDescription;
    private List<String> interests;
    private String userName;
}
