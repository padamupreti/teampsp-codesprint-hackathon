package com.progress.sprinthacking.DTO.guide;

import lombok.Data;

import java.util.List;

@Data
public class GuideRequestDTO {
    private Long id;
    private String guideName;
    private String guideDescription;
    private String guideImageUrl;
    private String guidePhone;
    private List<String> specialities;
    private String userName;
    private String password;
    private String email;
}
