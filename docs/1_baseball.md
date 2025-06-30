# 숫자 야구

[요구사항](../java-baseball-6/README.md)  
[설계](../java-baseball-6/docs/README.md)

## 피드백

- mvc 패턴에 따라 패키지 나누기
  - 패키지 구조
    - model
    - view: input과 output 따로 분리
    - controller
  - 어플리케이션은 순수하게 프로그램 실행만 담당
  - 컨트롤러가 중심
- 상수로 빼기: 유지보수 쉽게 가능하도록
- 값 객체 만들기: 도메인 개념을 명확하게 표현 가능, 불변성 유지
- 일급컬렉션 사용: 외부에서 컬렉션 직접 수정하지 못하게 보호
- getter 남발하지 않기: 객체의 내부 상태를 꺼내기보단, 객체에게 메시지 보내기
- 선언과 초기화 분리하지 말기: 코드 복잡해지면 문제 발생 가능

## 학습 내용 정리

- 값 객체(Value Object, VO): 하나의 의미 있는 값을 표현하는 객체

  - 불변성 유지 (final, 생성자에서 모든 값 초기화, setter 없음)
  - 값이 같으면 동일한 객체로 간주 (equals, hashCode 오버라이드 필수)
  - 도메인의 한 개념을 나타냄 (ex. Money, Email, Name)
  - 조회 시 getter 통해 값 그대로 반환

- 일급컬렉션(First-Class Collection): 하나의 컬렉션(List, Set, Map 등)을 단일 필드로 감싼 클래스

  - 컬렉션 관련 비즈니스 로직을 내부에 응집
  - 불변성 유지 (내부 컬렉션을 private final, 방어적 복사, Collections.unmodifiableList() 등 사용)
  - 조회 시 새로 생성해서 반환하거나 읽기 전용 컬렉션으로 노출

- `@Embedded`, `@Embeddable`

  - `@Embeddable` 붙은 클래스는 다른 엔티티의 컬럼 일부로 삽입
  - `@Embedded`를 붙이면, JPA는 해당 `@Embeddable` 클래스의 필드들을 엔티티 컬럼으로 flat하게 넣어줌
  - 사용법

    ```Java
    # Address.java
    @Embeddable
    public class Address {
        private String city;
        private String street;
        private String zipcode;

        ...
    }

    ---------------------
    # Member.java
    @Entity
    public class Member {
        @Id
        @GeneratedValue
        private Long id;

        @Embedded
        private Address address;

        ...
    }
    ```

    - `Member` 테이블에 `city`, `street`, `zipcode` 컬럼이 함께 들어감

  - 하나의 엔티티에 같은 타입 값 객체 여러개 포함 시 `@AttributeOverrides` 사용하여 컬럼명 다르게 지정

    ```java
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "home_city")),
        @AttributeOverride(name = "street", column = @Column(name = "home_street")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "home_zipcode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "work_city")),
        @AttributeOverride(name = "street", column = @Column(name = "work_street")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "work_zipcode"))
    })
    private Address workAddress;
    ```

- 생성자: 객체의 생성만 담당, 불필요한 로직은 피해야 함

  - 역할
    - 필수 필드 초기화
    - 유효성 검증
    - 불변 상태 보장
  - 복잡한 생성 로직 필요 시: 정적 팩토리 메서드 또는 Builder 패턴을 사용해서 분리

    ```java
    public static User fromRawData(String raw) {
        String[] parts = raw.split(",");
        return new User(parts[0], Integer.parseInt(parts[1]));
    }
    ```

    - 정적 팩토리 메서드 (Static Factory Method): `new` 키워드 대신 `static 메서드`로 객체 생성

      ```java
      public class User {
      private final String name;
      private final int age;

          private User(String name, int age) {
              this.name = name;
              this.age = age;
          }

          public static User of(String name, int age) {
              return new User(name, age);
          }

      }
      ```

    - Builder 패턴: 많은 인자를 가진 객체를 가독성 있게 생성하고, 유연하게 값 설정 가능

      ```java
      public class User {
      private final String name;
      private final int age;

      private User(Builder builder) {
          this.name = builder.name;
          this.age = builder.age;
      }

      public static class Builder {
          private String name;
          private int age;

          public Builder name(String name) {
              this.name = name;
              return this;
          }

          public Builder age(int age) {
              this.age = age;
              return this;
          }

          public User build() {
              return new User(this);
          }
        }
      }
      ```

    - 각 방식 비교

      | 구분         | 생성자     | 정적 팩토리 메서드 | Builder 패턴            |
      | ------------ | ---------- | ------------------ | ----------------------- |
      | 표현 방식    | `new` 사용 | `클래스명.of()` 등 | `new Builder().build()` |
      | 이름 부여    | 불가능     | 가능               | 가능                    |
      | 유연성       | 낮음       | 중간               | 높음                    |
      | 필드 많을 때 | 불편       | 불편               | 유리                    |
      | 선택적 인자  | 불가능     | 가능 (오버로드)    | 가능                    |
      | 가독성       | 낮음       | 높음               | 가장 좋음               |
