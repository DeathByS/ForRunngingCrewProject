# ForRunngingCrewProject

## 핵심내용

 * 런닝의 출발 지점과 도착 지점, 날짜와 시각을 설정하여 일정 생성
 * 페이스 메이커의 현재 위치 파악함으로써 낙오 방지

## 기획배경

* 기존 앱의 문제점은 런닝의 출발 지점만 표시한다. 이로 인해 경로의  혼선이 생긴다. 도착 지점을 추가함으로써 이러한 문제점을 방지한다.     
* 혼자 하는 운동이 아닌, 단체로 하는 운동에서 페이스 메이커와의 
    거리 조절, 낙오 방지에 대한 필요성을 느꼈다.

## 기대효과

* 런닝 일정을 편하게 공유 할 수 있음
* 런닝의 출발 지점, 도착 지점을 통해 러너들이 경로를 찾는데 도움을 줌
* 페이스메이커를 표시하여 낙오 방지와 페이스 조절

## 사용 기술 및 환경

* FrontEnd - Android(Kotiln, Java, Android Studio)
* BackEnd - MSSQL(SSMS), Spring Boot(Kotiln, Intellj) 

## ToDo

* FaceMaker 위치 표시
* FaceMaker 현제 위치 DB에 지속적으로 업데이트
* 출발 지점, 도착 지점 표시
* 러닝 일정 생성, 조회, 삭제
* ~~러닝 일정 수정
* ~~Runner에서 Fackmaker의 실시간 위치 이동을 표시하는 부분
* ~~FaceMaker, Runner 의 로그인

## Mock Up

![캡처](https://user-images.githubusercontent.com/46432795/70389259-4fd67b80-1a00-11ea-882e-f9d930d23fd1.JPG)

## 개발

Android 1 조길호 - ListView, Web App Server와 통신, 프로젝트 
* https://github.com/DeathByS/ForRunngingCrewProject

Android 2 황근호 - Google Map Api 관련 부분
* https://github.com/ITRecipe/korcham_Android.Project

DB And Spring Boot 양정우 
* https://github.com/jeong-w-93/FRCProject

## 실행화면

#### 시작화면 
> ![Start1](https://user-images.githubusercontent.com/46432795/70387648-06c7fc80-19eb-11ea-95bc-d196b0171f4c.png)
> * listView로 구현
> * 각각의 일정 삭제 버튼  
> * "+" 버튼 누를 시 일정 추가 화면으로 이동 
 
![Start2](https://user-images.githubusercontent.com/46432795/70387804-ef8a0e80-19ec-11ea-959c-e1f1b9e54973.png)
> * 테스트9 일정을 삭제 후 화면
> * 삭제 후 바로 화면에서 사라짐
> * DB에서도 같이 삭제 됨

#### 일정 추가 화면

![Todo](https://user-images.githubusercontent.com/46432795/70389078-e5bcd700-19fd-11ea-9d3e-a10e890c8b15.png)

> * 보이는 지도를 클릭하여 출발 지점, 
    도착 지점을 설정

        청록색 - 시작 지점 
        연두색 - 도착 지점
> * 모임 이름, 날짜, 시간, facemakerID를 입력
> * "+" 버튼을 누르면 일정 추가 후 시작 화면으로 이동
> * 시작 지점과 도착 지점을 설정하지 않으면 
    + 버튼이 활성화 되지 않음 

#### 페이스메이커 위치 창

![FaceMaker](https://user-images.githubusercontent.com/46432795/70389112-33394400-19fe-11ea-991c-f37cfb0ce6df.png)

> * listView의 각 일정을 클릭하면 화면으로 이동
> * 각 일정의 출발 지점, 도착 지점, 페이스메이커 위치를 표시
> * 위치는 마커로 표시
> * 붉은색 마커 – 페이스 메이커

## 아쉬운 점 및 개선 해야 될 부분

* 로그인 미구현
* 페이스메이커 / 러너 선택 미 구현
* 일정 생성 부분의 날짜 선택 / 시간 선택을 텍스트박스가 아닌 캘린더, 리스트박스로 변경
* 단위 테스트 / UI 테스트(Espresso) 등을 해보지 못함
  


