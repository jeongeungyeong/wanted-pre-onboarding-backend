package com.example.wantedpreonboardingbackend.controller;

import com.example.wantedpreonboardingbackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    사용자 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("userGradeId") int userGradeId,
            HttpServletRequest req){

        Long userId = null;
        Long companyId = null;

//        userGradeId가 1이면 회사, 2면 개인 사용자
        if(userGradeId == 2) {
            userId = userService.findUserId(email,password);
        } else if(userGradeId == 1){
            companyId = userService.findCompanyId(email,password);
        } else {
            return ResponseEntity.badRequest().body("잘못된 이메일, 비밀번호입니다!");
        }

//        로그인 성공 > 세션 생성
        HttpSession session = req.getSession();
        session.setAttribute("userId",userId);
        session.setAttribute("companyId", companyId);
        session.setAttribute("userGradeId",userGradeId);

//       사용자 정보 반환
        Map<String,Object> response = new HashMap<>();
        response.put("userId",userId);
        response.put("companyId", companyId);
        response.put("userGradeId",userGradeId);

        return ResponseEntity.ok(response);

    }
}


