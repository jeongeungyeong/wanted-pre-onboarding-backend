package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.RecruitDto;
import com.example.wantedpreonboardingbackend.dto.RecruitListDto;
import com.example.wantedpreonboardingbackend.service.BoardService;
import com.example.wantedpreonboardingbackend.vo.RecruitDetailVo;
import com.example.wantedpreonboardingbackend.vo.RecruitSearchResponse;
import com.example.wantedpreonboardingbackend.vo.RecruitVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BoardControllerTest {
    @Mock
    private BoardService boardService;

    @InjectMocks
    private BoardController boardController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    RecruitDto recruitDto;
    RecruitVo recruitVo;
    RecruitDetailVo recruitDetailVo;
    RecruitListDto recruitListDto;
    RecruitSearchResponse recruitSearchResponse;
    List<RecruitVo> recruitList;
    List<RecruitDetailVo> recruitDetailList;


    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
        objectMapper = new ObjectMapper();

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

        recruitList = Arrays.asList(recruitVo);
        recruitDetailList = Arrays.asList(recruitDetailVo);
    }

    @DisplayName("채용공고 등록")
    @Test
    void addRecruit() throws Exception{
        // Given
        Mockito.when(boardService.registerRecruit(any(RecruitDto.class))).thenReturn(recruitDto);
        // When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/board/write")
                        .sessionAttr("userGradeId",1L)
                        .sessionAttr("companyId",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recruitDto))
        );
        // Then
        resultActions.andExpect(status().isCreated())
                     .andExpect(content().json(objectMapper.writeValueAsString(recruitDto)));
    }
    @DisplayName("채용공고 수정 - 성공")
    @Test
    void modifyRecruit() throws Exception{
        // Given
        Long recruitId = 1L;
        // 수정된 공고
        RecruitDto updatedRecruitDto = new RecruitDto();
        updatedRecruitDto.setRecruitId(recruitId);
        updatedRecruitDto.setRecruitPosition("백엔드 개발자");
        updatedRecruitDto.setRecruitPay(2200000L);
        updatedRecruitDto.setRecruitContent("수정된 채용공고 내용입니다.");
        updatedRecruitDto.setRecruitSkill("Spring");

        Mockito.when(boardService.modifyRecruit(eq(recruitId),any(RecruitDto.class)))
                .thenReturn(updatedRecruitDto);
        //    When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/board/modify/{recruitId}",recruitId)
                        .sessionAttr("userGradeId",1L)
                        .sessionAttr("companyId",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recruitDto))
        );

        //       Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedRecruitDto)));
    }

    @DisplayName("채용공고 삭제하기")
    @Test
    void removeRecruit() throws Exception {
        // Given
        Long recruitId = 1L;
        Mockito.doNothing().when(boardService).removeRecruit(recruitId);

        // When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/board/remove/{recruitId}", recruitId)
                        .sessionAttr("userGradeId", 1L)
                        .sessionAttr("companyId", 1L)
        );

        // Then
        resultActions.andExpect(status().isNoContent());
    }

    @DisplayName("채용공고 목록 가져오기")
    @Test
    void recruitList() throws Exception {
        // Given
        Mockito.when(boardService.findRecruitList()).thenReturn(recruitList);

        // When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/board/list")
        );

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(recruitList)));
    }

    @DisplayName("채용공고 목록 검색하기")
    @ParameterizedTest
    @ValueSource(strings = {"개발자", "백엔드", "테스트"})
    void searchRecruitByWord(String searchWord) throws Exception {
        // Given
        Mockito.when(boardService.findRecruitByWord(searchWord)).thenReturn(recruitList);
        recruitSearchResponse = new RecruitSearchResponse(recruitList, searchWord);
        // When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/board/search")
                        .param("searchWord", searchWord)
        );

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(recruitSearchResponse)));
    }

    @DisplayName("채용공고 상세보기")
    @Test
    void recruitDetail() throws Exception {
        // Given
        Long recruitId = 1L;
        Mockito.when(boardService.findRecruitDetail(recruitId)).thenReturn(recruitDetailList);

        // When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/board/list/{recruitId}", recruitId)
        );

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(recruitDetailList)));
    }

    @DisplayName("채용공고 지원하기 - 성공")
    @Test
    void applyRecruit() throws Exception {
        // Given
        Long recruitId = 1L;
        Long userGradeId = 2L;
        Long userId = 123L;
        RecruitListDto recruitListDto = new RecruitListDto();
        recruitListDto.setRecruitId(recruitId);
        recruitListDto.setUserId(userId);

        Mockito.doNothing().when(boardService).registerRecruitApply(recruitListDto);

        // When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/board/list/{recruitId}/apply", recruitId)
                        .sessionAttr("userGradeId", userGradeId)
                        .sessionAttr("userId", userId)
        );

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(recruitListDto)));
    }


}
