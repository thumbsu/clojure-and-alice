; 집합을 사용해 유일한 데이터의 컬렉션 표현하기
; 1장 클로저의 구조 p15 ~ p18


;------;
;** 4. 집합 컬렉션

; 집합은 #{}로 둘러싼다
#{:red :blue :white :pink}
;=> #{:red :blue :white :pink}

; 집합을 생성할 때 중복은 허용되지 않음
#{:red :blue :white :pink :pink}
;=> IllegalArgumentException Duplicate key: :pink  clojure.lang.PersistentHashSet.createWithCheck (PersistentHashSet.java:68)

; 집합이기 때문에 집합연산이 가능하다
(clojure.set/union #{:r :b :w} #{:w :p :y})
;=> #{:y :r :b :p}
(clojure.set/difference #{:r :b :w} #{:w :p :y})
;=> #{:r :b}
(clojure.set/intersection #{:r :b :w} #{:w :p :y})
;=> #{:w}

; set 함수를 사용하여 다른 종류의 컬렉션을 집합으로 바꿔보자
(set [:rabbit :rabbit :watch :door])
;=> #{:door :watch :rabbit}
(set {:a 1 :b 2 :c 3})
;=> #{[:c 3] [:b 2] [:a 1]}
;(*연습)
(set '(:rabbit :rabbit :watch :door))
;=> #{:door :watch :rabbit}

; 집합에서 요소를 가져오려면?
(get #{:rabbit :door :watch} :rabbit)
;=> :rabbit
(get #{:rabbit :door :watch} :jar)
;=> nil

; 키워드를 사용해서 직접 가져오기
(:rabbit #{:rabbit :door :watch})
;=> :rabbit
; 키워드가 아닌 요소도 가능할까? (*연습)
(rabbit #{rabbit door watch})
;=> CompilerException java.lang.RuntimeException: Unable to resolve symbol: rabbit in this context, compiling:(null:1:1)

; 집합 자체를 함수로 사용해서 가져올 수 있음
(#{:rabbit :door :watch} :rabbit)
;=> :rabbit

; 집합에 요소가 있는지 확인하려면? contains? 함수
(contains? #{:rabbit :door :watch} :rabbit)
;=> true
(contains? #{:rabbit :door :watch} :jam)
;=> false

; conj 함수로 집합에 요소를 추가하자
(conj #{:rabbit :door} :jam)
;=> #{:door :rabbit :jam}

; disj 함수로 집합에 요소를 제거하자
(disj #{:rabbit :door} :rabbit)
;=> {:door}
