package com.progress.sprinthacking.DTO;

import lombok.Data;

import java.util.List;

@Data
public class GuideDTO {
    private Long id;
    private String guideName;
    private String guideDescription;
    private String guideImageUrl;
    private String guidePhone;
    private List<String> specialities;
    private String userName;
}
