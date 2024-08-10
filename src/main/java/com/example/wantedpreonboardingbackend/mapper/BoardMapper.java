package com.example.wantedpreonboardingbackend.mapper;
import com.example.wantedpreonboardingbackend.dto.RecruitDto;
import com.example.wantedpreonboardingbackend.dto.RecruitListDto;
import com.example.wantedpreonboardingbackend.vo.RecruitDetailVo;
import com.example.wantedpreonboardingbackend.vo.RecruitVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BoardMapper {
//    채용 공고 등록
    public void insertRecruit(RecruitDto recruitDto);

//    채용 공고 수정
    public void updateRecruit(RecruitDto recruitDto);

//    채용 공고 ID 조회
    public RecruitDto selectByRecruitId(Long recruitId);

//    채용 공고 삭제
    public void deleteRecruit(Long recruitId);

//    채용 공고 목록 가져오기
    public List<RecruitVo> selectRecruitList();

//   채용 공고 검색하기
    public List<RecruitVo> selectRecruitByWord(String searchWord);

//    채용 공고 상세보기
    public List<RecruitDetailVo> selectRecruitDetail(Long recruitId);

//    채용 공고 지원하기
    public void insertRecruitApply(RecruitListDto recruitListDto);

//    사용자 지원한 공고인지 조회
    int selectRecruitApply(RecruitListDto recruitListDto);
}












