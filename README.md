# dcx-tech API
```
  DCX팀 기술담당 Server API
```

## 개발환경
* OS : Windows10
* Framework : Spring Framework(전자정부 egov 프레임워크)
* IDE : Eclipse 2021-03 (4.19.0)
* Language : Java
* Design Pattern : MVCS

## API 목록
* L.POINT Certification
* L.POINT CheckLPoint
* L.POINT useLpointUsingCustNo
* L.POINT use
* L.POINT cancel

## 아키텍쳐 구성
+ dcx
  + comn (공통)
    + exception
    + util
  + lpoint (엘포인트 API)
    + controller 
    + qc (DB 연결 시 사용 예정)
    + prop (Properties)
    + service
    + vo (Value Object)
    + svo (Send Value Object)
    + rvo (Receive Value Object)
    + tran (Transfer)
      * no (전문번호 헤더)

## 사용 라이브러리
* Lombok
* Google Guava
* jackson

## Contributer
* 이해원
* 황견주
* DCX Team
