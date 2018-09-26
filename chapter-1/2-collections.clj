; 클로저 데이터를 컬렉션에 담기
; 1장 클로저의 구조 p7 ~ p15


;------;
;** 1. 리스트 컬렉션

;클로저에서 리스트를 만드려면?
'(1 2 "jam" :marmalade-jar)
;=> (1 2 "jam" :marmalade-jar)

;쉼표를 사용하면?
'(1, 2, "jam", :bee)
;=> (1 2 "jam" :bee)

;리스트의 첫번째 요소를 가져오려면!
(first '(:rabbit :poket-watch :marmalade :door))
;=> :rabbit

;리스트의 첫번째 요소를 제외한 나머지 전체 요소들을 가져오려면!
(rest '(:rabbit :poket-watch :marmalade :door))
;=> (:poket-watch :marmalade :door)

;first와 rest를 함께 써보자
(first (rest '(:rabbit :poket-watch :marmalade :door)))
;=> :poket-watch
(first (rest (rest '(:rabbit :poket-watch :marmalade :door))))
;=> :marmalade
(first (rest (rest (rest '(:rabbit :poket-watch :marmalade :door)))))
;=> :door
(first (rest (rest (rest (rest '(:rabbit :poket-watch :marmalade :door))))))
;=> nil

; 리스트를 단번에 생성하지 않고 조금씩 만들어 가려면? cons 함수!
(cons 5 '())
;=> (5)

; nil로도 동일한 일을 할 수 있다.
(cons 5 nil)
;=> (5)

; 더 큰 리스트를 만들어보자
(cons 4 (cons 5 nil))
;=> (4 5)

; 더 큰거
(cons 3 (cons 4 (cons 5 nil)))
;=> (3 4 5)

; 더더더더 큰거
(cons 2 (cons 3 (cons 4 (cons 5 nil))))
;=> (2 3 4 5)

; 인용 기호(')와 list 함수를 사용해보자
'(1 2 3 4 5)
;=> (1 2 3 4 5)
(list 1 2 3 4 5)
;=> (1 2 3 4 5)


;------;
;** 2. 벡터 컬렉션

; 백터는 대괄호로 둘러싸서 만든다
[:jar1 1 2 3 :jar2]
;=> [:jar1 1 2 3 :jar2]

; 백터에서도 first와 rest 연산이 동작한다
(first [:jar1 1 2 3 :jar2])
;=> :jar1
(rest [:jar1 1 2 3 :jar2])
;=> (1 2 3 :jar2) ; 그치만 평가 결과는 리스트

;nth 함수
(nth [:jar1 1 2 3 :jar2] 0)
;=> :jar1
(nth [:jar1 1 2 3 :jar2] 2)
:=> 2

;last 함수
(last [:rabbit :poket-watch :marmalade])
;=> :marmalade
;last 함수를 리스트에 적용
(last '(:rabbit :poket-watch :marmalade))
;=> :marmalade

;count 함수
(count [1 2 3 4])
;=> 4
;count 함수를 리스트에서 써보기 (*연습)
(count '(1 2 3 4))
;=> 4

;conj 함수 (컬렉션에 하나 이상의 요소를 추가)
(conj [:toast :butter] :jam)
;=> [:toast :butter :jam]
(conj [:toast :butter] :jam :honey)
;=> [:toast :butter :jam :honey]
;(*연습)
(conj [:jam1 :jam2] :jam3 :jam4)
;=> [:jam1 :jam2 :jam3 :jam4]
(conj [:jam1 :jam2] :jam3 :jam4 :jam5 :jam6)
;=> [:jam1 :jam2 :jam3 :jam4 :jam5 :jam6]

; conj 함수를 리스트에 쓰면?
(conj '(:toast :butter) :jam)
;=> (:jam :toast :butter)
(conj '(:toast :butter) :jam :honey)
;=> (:honey :jam :toast :butter)
;(*연습)
(conj '(:toast :butter) :jam :honey :marmalade)
;=>(:marmalade :honey :jam :toast :butter)
(conj '(:jam1 :jam2 :jam3) :jam4 :jam5 :jam6)
;=> (:jam6 :jam5 :jam4 :jam1 :jam2 :jam3)


;------;
;** 3. 맵 컬렉션

; 맵은 중괄호로 둘러싼다
{:jam1 "strawberry" :jam2 "blackberry"}
;=> {:jam1 "strawberry", :jam2 "blackberry"}

; 가독성을 위해 맵에서 쉼표를 사용할 수 있다
{:jam1 "strawberry", :jam2 "blackberry"}
;=> {:jam1 "strawberry", :jam2 "blackberry"}

; get 함수로 맵에서 값 가져오기
(get {:jam1 "strawberry" :jam2 "blackberry"} :jam2)
;=> "blackberry"

; 키가 없을 경우 디폴트 값을 줄 수 있다
(get {:jam1 "strawberry" :jam2 "blackberry"} :jam3 "not found")
;=> "not found"

; 맵의 키가 키워드라면?
(:jam2 {:jam1 "strawberry" :jam2 "blackberry" :jam3 "marmalade"})
;=> "blackberry"

; keys 함수
(keys {:jam1 "strawberry" :jam2 "blackberry" :jam3 "marmalade"})
;=> (:jam1 :jam2 :jam3)

; vals 함수
(vals {:jam1 "strawberry" :jam2 "blackberry" :jam3 "marmalade"})
;=>("strawberry" "blackberry" "marmalade")

; assoc 함수
(assoc {:jam1 "red" :jam2 "black"} :jam1 "orange")
;=> {:jam1 "orange", :jam2 "black"}
;(*연습)
(assoc {:jam1 "strawberry" :jam2 "blackberry" :jam3 "marmalade"} :jam2 "banana" :jam1 "apple")
;=> {:jam1 "apple", :jam2 "banana" :jam3 "marmalade"}

; dissoc 함수
(dissoc {:jam1 "strawberry" :jam2 "blackberry"} :jam1)
;=> {:jam2 "blackberry"}

; merge 함수
(merge {:jam1 "red" :jam2 "black"}
        {:jam1 "orange" :jam3 "red"}
        {:jam4 "blue"})
;=> {:jam1 "orange", :jam2 "black", :jam3 "red", :jam4 "blue"}
;(*연습)
(merge {:jam1 "orange" :jam3 "blue"}
       {:jam1 "red" :jam4 "yellow" :jam2 "white"}
       {:jam1 "black" :jam3 "green"})
;=> {:jam1 "black", :jam3 "green" :jam4 "yellow", :jam2 "white"}


;------;
;** 4. 집합 컬렉션
