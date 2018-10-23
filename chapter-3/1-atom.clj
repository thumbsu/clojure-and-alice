; 독립적인 항목에 아톰 사용하기
; 3장 상태와 병행성 - 현실 세계의 상태와 병행성 다루기 p67 ~ p73

(def who-atom (atom :caterpillar))
;=> #'user/who-atom
who-atom
;=> #object[clojure.lang.Atom 0x3bd0760f {:status :ready, :val :caterpillar}]
@who-atom
;=> :caterpillar
(reset! who-atom :chrysalis)
;=> :chrysalis
@who-atom
;=> :chrysalis


(def who-atom (atom :caterpillar))
;=> #'user/who-atom
@who-atom
;=> :caterpillar
(defn change [state]
  (case state
    :caterpillar :chrysalis
    :chrysalis :butterfly
    :butterfly))
;=> #'user/change
(swap! who-atom change)
;=> :chrysalis
@who-atom
;=> :chrysalis
(swap! who-atom change)
;=> :butterfly
@who-atom
;=> :butterfly
(swap! who-atom change)
;=> :butterfly
@who-atom
;=> :butterfly


(def counter (atom 0))
;=> #'user/counter
@counter
;=> 0
(dotimes [_ 5] (swap! counter inc))
;=> nil
@counter
;=> 5


(def counter (atom 0))
;=> #'user/counter
@counter
;=> 0
(let [n 5]
  (future (dotimes [_ n] (swap! counter inc)))
  (future (dotimes [_ n] (swap! counter inc)))
  (future (dotimes [_ n] (swap! counter inc))))
@counter
;=> 15


(def counter (atom 0))
;=> #'user/counter
(defn inc-print [val]
  (println val)
  (inc val))
;=> #'user/inc-print
(swap! counter inc-print)
; 0
;=> 1
(let [n 2]
  (future (dotimes [_ n] (swap! counter inc-print))
          (dotimes [_ n] (swap! counter inc-print))
          (dotimes [_ n] (swap! counter inc-print))))
; 0
; 1
; #object[clojure.core$future_call$reify__6962 0x2e1495c3 {:status :pending, :val nil}] 2
; 3
; 4
; 5
@counter
;=> 6