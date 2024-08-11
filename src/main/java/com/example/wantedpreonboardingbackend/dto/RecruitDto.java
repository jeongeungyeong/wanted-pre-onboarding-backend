package com.example.wantedpreonboardingbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class RecruitDto {
    private Long companyId;
    private Long recruitId;
    private String recruitPosition;
    private Long recruitPay;
    private String recruitContent;
    private String recruitSkill;
}
