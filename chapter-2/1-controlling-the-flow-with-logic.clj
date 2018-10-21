; 논리에 따라 흐름 제어하기
; 2장 제어문과 함수형 변환 p31 ~ p45


;------;
;** '클로저 시작하기' 책에서 말하는 식(expression)과 형식(form)

; 식(expression)
(first [1 2 4])
;=> 1

; 형식(form)
(first [:a :b :c])
;=> :a

; 형식 아님
(first)
;=> ArityException


;------;
;** 논리 연산

; 불린(boolean) 데이터 타입의 class 확인
(class true)
;=> java.lang.boolean

; 불린 true 값인지 확인
(true? true)
;=> true
(true? false)
;=> false

; 불린 false 값인지 확인
(false? true)
;=> false
(false? false)
;=> true

; 어떤 것이 없다는 것을 검사
(nil? nil)
;=> true
(nil? true)
;=> false
(nil? 1)
;=> false

; 부정(negation), 어떤 것의 반대를 검사
(not true)
;=> false
(not false)
;=> true
(not nil)
;=> true
(not "hi")
;=> false

; 두 인수의 값이 같은지 다른지 확인
(= :drinkme :drinkme)
;=> true
(= :drinkme 4)
;=> false

; 컬렉션의 동등비교
(= '(:drinkme :bottle) [:drinkme :bottle])
;=> true
(= '(:drinkme :bottle) #{:drinkme :bottle})
;=> false
(= [:drinkme :bottle] #{:drinkme :bottle})
;=> false

; (not (= x y)) = (not= x y)
(not= :drinkme :4)
;=> true


;------;
;** 컬렉션에 사용하는 논리검사

; 컬렉션이 비었는지 확인
(empty? [:table :door :key])
;=> false
(empty? [])
;=> true
(empty? {})
;=> true
(empty? '())
;=> true

; empty? 함수의 실제 정의
(defn empty? [coll]
  (not (seq coll)))

; seq 함수는?
(seq [1 2 3])
;=> (1 2 3)
(class [1 2 3])
;=> clojure.lang.PersistentVector
(seq [])
;=> nil

; 비어있는지 알고 싶을때 => empty?보다 seq 함수를 바로 사용하는 것이 관용적
(empty? [])
;=> true
(seq [])
;=> nil

; 컬렉션의 모든 요소에 대한 검사 결과가 참인지 확인
(every? odd? [1 3 5])
;=> true
(every? odd? [1 2 3 4 5])
;=> false

; 진위함수 만들어보기
(defn drinkable? [x]
  (= x :drinkme))
;=> #'user/drinkable?
(every? drinkable? [:drinkme :drinkme])
;=> true
(every? drinkable? [:drinkme :poison])
;=> false

; 무명함수를 사용
(every? (fn [x] (= x :drinkme)) [:drinkme :drinkme])
;=> true

; 무영함수 단축형으로 사용
(every? #(= % :drinkme) [:drinkme :drinkme])
;=> true

; 반대로 모든 요소가 거짓임을 판단하는 것은?
(not-any? #(= % :drinkme) [:drinkme :drinkme])
;=> false
(not-any? #(= % :drinkme) [:poison :poison])
;=> true

; 요소 중 일부라도 통과하는지를 알고 싶다면?
(some #(> % 3) [1 2 3 4 5])
;=> true

; 집합은 요소의 존재 여부를 확인하는 함수로 사용될 수 있음
(#{1 2 3 4 5} 3)
;=> 3
(some #{3} [1 2 3 4 5])
;=> 3
(some #{4 5} [1 2 3 4 5])
;=> 4

; 논리적 거짓인 값에 대해서는 조심
(some #{nil} [nil nil nil])
;=> nil
(some #{false} [false false false])
;=> nil


;------;
;** 흐름 제어 이용하기

; if
(if true "it is true" "it is false")
;=> "it is true"
(if false "it is true" "it is false")
;=> "it is false"
(if nil "it is true" "it is false")
;=> "it is false"
(if (= :drinkme :drinkme)
  "Try it"
  "Don't try it")
;=> "Try it"

; let과 if의 결합
(let [need-to-grow-small (> 5 3)]
  (if need-to-grow-small
    "drink bottle"
    "don't drink bottle"))
;=> "drink bottle"
(if-let [need-to-grow-small (> 5 1)]
  "drink bottle"
  "don't drink bottle")
;=> "drink bottle"

; when
(defn drink [need-to-grow-small]
  (when need-to-grow-small "drink bottle"))
;=> #'user/drink
(drink true)
;=> "drink bottle"
(drink false)
;=> nil

; when과 let의 결합
(when-let [need-to-grow-small true]
  "drink bottle")
;=> "drink bottle"
(when-let [need-to-grow-small false]
  "drink bottle")
;=> nil

; 조건식을 여러개 쓰고 싶다면 cond
(let [bottle "drink me"]
  (cond
    (= bottle "poison") "don't touch"
    (= bottle "drink me") "grow smaller"
    (= bottle "empty") "all gone"))
;=> "grow smaller"
(let [x 5]
  (cond
    (> x 10) "bigger than 10"
    (> x 4) "bigger than 4"
    (> x 3) "bigger than 3"))
;=> "bigger than 4"
(let [x 1]
  (cond
    (> x 3) "bigger than 3"
    (> x 10) "bigger than 10"
    (> x 4) "bigger than 4"))
;=> nil
(let [bottle "mystery"]
  (cond
    (= bottle "poison") "don't touch"
    (= bottle "drinkme") "grow smaller"
    (= bottle "empty") "all gone"
    :else "unknown"))
;=> "unknown"
(let [bottle "mystery"]
  (cond
    (= bottle "poison") "don't touch"
    (= bottle "drinkme") "grow smaller"
    (= bottle "empty") "all gone"
    "default" "unknown"))
;=> "unknown"

; 단축형인 case
(let [bottle "mystery"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"))
;=> IllegalArgumentException No matching clause: mystery
(let [bottle "mystery"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"
    "unknown"))
;=> "unknown"