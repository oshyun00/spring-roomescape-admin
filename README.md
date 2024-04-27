## 방탈출 예약 관리

---

- **구현 목표**: 관리자 페이지에서 방탈출 게임 예약 현황을 조회하고, 예약을 생성하거나 삭제할 수 있다.

### 기능 요구 사항

#### 1단계

- [x] `localhost:8080/admin` GET 요청 시 어드민 메인 페이지를 응답한다

#### 2단계

- [x] `/admin/reservation` GET 요청 시 예약 관리 페이지(reservation-legacy.html)를 응답한다
    - [x] 예약 관리 페이지 로드 시 예약 목록을 표시한다
    - [x] `/reservations` GET 요청 시 모든 예약을 조회한다

#### 3단계

- [x] 예약을 추가할 수 있다
    - [x] 예약하려는 날짜, 예약자 이름, 시간을 받는다
    - [x] `/reservations` POST 요청 시 전달 받은 예약을 저장한다
- [x] 예약을 삭제할 수 있다
    - [x] 삭제하려는 예약의 예약 번호(id)를 받는다
    - [x] `/reservations/{id}` DELETE 요청 시 해당 id를 가진 예약을 삭제한다

#### 4단계

- [x] h2 데이터베이스를 연결한다
- [x] 테이블 스키마를 정의한다

#### 5단계

- [x] 예약 조회 시 데이터베이스에서 데이터를 가져온다

#### 6단계

- [x] 예약 추가 시 데이터베이스를 활용한다
- [x] 예약 삭제 시 데이터베이스를 활용한다

#### 7단계

- [x] `/admin/time` GET 요청 시 예약 시간 관리 페이지를 응답한다.
- [x] 예약 가능한 시간을 추가할 수 있다
    - [x] 시간을 HH:mm 형태로 받는다
    - [x] `/times` POST 요청 시 전달받은 시간을 저장한다
- [x] 예약 가능한 시간 목록을 조회할 수 있다
    - [x] `/times` GET 요청 시 존재하는 모든 시간을 조회한다
- [x] 예약 가능 시간을 삭제할 수 있다
    - [x] 삭제하려는 시간의 시간 번호(id)를 받는다
    - [x] `/times/{id}` DELETE 요청 시 해당 id를 가진 시간을 삭제한다

#### 8단계

- [x] `/admin/reservation` GET 요청 시 예약 관리 페이지(reservation.html)를 응답한다
- [x] reservation 테이블과 reservation_time의 관계를 설정한다
- [x] 예약의 시간 타입을 String에서 ReservationTime 객체로 수정한다
- [x] 예약 시 시간 테이블에 저장된 시간만 선택할 수 있도록 한다
    - [x] 예약 추가 시, 시간을 문자열 형태가 아닌 ReservationTime의 식별자(id)로 입력한다

#### 9단계

- [x] 레이어드 아키텍처를 적용한다

#### 10단계

- [ ] 콘솔 UI를 지원한다
    - [ ] 사용자 입출력을 담당하는 view를 구현한다
- [ ] 시간 관리 기능을 콘솔에서 사용할 수 있다
    - [ ] 모든 예약 시간을 조회할 수 있다
    - [ ] 예약 시간을 추가할 수 있다
    - [ ] 예약 시간을 삭제할 수 있다
- [ ] 예약 관리 기능을 콘솔에서 사용할 수 있다
    - [ ] 모든 예약을 조회할 수 있다
    - [ ] 예약을 추가할 수 있다
    - [ ] 예약을 삭제할 수 있다
- [ ] 콘솔 UI로 생성되는 데이터는 데이터베이스가 아닌 메모리에 저장한다
