(ns clj-d2l.core
  (:require [clj-djl.ndarray :as nd]
            [com.hypirion.clj-xchart :as c]))

(defn accuracy [y-hat y]
  (if (> (nd/size (nd/get-shape y-hat)) 1)
    (-> (.argMax y-hat 1)
        (nd/to-type :int64 false)
        (nd/= (nd/to-type y :int64 false))
        (nd/sum)
        (nd/to-type :float32 false)
        (nd/get-element))
    (-> (nd/= y-hat (nd/to-type y :int64 false))
        (nd/sum)
        (nd/to-type :float32 false)
        (nd/get-element))))

(defn sgd [params lr batch-size]
  (doseq [param params]
    ;; param = param - param.gradient * lr / batchSize
    (nd/-! param (nd// (nd/* (nd/get-gradient param) lr) batch-size))))

(defn plot [filename title x y]
  (-> (c/xy-chart
     {title
      {:x x
       :y y
       :style {:marker-type :none}}})
    (c/spit filename)))
