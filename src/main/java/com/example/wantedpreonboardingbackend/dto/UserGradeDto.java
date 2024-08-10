package com.example.wantedpreonboardingbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserGradeDto {
    private Long userGradeId;
    private String userGradeName;
}
