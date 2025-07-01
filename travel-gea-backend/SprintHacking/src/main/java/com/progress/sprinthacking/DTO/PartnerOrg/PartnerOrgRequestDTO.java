package com.progress.sprinthacking.DTO.PartnerOrg;

import lombok.Data;

import java.util.List;

@Data
public class PartnerOrgRequestDTO {
    private Long id;
    private String name;
    private String location;
    private String description;
    private String photo;
    private String contact;
    private List<String> tags;
    private String userName;
    private String password;
    private String email;
}
