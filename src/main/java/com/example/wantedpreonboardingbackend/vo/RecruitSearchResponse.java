package com.example.wantedpreonboardingbackend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitSearchResponse {
    private List<RecruitVo> RecruitByWord;
    private String searchWord;
}
