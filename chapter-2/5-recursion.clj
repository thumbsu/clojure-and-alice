; 재귀
; 2장 제어문과 함수형 변환 p54 ~ p57

(def adjs ["normal"
           "too small"
           "too big"
           "is swimming"])
;=> #'user/adjs

(defn alice-is [in out]
  (if (empty? in)
    out
    (alice-is
     (rest in)
     (conj out
           (str "Alice is " (first in))))))
;=> #'user/alice-is

(alice-is adjs [])
;=> ["Alice is normal" "Alice is too small" "Alice is too big" "Alice is swimming"]

(defn alice-is [input]
  (loop [in input
         out []]
    (if (empty? in)
      out
      (recur (rest in)
             (conj out (str "Alice is " (first in)))))))
;=> #'user/alice-is
(alice-is adjs)
;=> ["Alice is normal" "Alice is too small" "Alice is too big" "Alice is is swimming"]

(defn countdown [n]
  (if (= n 0)
    n
    (countdown (- n 1))))
;=> #'user/countdown

(countdown 3)
;=> 0

(countdown 100000)
;=> StackOverflowError

(defn countdown [n]
  (if (= n 0)
    n
    (recur (- n 1))))
;=> #'user/countdown

(countdown 100000)
;=> 0