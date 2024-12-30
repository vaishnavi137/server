package com.example.servicemanagementsystem.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AdDTO {
    

    private Long id;

    private String serviceName;

    private String description;

    private String category;

    private Double price;

    private MultipartFile img;

    private byte[] returnedImg;

    private Long userId;

    private String companyName;
}
