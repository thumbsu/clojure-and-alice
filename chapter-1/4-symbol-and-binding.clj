; 심볼과 바인딩의 기술 ~ 함수 만들기
; 1장 클로저의 구조 p20 ~ p29


;------;
;** 심볼과 바인딩의 기술

; 문자열 "Alice"에 이름을 주기
(def developer "Alice")
;=> #'user/developer
developer
;=> "Alice"
user/developer
;=> "Alice"

; let을 사용해서 let 영역에서만 사용되는 심볼에 값을 바인딩 하기
(let [developer "Alice in Wonderland"]
  developer)
;=> "Alice in Wonderland"
developer
;=> "Alice"

; let 안에서 바인딩한 심볼은 let 바깥에서 참조할 수 없다
(let [developer "Alice in Wonderland"
      rabbit "White Rabbit"]
  [developer rabbit])
;=>  ["Alice in Wonderland" "White Rabbit"]
rabbit
;=> CompilerException java.lang.RuntimeException: Unable to resolve symbol: rabbit in this context, compiling:(null:0:0)


;------;
;** 함수 만들기

; 빈 벡터를 이용해 함수를 인수 없이 정의하기
(defn follow-the-rabbit [] "Off we go!")
;=> #'user/follow-the-rabbit
(follow-the-rabbit)
;=> "Off we go!"

; 인수를 받아서 맵 반환해보기
(defn shop-for-jams [jam1 jam2]
  {:name "jam-basket"
   :jam1 jam1
   :jam2 jam2})
;=> #'user/shop-for-jams
(shop-for-jams "strawberry" "marmalade")
;=> {:name "jam-basket" :jam1 "strawberry" :jam2 "marmalade"}

; 무명 함수 만들기
(fn [] (str "Off we go" "!"))
;=> #object[user$eval1845$fn__1846 0x53c50a19 "user$eval1845$fn__1846@53c50a19"]
((fn [] (str "Off we go" "!")))
;=> "Off we go!"

; defn은 def로 무명 함수에 이름을 바인딩하는 것과 동일
(def follow-again (fn [] (str "Off we go" "!")))
;=> #'user/follow-again
(follow-again)
;=> "Off we go!"

; 괄호 앞에 #을 붙여서 무명 함수를 만드는 단축형도 있음
(#(str "Off we go" "!"))
;=> "Off we go!"

; 단축형에서 인수가 하나 있다면 퍼센트 기호(%)로 나타낼 수 있음
(#(str "Off we go" "!" " - " %) "again")
;=> "Off we go! - again"

; 인수가 여러개라면 숫자를 붙여서 표시
(#(str "Off we go" "!" " - " %1 %2) "again" "?")
;=> "Off we go! - again?"


;------;
;** 이름공간에서 심볼을 관리하기 

; ns로 이름 공간을 만들기
(ns alice.favfoods)
;=> nil

; *ns*로 현재의 이름 공간을 확인
*ns*
;=> #object[clojure.lang.Namespace 0x588d26f8 "alice.favfoods"]

; var에 직접 접근하기
(def fav-food "strawberry jam")
;=> #'alice.favfoods/fav-food 
fav-food
;=> "strawberry jam"

; 이름공간을 포함하는 완전한 이름으로도 접근 가능
alice.favfoods/fav-food
;=> "strawberry jam"

; 다른 이름공간으로 전환하면 그 심볼은 더 이상 참조되지 않음 
(ns rabbit.favfoods)
;=> nil
fav-food
;=> CompilerException java.lang.RuntimeException: Unable to resolve symbol: fav-food in this context, compiling:(null:0:0)

; 전환한 다른 이름공간에서 그 심볼을 다른 값을 지정하는 var로 정의
(def fav-food "lettuce soup")
;=> #'rabbit.favfoods/fav-food
fav-food
;=> "lettuce soup"

; 다른 이름공간에서 참조하려면 완전한 이름이 팔요함
alice.favfoods/fav-food
;=> "strawberry jam"

; require를 사용해서 자신의 이름공간에서 라이브러리를 사용하기
(require 'clojure.set)

; :as를 사용해서 require의 별칭 기능을 이용
(ns wonderland)
;=> nil
(require '[alice.favfoods :as af])
;=> nil
af/fav-food
;=> "strawberry jam"

; 키워드와 벡터의 형태로 사용하기
(ns wonderland
  (:require [alice.favfoods :s af]))
;=> "strawberry jam"

; :refer :all 옵션 사용하기
(ns wonderland
  (:require [alice.favfoods :refer :all]
            [rabbit.favfoods :refer :all]))
;=> IllegalStateException fav-food already refers to: #'alice.favfoods/fav-food in namespace: wonderland

; 이름공간과 심볼을 사용한 예제
(ns wonderland
  (:require [clojure.set :as s]))
(defn common-fav-foods [foods1 foods2]
  (let [food-set1 (set foods1)
        food-set2 (set foods2)
        common-foods (s/intersection food-set1 food-set2)]
    (str "Common Foods: " common-foods)))

(common-fav-foods [:jam :brownies :toast]
                  [:lettuce :carrots :jam])
;=> "Common Foods: #{:jam}"