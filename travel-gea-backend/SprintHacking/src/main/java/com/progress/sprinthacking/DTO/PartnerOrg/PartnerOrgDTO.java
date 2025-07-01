package com.progress.sprinthacking.DTO.PartnerOrg;

import lombok.Data;

import java.util.List;

@Data
public class PartnerOrgDTO {
    private Long id;
    private String name;
    private String description;
    private String photo;
    private String contact;
    private List<String> tags;
    private String userName;
}
