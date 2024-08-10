package com.example.wantedpreonboardingbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CompanyDto {
    private Long companyId;
    private String companyName;
    private String companyEmail;
    private String companyPassword;
    private String companyNation;
    private String companyArea;
    private Long userGradeId;
}
