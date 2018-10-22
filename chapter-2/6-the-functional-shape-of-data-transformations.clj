; 함수형 프로그래밍에서의 데이터 변환
; 2장 제어문과 함수형 변환 p57 ~ p66


;------;
;** 궁극의 map

(def animals [:mouse :duck :dodo :lory :eaglet])
;=> #'user/animals
(#(str %) :mouse)
;=> ":mouse"
(map #(str %) animals)
;=> (":mouse" ":duck" ":dodo" ":lory" ":eaglet")
(class (map #(str %) animals))
;=> clojure.lang.LazySeq

(take 3 (map #(str %) (range)))
;=> ("0" "1" "2")
(take 10 (map #(str %) (range)))
;=> ("0" "1" "2" "3" "4" "5" "6" "7" "8" "9") 

(println "Look at the mouse!")
; Look at the mouse!
;=> nil

(def animal-print (map #(println %) animals))
;=> #'user/animal-print
animal-print
; :mouse
; :duck
; :dodo
; :lory
; :eaglet
;=> (nil nil nil nil nil)


(def animal-print (doall (map #(println %) animals)))
; :mouse
; :duck
; :dodo
; :lory
; :eaglet
;=> #'user/animal-print

(def animals
  ["mouse" "duck" "dodo" "lory" "eaglet"])
;=> #'user/animals
(def colors
  ["brown" "black" "blue" "pink" "gold"])
;=> #'user/colors
(defn gen-animal-string [animal color]
  (str color " - " animal))
;=> #'user/gen-animal-string
(map gen-animal-string animals colors)
;=> ("brown - mouse" "black - duck" "blue - dodo" "pink - lory" "gold - eaglet")

(def animals
  ["mouse" "duck" "dodo" "lory" "eaglet"])
;=> #'user/animals
(def colors
  ["brown" "black"])
;=> #'user/colors
(map gen-animal-string animals colors)
;=> ("brown - mouse" "black - duck")

(def animals
  ["mouse" "duck" "dodo" "lory" "eaglet"])
;=> #'user/animals
(map gen-animal-string animals (cycle ["brown" "black"]))
;=> ("brown - mouse" "black - duck" "brown - dodo" "black - lory" "brown - eaglet")


;------;
;** 궁극의 reduce

(reduce + [1 2 3 4 5])
;=> 15

(reduce (fn [r x] (+ r (* x x))) [1 2 3])
;=> 14

(reduce (fn [r x] (if (nil? x) r (conj r x)))
        []
        [:mouse nil :duck nil nil :lory])
;=> [:mouse :duck :lory]


;------;
;** 다른 유용한 데이터 처리 함수들

((complement nil?) nil)
;=> false
((complement nil?) 1)
;=> true
((complement odd?) 2)
;=> true

(filter (complement nil?) [:mouse nil :duck nil])
;=> (:mouse :duck)
(filter keyword? [:mouse nil :duck nil])
;=> (:mouse :duck)

(remove nil? [:mouse nil :duck nil])
;=> (:mouse :duck)

(for [animal [:mouse :duck :lory]]
  (str (name animal)))
;=> ("mouse" "duck" "lory")

(for [animal [:mouse :duck :lory]
      color [:red :blue]]
  (str (name color) (name animal)))
;=> ("redmouse" "bluemouse" "redduck" "blueduck" "redlory" "bluelory")

(for [animal [:mouse :duck :lory]
      color [:red :blue]
      :let [animal-str (str "animal-" (name animal))
            color-str (str "color-" (name color))
            display-str (str animal-str "-" color-str)]]
  display-str)
;=> ("animal-mouse-color-red" "animal-mouse-color-blue" "animal-duck-color-red" "animal-duck-color-blue" "animal-lory-color-red" "animal-lory-color-blue")

(for [animal [:mouse :duck :lory]
      color [:red :blue]
      :let [animal-str (str "animal-" (name animal))
            color-str (str "color-" (name color))
            display-str (str animal-str "-" color-str)]
      :when (= color :blue)]
  display-str)
;=> ("animal-mouse-color-blue" "animal-duck-color-blue" "animal-lory-color-blue")

(flatten [[:duck [:mouse [[:lory]]]]])
;=> (:duck :mouse :lory)

(vec '(1 2 3))
;=> [1 2 3]
(into [] '(1 2 3))
;=> [1 2 3]

(sorted-map :b 2 :a 1 :z 3)
;=> {:a 1, :b 2, :z 3}
(into (sorted-map) {:b 2 :c 3 :a 1})
;=> {:a 1, :b 2, :c 3}

(into {} [[:a 1] [:b 2] [:c 3]])
;=> {:a 1, :b 2, :c 3}

(into [] {:a 1, :b 2, :c 3})
;=> [[:a 1] [:b 2] [:c 3]]

(partition 3 [1 2 3 4 5 6 7 8 9])
;=> ((1 2 3) (4 5 6) (7 8 9))

(partition 3 [1 2 3 4 5 6 7 8 9 10])
;=> ((1 2 3) (4 5 6) (7 8 9))

(partition-all 3 [1 2 3 4 5 6 7 8 9 10])
;=> ((1 2 3) (4 5 6) (7 8 9) (10))

(partition-by #(= 6 %) [1 2 3 4 5 6 7 8 9 10])
;=> ((1 2 3 4 5) (6) (7 8 9 10))