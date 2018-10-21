# clojure-and-alice
<이상한 나라의 앨리스> 이야기와 함께하는 클로저 공부 \
[클로저 시작하기](http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&barcode=9788966261802) 책에 나오는 코드 + 혼자 공부했던 코드를 업로드함
> clojure-and-alice인 이유? \
> ==> 저자인 캐린 마이어가 코드예제를 더 재미있게 하기위해 <이상한 나라의 앨리스> 이야기를 소재 삼아 스토리텔링 형식으로 진행함.
### 작성 규칙
- 책 목차의 순서로 진행
- 책에 작성된 코드와 혼자 공부하면서 추가한 코드 분리해서 표시
- 코드에 대한 설명은 블로그에 작성
- 클로저 식이 평가된 결과는 해당 책의 작성 법과 동일하게 ';=>' 를 사용
- 예시는 아래와 같다
```clojure
; 아래의 클로저 식에 대한 간단한 제목 혹은 설명
(+ 1 1) ;클로저 식
;=> 2 ;클로저 식의 평가 결과
```
- 책에 나온 코드가 아닌 연습 코드일 땐 (*연습)을 붙임
- 코드에 대한 설명를 블로그에 올리고 나면 아래 목차에 링크 붙임
### 블로그 정리 보기
- [지수와로그 클로저 정리 목록](https://thumbsu.github.io/tags/clojure/)
### 목차
1. chapter-1 클로저의 구조
> - 1-simple-value ([단순값으로 처음 시작하기](https://thumbsu.github.io/2018/09/26/get-started-clojure/)) 
> - 2-collections ([클로저 데이터를 컬렉션에 담기 - 리스트, 벡터, 맵](https://thumbsu.github.io/2018/09/27/clojure-collections-1/))
> - 3-collections(Sets) ([집합을 사용해 유일한 데이터의 컬렉션 표현하기](https://thumbsu.github.io/2018/10/02/clojure-collections-2/))
> - 4-symbol-and-binding ([심볼과 바인딩, 함수만들기, 이름공간](https://thumbsu.github.io/2018/10/03/symbols-and-function]))
2. chapter-2 제어문과 함수형 변환
> - 1-controlling-the-flow-with-logic (논리에 따라 흐름 제어하기)
> - 2-functions-creating-functions (함수를 만드는 함수)
> - 3-destructing (구조분해)