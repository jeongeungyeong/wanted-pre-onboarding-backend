package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.RecruitDto;
import com.example.wantedpreonboardingbackend.dto.RecruitListDto;
import com.example.wantedpreonboardingbackend.mapper.BoardMapper;
import com.example.wantedpreonboardingbackend.vo.RecruitDetailVo;
import com.example.wantedpreonboardingbackend.vo.RecruitSearchResponse;
import com.example.wantedpreonboardingbackend.vo.RecruitVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @Mock
    BoardMapper boardMapper;
    @InjectMocks
    BoardService boardService;


    RecruitDto recruitDto;
    RecruitVo recruitVo;
    RecruitDetailVo recruitDetailVo;
    RecruitListDto recruitListDto;

    @BeforeEach
    void setUp() {
        recruitDto = new RecruitDto();
        recruitDto.setRecruitId(1L);
        recruitDto.setRecruitPosition("백엔드 개발자 테스트");
        recruitDto.setRecruitPay(1000000L);
        recruitDto.setRecruitContent("채용공고 테스트합니다!");
        recruitDto.setRecruitSkill("Python");
        recruitDto.setCompanyId(1L);

        recruitVo = new RecruitVo();
        recruitVo.setRecruitId(1L);
        recruitVo.setCompanyName("테스트");
        recruitVo.setCompanyNation("한국");
        recruitVo.setCompanyArea("서울");
        recruitVo.setRecruitPosition("개발자 테스트");
        recruitVo.setRecruitPay(10000L);
        recruitVo.setRecruitSkill("Java");

        recruitDetailVo = new RecruitDetailVo();
        recruitDetailVo.setRecruitId(1L);
        recruitDetailVo.setCompanyName("테스트");
        recruitDetailVo.setCompanyNation("한국");
        recruitDetailVo.setCompanyArea("서울");
        recruitDetailVo.setRecruitPosition("개발자 테스트");
        recruitDetailVo.setRecruitPay(10000L);
        recruitDetailVo.setRecruitSkill("Java");
        recruitDetailVo.setRecruitContent("채용공고 상세 테스트~");
        recruitDetailVo.setRecruitIdList("1,2,3");

        recruitListDto = new RecruitListDto();
        recruitListDto.setRecruitId(1L);
        recruitListDto.setUserId(1L);
    }
    
    @DisplayName("채용공고 등록")
    @Test
    void registerRecruit() {
        // Given
        Mockito.doNothing().when(boardMapper).insertRecruit(any(RecruitDto.class));
        // When
        boardService.registerRecruit(recruitDto);
        // Then
        Mockito.verify(boardMapper, Mockito.times(1)).insertRecruit(any());
    }
    @DisplayName("채용공고 수정")
    @Test
    void modifyRecruit() {
        // Given
        doNothing().when(boardMapper).updateRecruit(any(RecruitDto.class));
        when(boardMapper.selectByRecruitId(any(Long.class))).thenReturn(recruitDto);
        // When
        RecruitDto updatedRecruitDto = boardService.modifyRecruit(recruitDto.getRecruitId(), recruitDto);
        // Then
        verify(boardMapper, times(1)).updateRecruit(any(RecruitDto.class));
        verify(boardMapper, times(1)).selectByRecruitId(recruitDto.getRecruitId());

        assertEquals(recruitDto, updatedRecruitDto);
    }
    @DisplayName("채용공고 삭제")
    @Test
    void removeRecruit() {
        // Given
        doNothing().when(boardMapper).deleteRecruit(any(Long.class));
        // When
        boardService.removeRecruit(1L);
        // Then
        verify(boardMapper, times(1)).deleteRecruit(any());
    }
    
    @DisplayName("채용공고 조회")
    @Test
    void findRecruitList() {
        // Given
        doReturn(List.of(recruitVo)).when(boardMapper).selectRecruitList();
        // When
        List<RecruitVo> recruitList = boardService.findRecruitList();
        // Then
        recruitList.contains(recruitVo);
    }
    @DisplayName("채용공고 검색")
    @Test
    void findRecruitByWord() {
        // Given
        String searchWord = "개발자";
        List<RecruitVo> mockRecruitList = List.of(recruitVo);
        RecruitSearchResponse expectedResponse = new RecruitSearchResponse(mockRecruitList, searchWord);

        when(boardMapper.selectRecruitByWord(searchWord)).thenReturn(mockRecruitList);

        // When
        List<RecruitVo> actualRecruitList = boardService.findRecruitByWord(searchWord);

        // Then
        assertEquals(expectedResponse.getRecruitByWord(), actualRecruitList);
        assertEquals(expectedResponse.getSearchWord(), searchWord);

        verify(boardMapper, times(1)).selectRecruitByWord(searchWord);
    }
    @DisplayName("채용공고 상세 조회")
    @Test
    void findRecruitDetail() {
        // Given
        Long recruitId = 1L;
        List<RecruitDetailVo> mockDetailList = List.of(recruitDetailVo);

        when(boardMapper.selectRecruitDetail(recruitId)).thenReturn(mockDetailList);

        // When
        List<RecruitDetailVo> detailList = boardService.findRecruitDetail(recruitId);

//       Then
        assertNotNull(detailList);
        assertEquals(1, detailList.size());
        assertTrue(detailList.contains(recruitDetailVo));

        verify(boardMapper, times(1)).selectRecruitDetail(recruitId);
    }
    @DisplayName("채용공고 지원")
    @Test
    void registerRecruitApply() {
        // Given
        when(boardMapper.selectRecruitApply(recruitListDto)).thenReturn(0);

        // When
        boardService.registerRecruitApply(recruitListDto);

        // Then
        verify(boardMapper, times(1)).insertRecruitApply(recruitListDto);
    }

}
