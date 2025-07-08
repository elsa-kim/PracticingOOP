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

- `String.split()`
  - `split(String regex)`: 내부적으로 `split(regex, 0)` 호출
  - `split(String regex, int limit)`
    - `regex`: 문자열을 나눌 기준, 메타 문자를 단순 문자로 사용하려면 이스케이프 필요
    - `limit`: 나눌 문자열의 최대 개수 또는 동작 방식을 제어하는 값
      - 양수 N: 최대 N개 요소만 반환(N-1번만 나누고 나머지 마지막에 다 반환)
      - 0: 기본값. 나눈 후 배열 끝의 빈 문자열 제거
      - 음수: 나눈 후 빈 문자열 포함 전부 반환
