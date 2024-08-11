# 프리온보딩 백엔드 인턴십 선발과제
## 🟥 Entity Diagram
<img width="581" alt="ERD" src="https://github.com/user-attachments/assets/3ce66483-f4a1-4fe4-bdbf-a0f963c2f1ed">

## 🟧 사용기술
<img src="https://img.shields.io/badge/Java 17-skyblue?style=flat&logo=java&logoColor=F80000"> <img src="https://img.shields.io/badge/JDK 17 -pink?style=flat&logo=JDK&logoColor=F80000"> <img src="https://img.shields.io/badge/intellijidea-white?style=flat&logo=intellijidea&logoColor=000000"> <img src="https://img.shields.io/badge/springboot(3.2.2)-white?style=flat&logo=springboot&logoColor=6DB33F"> <img src="https://img.shields.io/badge/oracle DB(11xe)-red?style=flat&logo=oracle&logoColor=F80000"> <img src="https://img.shields.io/badge/Mybatis-orange?style=flat&logo=mybatis&logoColor=F80000"> <img src="https://img.shields.io/badge/Mockito-green?style=flat&logo=mybatis&logoColor=F80000">

## 🟨 요구사항
### 1️⃣ 채용공고를 등록합니다
- 회사는 아래 데이터와 같이 채용공고를 등록합니다.
- (추가 기능) 세션을 통한 회사 ID 조회
- (추가 기능) 세션을 통한 회원 등급 ID 조회
### 🟣 API 명세서
|HTTP Method|EndPoint|Description| 
|:---:|:---:|:---:|
|POST|board/write|채용공고 등록|
#### Request Parameters
- @SessionAttribute("userGradeId") int userGradeId: 회원 등급 (1: 회사)
- @SessionAttribute("companyId") Long companyId: 회사 ID
#### Request Body
```json
{
  "recruitPosition": "백엔드 주니어 개발자",
  "recruitPay": "2500000",
  "recruitContent": "야놀자에서 백엔드 주니어 개발자를 채용합니다!",
  "recruitSkill": "php"
}
```
#### Response
- Success (201 Created)
```json
{
    "recruitId": 10,
    "recruitPosition": "백엔드 주니어 개발자",
    "recruitPay": 2500000,
    "recruitContent": "야놀자에서 백엔드 주니어 개발자를 채용합니다!",
    "recruitSkill": "php",
    "companyId": 1
}
```
- Failure (403 Forbidden)
```json
{
  "message": "채용 공고 등록 권한이 없습니다."
}
```
- Failure (500 Internal Server Error)
```json
{
  "message": "채용 공고 등록 중 오류가 발생했습니다."
}
```

### 2️⃣ 채용공고를 수정합니다
- 회사는 아래 데이터와 같이 채용공고를 수정합니다
- 회사 ID 이외 모두 수정 가능합니다.
#### 🟣 API 명세서
|HTTP Method|EndPoint|Description| 
|:---:|:---:|:---:|
|PACTH|board/modify/{recruitId}|채용공고 수정|
#### Request Parameters
- @PathVariable Long recruitId: 수정할 채용공고의 ID
#### Request Body
```json
{
 "recruitPay": 2500000
}
```
```json
{
  "recruitContent": "네이버에서 백엔드 주니어 개발자를 채용합니다!",
  "recruitSkill": "Spring"
}
```
#### Response
- Success (200 OK)
```json
{
    "companyId": 1,
    "recruitId": 9,
    "recruitPosition": "백엔드 주니어 개발자",
    "recruitPay": 2500000,
    "recruitContent": "야놀자에서 백엔드 주니어 개발자를 채용합니다!",
    "recruitSkill": "Spring" 
}
```
- Failure (404 Not Found)
```json
{
  "message": "해당 채용 공고를 찾을 수 없습니다."
}
```
- Failure (500 Internal Server Error)
```json
{
  "message": "채용 공고 수정 중 오류가 발생했습니다."
}
```
### 3️⃣
### 4️⃣
### 5️⃣
### 6️⃣




## 🔴🟩⏺🟥
