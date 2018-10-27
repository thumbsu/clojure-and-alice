; 비동기적 변경을 관리하기 위해 에이전트 사용하기
; 3장 상태와 병행성 - 현실 세계의 상태와 병행성 다루기 p78 ~ p82

(def who-agent (agent :caterpillar))
;=> '#user/who-agent
@who-agent
;=> :caterpillar
(defn change [state]
  (case state
    :caterpillar :chrysalis
    :chrysalis :butterfly
    :butterfly))
;=> '#user/change
(send who-agent change)
;=> #object[clojure.lang.Agent 0x7c49223f {:status :ready, :val :caterpillar}]
@who-agent
;=> :chrysalis
(send-off who-agent change)
;=> #object[clojure.lang.Agent 0x7c49223f {:status :ready, :val :butterfly}]
@who-agent
;=> :butterfly


(def who-agent (agent :caterpillar))
;=> '#user/who-agent
(defn change [state]
  (case state
    :caterpillar :chrysalis
    :chrysalis :butterfly
    :butterfly))
;=> '#user/change
(defn change-error [state]
  (throw (Exception. "Boom!")))
;=> '#user/change-error
(send who-agent change-error)
;=> #object[clojure.lang.Agent 0x7eb8b905 {:status :ready, :val :caterpillar}]
@who-agent
;=> :caterpillar
(send-off who-agent change)
;=> Exception Boom!  user$change_error.invokeStatic (:1)
(agent-errors who-agent)
;=> (#error {:cause "Boom!"
;  :via
;  [{:type java.lang.Exception
;    :message "Boom!"
;    :at [user$change_error invokeStatic "/Users/thumbsu/src/clojure-with-alice/chapter-3/3-agent.clj" 32]}]
;  :trace
;  [[user$change_error invokeStatic "/Users/thumbsu/src/clojure-with-alice/chapter-3/3-agent.clj" 32]
;   [user$change_error invoke "/Users/thumbsu/src/clojure-with-alice/chapter-3/3-agent.clj" 32]
;   [clojure.core$binding_conveyor_fn$fn__4676 invoke "core.clj" 1941]
;   [clojure.lang.AFn applyToHelper "AFn.java" 154]
;   [clojure.lang.RestFn applyTo "RestFn.java" 132]
;   [clojure.lang.Agent$Action doRun "Agent.java" 114]
;   [clojure.lang.Agent$Action run "Agent.java" 163]
;   [java.util.concurrent.ThreadPoolExecutor runWorker "ThreadPoolExecutor.java" 1149]
;   [java.util.concurrent.ThreadPoolExecutor$Worker run "ThreadPoolExecutor.java" 624]
;   [java.lang.Thread run "Thread.java" 748]]})
(restart-agent who-agent :caterpillar)
;=> :caterpillar
(send who-agent change)
;=> #object[clojure.lang.Agent 0x69090fb4 {:status :ready, :val :caterpillar}]
@who-agent
;=> :chrysalis
(set-error-mode! who-agent :continue)
;=> nil
(defn err-handler-fn [a ex]
  (println "error " ex " value is " @a))
;=> #'user/err-handler-fn
(set-error-handler! who-agent err-handler-fn)
;=> nil
(def who-agent (agent :caterpillar))
;=> 'user/who-agent
(set-error-mode! who-agent :continue)
;=> nil
(set-error-handler! who-agent err-handler-fn)
;=> nil
(send who-agent change-error)
;=> error  #error {
;  :cause Boom!#object[clojure.lang.Agent 0x2fa674fb {:status :ready, :val :caterpillar}]

;  :via
;  [{:type java.lang.Exception
;    :message Boom!
;    :at [user$change_error invokeStatic /Users/thumbsu/src/clojure-with-alice/chapter-3/3-agent.clj 32]}]
;  :trace
;  [[user$change_error invokeStatic /Users/thumbsu/src/clojure-with-alice/chapter-3/3-agent.clj 32]
;   [user$change_error invoke /Users/thumbsu/src/clojure-with-alice/chapter-3/3-agent.clj 32]
;   [clojure.core$binding_conveyor_fn$fn__4676 invoke core.clj 1941]
;   [clojure.lang.AFn applyToHelper AFn.java 154]
;   [clojure.lang.RestFn applyTo RestFn.java 132]
;   [clojure.lang.Agent$Action doRun Agent.java 114]
;   [clojure.lang.Agent$Action run Agent.java 163]
;   [java.util.concurrent.ThreadPoolExecutor runWorker ThreadPoolExecutor.java 1149]
;   [java.util.concurrent.ThreadPoolExecutor$Worker run ThreadPoolExecutor.java 624]
;   [java.lang.Thread run Thread.java 748]]}  values is  :caterpillar
@who-agent
;=> :caterpillar
(send who-agent change)
;=> #object[clojure.lang.Agent 0x792a7d02 {:status :ready, :val :caterpillar}]
@who-agent
;=> :caterpillar