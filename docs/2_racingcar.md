# 자동차 경주

[요구사항](../java-racingcar-6/README.md)  
[설계](../java-racingcar-6/docs/README.md)  
[코드](../java-racingcar-6/)

## 학습 내용 정리

- String Validation

  - `null`: 참조 없음
  - `empty`: 빈 문자열(길이 0)
  - `blank`: 공백 문자열(공백 문자로만 구성)

  | 값 \ 검증 | `isNull()` | `isEmpty()` | `isBlank()` |
  | --------- | ---------- | ----------- | ----------- |
  | `null`    | ✅         | ❌ (NPE)    | ❌ (NPE)    |
  | `""`      | ❌         | ✅          | ✅          |
  | `" "`     | ❌         | ❌          | ✅          |
  | `"abc"`   | ❌         | ❌          | ❌          |

- 리스트 복사 방식

  - `Collections.unmodifiableList(cars)`
    - 반환타입: 불변 Wrapper
      - 원본 cars가 변경되면 영향 받음
    - 내부 요소 참조: 같은 객체
  - `new ArrayList<>(cars)`
    - 반환타입: 새 가변 ArrayList
    - 내부 요소 참조: 같은 객체
  - `Arrays.asList(cars.toArray())`
    - 반환타입: 고정 크기 리스트
      - set 가능
      - 추가, 삭제는 불가
    - 내부 요소 참조: 복사된 객체
  - `List.of(cars.toArray())`
    - 반환타입: 불변 리스트(`UnsupportedOperationException` 발생)
      - set도 불가
    - 내부 요소 참조: 복사된 객체
  - `List.copyOf(cars)`
    - 반환타입: 불변 리스트
    - 내부 요소 참조: 같은 객체
    - 원본 리스트 또는 요소에 null이 있으면 NullPointerException 발생

## todo

- 테스트코드 작성
