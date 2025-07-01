package com.progress.sprinthacking.DTO.Municipality;

import lombok.Data;

import java.util.List;

@Data
public class MunicipalityRequestDTO {
    private Long id;
    private String name;
    private String description;
    private String photo;
    private String contact;
    private String userName;
    private String password;
    private String email;
}
