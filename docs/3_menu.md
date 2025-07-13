# 점심 메뉴 추천

[요구사항](../java-menu/README.md)  
[설계](../java-menu/docs/README.md)  
[코드](../java-menu/)

### 피드백

- enum 영어로 작성하기
- 메서드 배치 기준 세우기

  1. 접근 제어자 기준

     - `public` → `protected` → `private`

  2. public 메서드 정렬 기준

     1. 상태를 변경하는 메서드(command): 도메인의 핵심 행위
     2. 상태를 판단하는 메서드(boolean return)
     3. 단순 조회 메서드: 객체 내부 값을 그대로 노출

- DTO 활용해보기
  - View(입력)/외부 요청을 도메인으로 바꾸는 중간 역할을 수행
  - 데이터 파싱, 형 변환 책임만 가지도록 하고, 비즈니스 검증은 도메인에서 수행
- 도메인 설계 시 복합 검증 여부에 따른 설계
  - 복합 검증이 있는 경우 → 하나의 도메인으로 묶기
  - 복합 검증이 없다면 → 각각을 개별 도메인으로 분리

### 학습 내용 정리

- 도메인 서비스 vs 응용 서비스

  - 도메인 서비스
    - 도메인 계층 (Domain Layer)
    - 하나의 도메인 엔티티나 밸류 객체에서 처리하기 애매한 도메인 로직을 위임
    - 도메인 규칙(비즈니스 규칙)을 책임짐
    - 특징
      - 상태를 가지지 않음
      - 도메인 객체들을 조합하여 비즈니스 규칙을 구현
      - 도메인 모델의 일부이므로, 비즈니스 용어 사용
    - 예시
      ```java
      public class TransferService {
          public void transfer(Account from, Account to, Money amount) {
              if (!from.canWithdraw(amount)) {
                  throw new InsufficientFundsException();
              }
              from.withdraw(amount);
              to.deposit(amount);
          }
      }
      ```
  - 응용 서비스

    - 응용 계층 (Application Layer)
    - 도메인 객체를 조합하여 유스케이스를 수행
    - 트랜잭션 관리, 도메인 서비스 호출, 저장소 접근 등 흐름 조율 담당
    - 특징
      - 비즈니스 규칙은 갖지 않음
      - 주로 도메인 객체를 호출하고 결과를 리턴하거나 저장
      - 컨트롤러와 도메인 계층 사이의 퍼사드 역할을 함
    - 예시

      ```java
      @Service
      public class TransferApplicationService {
          private final AccountRepository repository;
          private final TransferService transferService;

          public void transfer(TransferRequest request) {
              Account from = repository.findById(request.getFrom());
              Account to = repository.findById(request.getTo());
              transferService.transfer(from, to, request.getAmount());
              repository.saveAll(from, to);
          }
      }
      ```

- 퍼사드 패턴(Facade Pattern)

  - 복잡한 서브시스템(여러 객체, 로직)을 감추고, 간단한 인터페이스로 하나의 진입점을 제공하는 디자인 패턴
  - 외부에서는 간단한 메서드 하나로 복잡한 과정을 실행할 수 있게 함
  - 시스템 사용성을 높이고, 느슨한 결합을 유도
  - 일반적으로 응용 서비스가 퍼사드 역할 담당
  - 장점
    - 클라이언트 코드가 서브 시스템 내부 구조에 의존하지 않게 함
    - 코드 재사용성과 유지보수성 증가
    - 복잡한 로직을 감추고 명확한 책임과 역할 분리
  - 예시

    ```java
    public class HomeTheaterFacade {
        private Projector projector;
        private Amplifier amplifier;
        private DvdPlayer dvd;

        public HomeTheaterFacade(Projector projector, Amplifier amplifier, DvdPlayer dvd) {
            this.projector = projector;
            this.amplifier = amplifier;
            this.dvd = dvd;
        }

        public void watchMovie(String movie) {
            projector.on();
            amplifier.on();
            dvd.play(movie);
        }
    }

    // 클라이언트 코드
    HomeTheaterFacade homeTheater = new HomeTheaterFacade(projector, amplifier, dvd);
    homeTheater.watchMovie("Inception");  // 복잡한 내부 호출을 감추고 하나의 메서드로 처리
    ```

- `LinkedHashMap`: 해시 버킷 + 배열 구조를 사용하며, 이중 연결 리스트로 순서 정보를 함께 저장

  - `HashMap` vs `LinkedHashMap`
    | 항목 | `HashMap` | `LinkedHashMap` |
    | ------------ | ---------- | ------------------------ |
    | 순서 유지 | 없음 | 입력 순서 유지 |
    | 메모리 사용량 | 적음 | 더 큼 (순서 정보 저장) |
    | 성능 | 조금 더 빠름 | 조금 느림 (링크 관리 때문) |
    | LRU 캐시 사용 가능 | 직접 구현 필요 | `accessOrder=true`로 가능 |

  - 생성자 및 기본값

    ```java
    Map<K, V> map = new LinkedHashMap<>();
    Map<K, V> map = new LinkedHashMap<>(int initialCapacity);
    Map<K, V> map = new LinkedHashMap<>(int initialCapacity, float loadFactor);
    Map<K, V> map = new LinkedHashMap<>(int initialCapacity, float loadFactor, boolean accessOrder);
    ```

    - `initialCapacity`: 초기 버킷 크기
      - 기본값: 16(16개의 엔트리)
    - `loadFactor`: 해시 테이블이 얼마나 차야 리사이징할지 결정
      - 기본값: 0.75f(75%)
    - `accessOrder`: 순서 유지 방식
      - `false` (기본값): 입력 순서 유지
      - `true`: 접근 순서 유지 -> LRU 캐시 구현 시 사용
