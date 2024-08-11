package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.dto.RecruitDto;
import com.example.wantedpreonboardingbackend.dto.RecruitListDto;
import com.example.wantedpreonboardingbackend.service.BoardService;
import com.example.wantedpreonboardingbackend.vo.RecruitDetailVo;
import com.example.wantedpreonboardingbackend.vo.RecruitSearchResponse;
import com.example.wantedpreonboardingbackend.vo.RecruitVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

//  채용 공고 등록하기
@PostMapping("/write")
public ResponseEntity<?> addRecruit(@RequestBody RecruitDto recruitDto,
                                         @SessionAttribute("userGradeId") int userGradeId,
                                         @SessionAttribute("companyId") Long companyId){
//       일반 사용자 접근 거부
    if (userGradeId != 1) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("채용 공고 등록 권한이 없습니다.");
    }

    recruitDto.setCompanyId(companyId);

    try {
        RecruitDto createdRecruitDto = boardService.registerRecruit(recruitDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRecruitDto);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("채용 공고 등록 중 오류가 발생했습니다.");
    }

}

//    채용공고 수정하기
@PatchMapping("/modify/{recruitId}")
public ResponseEntity<RecruitDto> modifyRecruit(@PathVariable Long recruitId,
                                                @RequestBody RecruitDto recruitDto){
    try {
        RecruitDto updatedRecruitDto = boardService.modifyRecruit(recruitId, recruitDto);
        return ResponseEntity.ok(updatedRecruitDto);
    } catch (NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }  catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

//    채용공고 삭제하기
    @DeleteMapping("/remove/{recruitId}")
    public ResponseEntity<Void> removeRecruit(@PathVariable Long recruitId){
        boardService.removeRecruit(recruitId);
        return ResponseEntity.noContent().build();
    }

//    채용공고 목록 가져오기
    @GetMapping("list")
    public List<RecruitVo> recruitList(){
        List<RecruitVo> recruitList = boardService.findRecruitList();

        return recruitList;
    }

//    채용공고 목록 검색하기
    @GetMapping("/search")
    public ResponseEntity<RecruitSearchResponse> searchRecruitByWord(@RequestParam("searchWord") String searchWord){
        List<RecruitVo> recruitByWordList = boardService.findRecruitByWord(searchWord);
        RecruitSearchResponse response = new RecruitSearchResponse(recruitByWordList, searchWord);
        return ResponseEntity.ok(response);
    }

//    채용공고 상세보기
    @GetMapping("/list/{recruitId}")
    public List<RecruitDetailVo> recruitDetail(@PathVariable Long recruitId){
        return boardService.findRecruitDetail(recruitId);
    }

// 채용공고 지원하기
    @PostMapping("/list/{recruitId}/apply")
    public ResponseEntity<?> applyRecruit(@PathVariable Long recruitId,
                                                       @SessionAttribute("userGradeId") int userGradeId,
                                                       @SessionAttribute("userId") Long userId) {
        // 일반 사용자만 가능
        if (userGradeId != 2) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("입사지원 권한이 없습니다.");
        }

        try {
            RecruitListDto recruitListDto = new RecruitListDto();
            recruitListDto.setRecruitId(recruitId);
            recruitListDto.setUserId(userId);

            boardService.registerRecruitApply(recruitListDto);
            return ResponseEntity.status(HttpStatus.OK).body(recruitListDto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미 지원한 공고입니다.");
        }
    }

}
