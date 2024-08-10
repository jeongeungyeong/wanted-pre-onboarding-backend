package com.example.wantedpreonboardingbackend.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@NoArgsConstructor
public class RecruitDetailVo {
    private Long recruitId;
    private String companyName;
    private String companyNation;
    private String companyArea;
    private String recruitPosition;
    private Long recruitPay;
    private String recruitSkill;
    private String recruitContent;

//    회사가 올린 다른 채용공고 리스트형식으로 바꾸기
    private List<Long> recruitIdList;

    public void setRecruitIdList(String recruitIdListStr){
        if(recruitIdListStr != null && !recruitIdListStr.isEmpty()){
            this.recruitIdList = Arrays.stream(recruitIdListStr.split(","))
                                        .map(Long::parseLong)
                                        .collect(Collectors.toList());
        }
    }
}
