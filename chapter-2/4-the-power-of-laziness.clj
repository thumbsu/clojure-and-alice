; 지연의 힘
; 2장 제어문과 함수형 변환 p50 ~ p54

(take 5 (range))
;=> (0 1 2 3 4)

(take 10 (range))
;=> (0 1 2 3 4 5 6 7 8 9)

(range 5)
;=> (0 1 2 3 4)

(class (range 5))
;=> clojure.lang.LongRange

(count (take 1000 (range)))
;=> 1000

(count (take 100000 (range)))
;=> 100000

(repeat 3 "rabbit")
;=> ("rabbit" "rabbit" "rabbit")

(class (repeat 3 "rabbit"))
;=> clojure.lang.Repeat

(take 5 (repeat "rabbit"))
;=> ("rabbit" "rabbit" "rabbit" "rabbit" "rabbit")

(count (take 5000 (repeat "rabbit")))
;=> 5000

(rand-int 10)
;=> 7

(rand-int 10)
;=> 9

(repeat 5 (rand-int 10))
;=> (4 4 4 4 4)

#(rand-int 10)
;=> #object[user$eval852$fn__853 0x3b6e983a "user$eval852$fn__853@3b6e983a"]

(#(rand-int 10))
;=> 3

(repeatedly 5 #(rand-int 10))
;=> (2 7 0 5 6)

(take 10 (repeatedly #(rand-int 10)))
;=> (1 7 9 9 6 9 7 3 1 2)

(take 3 (cycle ["big" "small"]))
;=> ("big" "small" "big") 

(take 6 (cycle ["big" "small"]))
;=> ("big" "small" "big" "small" "big" "small")

(take 3 (rest (cycle ["big" "small"])))
;=> ("small" "big" "small")