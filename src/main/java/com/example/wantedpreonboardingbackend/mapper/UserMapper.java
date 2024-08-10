package com.example.wantedpreonboardingbackend.mapper;

import com.example.wantedpreonboardingbackend.dto.CompanyDto;
import com.example.wantedpreonboardingbackend.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

//   사용자 등록
void insertUser(UserDto userDto);

//  사용자 조회
Optional<Long> selectUserId(String userEmail, String userPassword);

//   회사 등록
    void insertCompany(CompanyDto companyDto);

//  회사 조회
   Optional<Long> selectCompanyId(String companyEmail, String companyPassword);

}








