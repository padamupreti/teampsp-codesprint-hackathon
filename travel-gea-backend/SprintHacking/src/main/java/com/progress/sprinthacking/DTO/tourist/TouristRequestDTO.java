package com.progress.sprinthacking.DTO.tourist;

import lombok.Data;

import java.util.List;

@Data
public class TouristRequestDTO {
    private Long id;
    private String touristName;
    private String touristDescription;
    private List<String> interests;
    private String userName;
    private String password;
    private String email;
}
