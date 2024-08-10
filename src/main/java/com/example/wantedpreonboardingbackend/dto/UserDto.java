package com.example.wantedpreonboardingbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;
    private String userSkill;
    private String userEmail;
    private String userPassword;
    private Long userGradeId;
}
