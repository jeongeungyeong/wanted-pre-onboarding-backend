package com.example.wantedpreonboardingbackend.service;

import com.example.wantedpreonboardingbackend.dto.CompanyDto;
import com.example.wantedpreonboardingbackend.dto.UserDto;
import com.example.wantedpreonboardingbackend.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserMapper userMapper;
    @InjectMocks UserService userService;

    UserDto userDto;
    CompanyDto companyDto;

    @BeforeEach
    void setUp(){
        userDto = new UserDto();
        userDto.setUserName("테스트");
        userDto.setUserSkill("Spring");
        userDto.setUserEmail("test");
        userDto.setUserPassword("1234");

        companyDto = new CompanyDto();
        companyDto.setCompanyName("회사 테스트");
        companyDto.setCompanyEmail("company");
        companyDto.setCompanyPassword("4321");
        companyDto.setCompanyNation("한국");
        companyDto.setCompanyArea("서울");
    }

    @Test
    void registerUser(){
        doNothing().when(userMapper).insertUser(any());
        userService.registerUser(userDto);
        verify(userMapper,times(1)).insertUser(any());
    }

    @Test
    void findUserId(){
        doReturn(Optional.of(1L)).when(userMapper).selectUserId(any(),any());
        Long foundNumber = userService.findUserId("test","1234");
        Assertions.assertThat(foundNumber).isEqualTo(1L);
    }

    @Test
    void registerCompany(){
        doNothing().when(userMapper).insertCompany(any());
        userService.registerCompany(companyDto);
        verify(userMapper,times(1)).insertCompany(any());
    }

    @Test
    void findCompanyId(){
        doReturn(Optional.of(1L)).when(userMapper).selectCompanyId(any(),any());
        Long foundCompanyNumber = userService.findCompanyId("company","4321");
        Assertions.assertThat(foundCompanyNumber).isEqualTo(1L);
    }
}
