# 공유-일정-공감(only 투표 기능)

> 모두가 만족할 수 있는 일정을 세우느라 힘드시지 않았나요?<br/> **ZeroOneZero**는 여행 계획을 세울 때 친구들 간에 원활한 의사결정을 돕습니다. 더 이상 여행 일정을 혼자 짜느라 스트레스 받지 마세요! <br/><br/> **ZeroOneZero**와 함께하면 친구들 모두가 의견을 내고, _일정을 투표로 쉽게 결정할 수 있어요_. 또한, 투표에 참여하지 않으면 벌칙이 기다리고 있어요! 모두가 참여하는 의사결정을 즐겁게 경험하세요

<br/>
🔗 서비스 링크 : https://zero-one-zero-web.vercel.app/
<br/></br>
🚀 투표를 만들지 않고 빠르게 둘러보실 분들을 위한 링크 🚀

- [테스트 링크1](https://zero-one-zero-web.vercel.app/?roomCode=c5737b00-9045-40da-8884-24a901f3d915)
- [테스트 링크2](https://zero-one-zero-web.vercel.app/?roomCode=a55cd91a-17e9-4347-ae7e-60b0643c660a)

⚠️현재 공유-일정-공감 서비스의 투표 기능만 구현되어 있습니다.

</br>

![대표이미지](https://github.com/kis-sprint/zero-one-zero-web/assets/62870362/fb04b638-f28a-4efb-a395-3bf38fc1403c)

## 🤼‍♂️ Team

| 닉네임 | 역할 | 1차 스프린트 | 2차 스프린트 |
| --- | --- | --- | --- |
| [키유](https://github.com/hyunjoogo) | 진행자 | 일정 조율 및 개발 참여 | 페어 프로그래밍 네비게이터 |
| 단지 | 기획 | 서비스 기획 |  |
| [니노](https://github.com/lynnday36) | BE | 투표 프로세스 API 설계 및 작성 | 건의사항 수정 및 서버  배포 |
| [앨빈](https://github.com/choegyumin) | FE | 개발 최고 의사결정권 |
| [제리](https://github.com/orgs/kis-sprint/people/chuhoon) | FE | UI 최고 의사결정권 |
| [팡이](https://github.com/smosco) | FE | 투표 코드 입장, 참가자 선택 | API 연결, 배포, 리드미 작성 |
| [하이수](https://github.com/DDUDII) | FE | 투표생성 |  |
| [시뮨](https://github.com/chaeyun-sim) | FE | 공유 및 종료 모달 |  |
| [헬로월드](https://github.com/HelloWorldDogs223) | FE | 투표 수행, 결과 및 벌칙 상장 |  |

</br>

## 📆 Sprint Schedule

**1차 스프린트 : 2023년 9월 4일 ~ 9월 11일**

**2차 스프린트 : 2023년 9월 22일 ~ 9월 27일 (니노, 키유, 팡이)**

</br>

## 📚 Stack

- **NextJS**
- **Typescript**
- **Java**
- **Spring Boot**

</br>

## ✨Feature

### 1. 투표 생성 폼

- 제목, 설명
- 투표 항목 추가 ( 최대 10개 )
- 참가자 명단 추가 ( 최대 12명 )
- 비밀번호 설정 ( 투표 종료에 필요 )

![투표만들기](https://github.com/kis-sprint/zero-one-zero-web/assets/62870362/2a1a85b0-a78e-4942-83a2-cdffb1106019)

</br>

### 2. 투표 입장

- 참여 코드 집접 입력
- 공유 받은 URL로 접속시 참여 코드 자동 입력

<img width="279" alt="투표입장" src="https://github.com/kis-sprint/zero-one-zero-web/assets/62870362/1bf600b7-24a8-43c1-bd0b-e708cc84e2be">

</br></br>

### 3. 참가자 선택 및 투표하기

- 자신의 이름을 선택하여 투표할 수 있음
  - 투표 완료한 사람은 투표 마크 표시
- 투표한 사람은 결과를 볼 수 있음
- 투표 공유하기 기능
  - 참여 코드가 포함된 링크 공유

<img width="784" alt="투표하기" src="https://github.com/kis-sprint/zero-one-zero-web/assets/62870362/3a891181-2478-42a4-a4cb-d4c96814ee0e">

</br></br>

### 4. 투표 결과

- 투표 결과 보기
- 투표 작성자는 비밀번호를 입력해 투표를 종료할 수 있음
- 종료된 투표일 경우 미참여자 상장 수여 ( 다운로드 가능 )

<img width="545" alt="투표결과" src="https://github.com/kis-sprint/zero-one-zero-web/assets/62870362/2142f81f-5d8a-4e92-9673-0d2181475696">
