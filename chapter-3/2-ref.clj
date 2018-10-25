; 조화로운 변경을 위해 ref 사용하기
; 3장 상태와 병행성 - 현실 세계의 상태와 병행성 다루기 p73 ~ p77

(def alice-height (ref 3))
;=> #'user/alice-height
(def right-hand-bites (ref 10))
;=> #'user/right-hand-bites
@alice-height
;=> 3
@right-hand-bites
;=> 10
(defn eat-from-right-hand []
  (when (pos? @right-hand-bites)
    (alter right-hand-bites dec)
    (alter alice-height #(+ % 24))))
;=> #'user/eat-from-right-hand
(eat-from-right-hand)
;=> IllegalStateException No transaction running  clojure.lang.LockingTransaction.getEx (LockingTransaction.java:208)
(dosync (eat-from-right-hand))
;=> 27
@alice-height
;=> 27
@right-hand-bites
;=> 9


(def alice-height (ref 3))
;=> #'user/alice-height
(def right-hand-bites (ref 10))
;=> #'user/right-hand-bites
(defn eat-from-right-hand []
  (dosync (when (pos? @right-hand-bites)
            (alter right-hand-bites dec)
            (alter alice-height #(+ % 24)))))
;=> #'user/eat-from-right-hand
(let [n 2]
  (future (dotimes [_ n] (eat-from-right-hand)))
  (future (dotimes [_ n] (eat-from-right-hand)))
  (future (dotimes [_ n] (eat-from-right-hand))))
;=> #object[clojure.core$future_call$reify__6962 0x5e11ef1d {:status :pending, :val nil}]
@alice-height
;=> 147
@right-hand-bites
;=> 4


(def alice-height (ref 3))
;=> #'user/alice-height
(def right-hand-bites (ref 10))
;=> #'user/right-hand-bites
(defn eat-from-right-hand []
  (dosync (when (pos? @right-hand-bites)
            (commute right-hand-bites dec)
            (commute alice-height #(+ % 24)))))
;=> #'user/eat-from-right-hand
(let [n 2]
  (future (dotimes [_ n] (eat-from-right-hand)))
  (future (dotimes [_ n] (eat-from-right-hand)))
  (future (dotimes [_ n] (eat-from-right-hand))))
;=> #object[clojure.core$future_call$reify__6962 0x1f11174e {:status :pending, :val nil}]
@alice-height
;=> 147
@right-hand-bites
;=> 4


(def x (ref 1))
;=> #'user/x
(def y (ref 1))
;=> #'user/y
(defn new-values []
  (dosync
   (alter x inc)
   (ref-set y (+ 2 @x))))
;=> #'user/new-values
(let [n 2]
  (future (dotimes [_ 2] (new-values)))
  (future (dotimes [_ 2] (new-values))))
;=> #object[clojure.core$future_call$reify__6962 0x7f326bf7 {:status :pending, :val nil}]
@x
;=> 5
@y
;=> 7