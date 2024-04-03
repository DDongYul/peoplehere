# 🥕 PeopleHere
> 2023 KUIT X STACK 프로젝트  
> **Meet People Here, Travel the Living Life of Here**

# 🧑🏻‍💻 프로젝트 및 멤버
> 기간: 2023. 12. 03 ~ 2024. 02. 20
## 프로젝트 소개
데이트립 매칭 플랫폼 피플히어 **PeopleHere**는 한국을 방문한 '**여행자**'와 한국에 살고 있는 '**현지인**'을 '**데이트립**'으로 연결합니다.
- Real 한국인의 삶을 경험하고 싶어요. 찐 현지 경험!
- 흔한 랜드마크보다 한국의 구석구석이 궁금해요
- 전문 업체의 투어 비용은 부담스러워요...
- 여행 동행자를 찾고 있어요
- 나만의 취향이 담긴 코스를 다른 사람과 공유하고 싶어요
- 한국의 숨은 명소를 널리 알리고 싶어요!

# 기술 스택

### 환경
![Android](https://img.shields.io/badge/android-34A853?style=for-the-badge&logo=android&logoColor=white)
![Windows](https://img.shields.io/badge/windows-0078D4?style=for-the-badge&logo=windows&logoColor=white)
![macOS](https://img.shields.io/badge/macos-000000?style=for-the-badge&logo=macos&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![Android Studio](https://img.shields.io/badge/androidstudio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white)
![Swagger](https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white)
![Git](https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white)


### 프론트엔드
![Kotlin](https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)

### 백엔드
![Java](https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/springdatajpa-6DB33F?style=for-the-badge&logo=springdatajpa&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Amazon AWS](https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white)
![Amazon S3](https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white)
![Amazon RDS](https://img.shields.io/badge/amazonrds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white)
![Amazon EC2](https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white)

### 협업 툴
![Notion](https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![Figma](https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)
![Slack](https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)
![Microsoft Teams](https://img.shields.io/badge/microsoftteams-6264A7?style=for-the-badge&logo=microsoftteams&logoColor=white)

<hr>

# 서버 아키텍처  

### ERD  
<div><img width="731" alt="erd" src="https://github.com/Petudio/backend/assets/75566606/82a108f4-dca8-44fc-a705-f858d52dd41b"></div>
*노란색 표시 부분은 정적 데이터  

<hr>

# 주요 서비스 화면

### 회원가입, 로그인
<div>
<img width="300" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/31cc10d2-e21e-46e5-9fae-a8413b01834f">
<img width="300" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/08d942c7-81c8-4cc7-b329-38deecfbacb6">
<img width="300" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/f06f9a06-a5e6-4a7d-891e-62c014beabb7">
</div>
<div><img width="527" alt="security config" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/5baffe9a-e239-45f0-8655-2a4c7dca6530"></div>
- Spring Security와 JWT를 사용하여 인증 및 인가 과정을 구현</br>
- 로그아웃시 만료시킬 토큰의 정보를 DB의 blacklist에 담아 해당 토큰을 사용하지 못하도록 구현</br>
- 순환 참조 문제를 해결하기 위해 Spring Security의 UserDetailsService를 구현한 서비스 생성

<hr>

### 코스 생성
<div>
<img width="280" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/b3d37a10-2adf-45fa-82b1-6b8f4e0e65de">
<img width="280" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/b3866de9-f0ae-478b-90e3-d00eb7c5a462">
<img width="280" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/a9b27020-04af-4bea-ac61-0db244a28169">
</div>
<div>
<img width="280" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/4016a5f6-3db8-4b36-9f52-31212286e346">
<img width="280" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/145a2499-0079-4dce-b055-c7a2ad59dffa">
<img width="280" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/a3aa503f-5a9b-49fe-8cf4-7df4bfb22f61">
</div>
- 하나의 코스에는 여러 장소 객체가 저장, 각 장소마다 여러 장의 이미지를 저장할 수 있음</br>
- 구글맵에서 장소의 정보를 가져온 뒤, 각 장소마다 새로운 장소 객체를 생성하여, 코스에 필요한 정보들을 저장하는 방식으로 구현</br>
- 코스 생성시 한 장소마다 여러 장의 이미지를 전송하여 저장해야 하는 문제 발생. 이를 위해 이미지를 인코딩하여 전송하는 방식을 선택</br>
- 인코딩 된 이미지를 디코딩한 뒤 S3에 저장 후 DB에는 해당 이미지들의 URL만 저장하는 방식으로 구현

<hr>

### 기타 사용자 화면
<div>
<img width="280" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/d904a180-f614-4e8c-9dbb-2b4fb49a076c">
<img width="280" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/a308641d-459d-4fef-8721-3d5ed031d78b">
<img width="280" height="650" alt="erd" src="https://github.com/PH-PeopleHere/BackEnd/assets/75566606/547a00f5-9a95-4274-98ec-52eeaaae4e5b">
</div>
- 각각 사용자 화면에서 코스 조회, 코스 상세 정보, 다가오는 만남 화면
<hr>


## 프로젝트 팀 멤버

| ![김태정](https://avatars.githubusercontent.com/u/92737123?v=4) | ![이동열](https://avatars.githubusercontent.com/u/75566606?v=4) | ![전우진](https://avatars.githubusercontent.com/u/90187250?v=4) | ![황정안](https://avatars.githubusercontent.com/u/76906418?v=4) |
|:---------------------------------------------:|:---------------------------------------------:|:---------------------------------------------:|:---------------------------------------------:|
| [김태정](https://github.com/imtaejugkim) | [이동열](https://github.com/DDongYul) | [전우진](https://github.com/bingbong916) | [황정안](https://github.com/jungan777) |

