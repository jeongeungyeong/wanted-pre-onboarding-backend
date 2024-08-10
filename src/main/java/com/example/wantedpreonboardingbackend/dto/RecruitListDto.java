package com.example.wantedpreonboardingbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class RecruitListDto {
    private Long userId;
    private Long recruitId;
}
