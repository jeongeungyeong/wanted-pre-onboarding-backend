package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.CompanyDto;
import com.example.wantedpreonboardingbackend.dto.UserDto;
import com.example.wantedpreonboardingbackend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserMapper userMapper;

//    사용자 등록
    public void registerUser(UserDto userDto) {
        userMapper.insertUser(userDto);
    }

//    사용자 조회
    public Long findUserId(String userEmail, String userPassword){
        return userMapper.selectUserId(userEmail,userPassword)
                .orElseThrow(()->new IllegalStateException("존재하지 않는 사용자입니다."));
    }
//    회사 등록
public void registerCompany(CompanyDto companyDto) {
    userMapper.insertCompany(companyDto);
}
//    회사 조회
public Long findCompanyId(String companyEmail, String companyPassword){
    return userMapper.selectCompanyId(companyEmail,companyPassword)
            .orElseThrow(()->new IllegalStateException("존재하지 않는 회사입니다."));
}

}
