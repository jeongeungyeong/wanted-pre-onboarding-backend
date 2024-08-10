package com.example.wantedpreonboardingbackend.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
public class RecruitVo {
    private Long recruitId;
    private String companyName;
    private String companyNation;
    private String companyArea;
    private String recruitPosition;
    private Long recruitPay;
    private String recruitSkill;
}
