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
### 3️⃣ 채용공고를 삭제합니다
- DB에서 삭제됩니다.
### 🟣 API 명세서
|HTTP Method|EndPoint|Description| 
|:---:|:---:|:---:|
|DELETE|board/remove/{recruitId}|채용공고 삭제|
#### Request Parameters
- @PathVariable Long recruitId: 삭제할 채용 공고의 ID
#### Response
- Success (204 No Content): 응답 본문 없음
- Failure (404 Not Found)
```json
{
  "message": "해당 채용 공고를 찾을 수 없습니다."
}
```
- Failure (500 Internal Server Error)
```json
{
  "message": "채용 공고 삭제 중 오류가 발생했습니다."
}
```
### 4️⃣ 채용공고 목록을 가져옵니다
#### ⏺ 사용자는 채용공고 목록을 아래와 같이 확인할 수 있습니다.
### 🟣 API 명세서
|HTTP Method|EndPoint|Description| 
|:---:|:---:|:---:|
|GET|board/list|채용공고 목록 조회|
#### Response
- Success (200 OK)
```json
[
    {
        "recruitId": 3,
        "companyName": "SOCAR",
        "companyNation": "한국",
        "companyArea": "부산",
        "recruitPosition": "백엔드 주니어 개발자",
        "recruitPay": 1500000,
        "recruitSkill": "Python"
    },
    {
        "recruitId": 4,
        "companyName": "SOCAR",
        "companyNation": "한국",
        "companyArea": "부산",
        "recruitPosition": "백엔드 주니어 개발자",
        "recruitPay": 2500000,
        "recruitSkill": "javascript"
    },
    {
        "recruitId": 5,
        "companyName": "SOCAR",
        "companyNation": "한국",
        "companyArea": "부산",
        "recruitPosition": "프론트엔드 주니어 개발자",
        "recruitPay": 1500000,
        "recruitSkill": "javascript"
    },
    {
        "recruitId": 6,
        "companyName": "yanolja",
        "companyNation": "한국",
        "companyArea": "서울",
        "recruitPosition": "프론트엔드 주니어 개발자",
        "recruitPay": 2500000,
        "recruitSkill": "javascript"
    },
    {
        "recruitId": 7,
        "companyName": "yanolja",
        "companyNation": "한국",
        "companyArea": "서울",
        "recruitPosition": "프론트엔드 주니어 개발자",
        "recruitPay": 4500000,
        "recruitSkill": "javascript"
    },
    {
        "recruitId": 8,
        "companyName": "yanolja",
        "companyNation": "한국",
        "companyArea": "서울",
        "recruitPosition": "백엔드 주니어 개발자",
        "recruitPay": 3500000,
        "recruitSkill": "Java"
    },
    {
        "recruitId": 9,
        "companyName": "yanolja",
        "companyNation": "한국",
        "companyArea": "서울",
        "recruitPosition": "백엔드 주니어 개발자",
        "recruitPay": 2500000,
        "recruitSkill": "Spring"
    },
    {
        "recruitId": 10,
        "companyName": "yanolja",
        "companyNation": "한국",
        "companyArea": "서울",
        "recruitPosition": "백엔드 주니어 개발자",
        "recruitPay": 2500000,
        "recruitSkill": "php"
    }
]
```

#### ⏺ 채용공고 검색 기능 구현 
### 🟣 API 명세서
|HTTP Method|EndPoint|Description| 
|:---:|:---:|:---:|
|GET|board/search?searchWord=백엔드|채용공고 검색|
#### Request Parameters
- @RequestParam("searchWord") String searchWord: 검색어
#### Response
- Success (200 OK)
```json
{
    "searchWord": "백엔드",
    "recruitByWord": [
        {
            "recruitId": 3,
            "companyName": "SOCAR",
            "companyNation": "한국",
            "companyArea": "부산",
            "recruitPosition": "백엔드 주니어 개발자",
            "recruitPay": 1500000,
            "recruitSkill": "Python"
        },
        {
            "recruitId": 4,
            "companyName": "SOCAR",
            "companyNation": "한국",
            "companyArea": "부산",
            "recruitPosition": "백엔드 주니어 개발자",
            "recruitPay": 2500000,
            "recruitSkill": "javascript"
        },
        {
            "recruitId": 8,
            "companyName": "yanolja",
            "companyNation": "한국",
            "companyArea": "서울",
            "recruitPosition": "백엔드 주니어 개발자",
            "recruitPay": 3500000,
            "recruitSkill": "Java"
        },
        {
            "recruitId": 9,
            "companyName": "yanolja",
            "companyNation": "한국",
            "companyArea": "서울",
            "recruitPosition": "백엔드 주니어 개발자",
            "recruitPay": 2500000,
            "recruitSkill": "Spring"
        },
        {
            "recruitId": 10,
            "companyName": "yanolja",
            "companyNation": "한국",
            "companyArea": "서울",
            "recruitPosition": "백엔드 주니어 개발자",
            "recruitPay": 2500000,
            "recruitSkill": "php"
        }
    ]
}
```

### 5️⃣ 채용공고 상세 페이지를 가져옵니다
- 사용자는 채용상세 페이지를 아래와 같이 확인할 수 있습니다.
- "채용내용"이 추가적으로 담겨있습니다.
- 해당 회사가 올린 다른 채용공고가 추가적으로 포함됩니다.
  - MyBatis를 사용하여 `BE_RECRUIT`와 `BE_COMPANY` 테이블에서 데이터 조회
```xml
<select id="selectRecruitDetail" parameterType="long" resultType="recruitDetailVo">
    <![CDATA[
    SELECT BR.RECRUIT_ID, BC.COMPANY_NAME, BC.COMPANY_NATION,
           BC.COMPANY_AREA, BR.RECRUIT_POSITION, BR.RECRUIT_PAY,
           BR.RECRUIT_SKILL, BR.RECRUIT_CONTENT,
           (
           SELECT LISTAGG(BR_SUB.RECRUIT_ID, ',') WITHIN GROUP (ORDER BY BR_SUB.RECRUIT_ID)
           FROM BE_RECRUIT BR_SUB
           WHERE BR_SUB.COMPANY_ID = BR.COMPANY_ID
           AND BR_SUB.RECRUIT_ID <> BR.RECRUIT_ID
           ) AS RECRUIT_ID_LIST
    FROM BE_RECRUIT BR
    LEFT JOIN BE_COMPANY BC ON BR.COMPANY_ID = BC.COMPANY_ID
    WHERE BR.RECRUIT_ID = #{recruitId}
    ]]>
</select>
```

### 🟣 API 명세서
|HTTP Method|EndPoint|Description| 
|:---:|:---:|:---:|
|GET|board/list/{recruitId}|채용공고 상세조회|
#### Request Parameters
- @PathVariable Long recruitId: 상세 정보를 볼 채용 공고의 ID
#### Response
- Success (200 OK)
```json
[
    {
        "recruitId": 6,
        "companyName": "yanolja",
        "companyNation": "한국",
        "companyArea": "서울",
        "recruitPosition": "프론트엔드 주니어 개발자",
        "recruitPay": 2500000,
        "recruitSkill": "javascript",
        "recruitContent": "야놀자에서 프론트엔드 주니어 개발자를 채용합니다!",
        "recruitIdList": [7,8,9,10]
    }
]
```
### 6️⃣ 사용자는 채용공고에 지원합니다
### 🟣 API 명세서
|HTTP Method|EndPoint|Description| 
|:---:|:---:|:---:|
|POST|board/list/{recruitId}/apply|채용공고 사용자 지원|
#### Request Parameters
- @PathVariable Long recruitId: 지원할 채용 공고의 ID
- @SessionAttribute("userGradeId") int userGradeId: 회원 등급 (2: 일반 사용자)
- @SessionAttribute("userId") Long userId: 사용자 ID
#### Response
- Success (200 OK)
```json
{
    "userId": 1,
    "recruitId": 7
}
```
- Failure (403 Forbidden)
```json
{
  "message": "입사지원 권한이 없습니다."
}
```
- Failure (500 Internal Server Error)
```json
{
  "message": "이미 지원한 공고입니다."
}
```
