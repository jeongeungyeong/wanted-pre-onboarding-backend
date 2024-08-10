package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.RecruitDto;
import com.example.wantedpreonboardingbackend.dto.RecruitListDto;
import com.example.wantedpreonboardingbackend.mapper.BoardMapper;
import com.example.wantedpreonboardingbackend.vo.RecruitDetailVo;
import com.example.wantedpreonboardingbackend.vo.RecruitVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardMapper boardMapper;

//    채용공고 등록
    public void registerRecruit(RecruitDto recruitDto){
        boardMapper.insertRecruit(recruitDto);
    }

//    채용공고 수정
public RecruitDto modifyRecruit(Long recruitId,RecruitDto recruitDto){
    recruitDto.setRecruitId(recruitId);
    boardMapper.updateRecruit(recruitDto);
    RecruitDto updatedRecruitDto = boardMapper.selectByRecruitId(recruitId);
    if (updatedRecruitDto == null) {
        throw new NoSuchElementException("해당 채용글ID가 없습니다: " + recruitId);
    }
    return updatedRecruitDto;
}


//    채용공고 삭제
    public void removeRecruit(Long recruitId){
        boardMapper.deleteRecruit(recruitId);
    }

//    채용공고 목록 가져오기
    public List<RecruitVo> findRecruitList(){
        List<RecruitVo> recruitList = boardMapper.selectRecruitList();

        return recruitList;
    }

//    채용공고 목록 검색하기
    public List<RecruitVo> findRecruitByWord(String searchWord){
        return boardMapper.selectRecruitByWord(searchWord);
    }

//   채용공고 상세보기
    public List<RecruitDetailVo> findRecruitDetail(Long recruitId){
        return boardMapper.selectRecruitDetail(recruitId);
    };

//    채용공고 참여하기
    public void registerRecruitApply(RecruitListDto recruitListDto){
        int count = boardMapper.selectRecruitApply(recruitListDto);
        if(count>0){
            throw new IllegalStateException("이미 지원한 공고입니다!");
        }
        boardMapper.insertRecruitApply(recruitListDto);
    }

}
