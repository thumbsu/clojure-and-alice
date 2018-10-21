; 구조분해
; 2장 제어문과 함수형 변환 p47 ~ p50

(let [[color size] ["blue" "small"]]
  (str "The " color " door is " size))
;=> "The blue door is small"
(let [x ["blue" "small"]
      color (first x)
      size (last x)]
  (str "The " color " door is " size))
;=> "The blue door is small"

(let [[color [size]] ["blue" ["very small"]]]
  (str "The " color " door is " size))
;=> "The blue door is very small"

(let [[color [size] :as original] ["blue" ["small"]]]
  {:color color :size size :original original})
;=> {:color "blue", :size "small", :original ["blue" ["small"]]}

(let [{flower1 :flower1 flower2 :flower2}
      {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " flower1 " and " flower2))
;=> "The flowers are red and blue"

(let [{flower1 :flower1 flower2 :flower2 :or {flower2 "missing"}}
      {:flower1 "red"}]
  (str "The flowers are " flower1 " and " flower2))
;=> "The flowers are red and missing"

(let [{flower1 :flower1 :as all-flowers}
      {:flower1 "red"}]
  [flower1 all-flowers])
;=> ["red" {:flower1 "red"}]

(let [{:keys [flower1 flower2]}
      {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " flower1 " and " flower2))
;=> "The flowers are red and blue"

(defn flower-colors [colors]
  (str "The flowers are "
       (:flower1 colors)
       " and "
       (:flower2 colors)))
;=> #'user/flower-colors 
(flower-colors {:flower1 "red" :flower2 "blue"})
;=> "The flowers are red and blue"

(defn flower-colors [{:keys [flower1 flower2]}]
  (str "The flowers are " flower1 " and " flower2))
;=> #'user/flower-colors
(flower-colors {:flower1 "red" :flower2 "blue"})
;=> "The flowers are red and blue"