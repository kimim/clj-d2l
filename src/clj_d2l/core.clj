(ns clj-d2l.core
  (:require [clj-djl.ndarray :as nd]
            [clj-djl.training.dataset :as ds]
            [com.hypirion.clj-xchart :as c])
  (:import [ai.djl.basicdataset.cv.classification FashionMnist]
           [java.nio.file Paths]))

(defn linreg [X w b]
  (nd/+ (nd/dot X w) b))

(defn squared-loss [y-hat y]
  (nd// (nd/* (nd/- y-hat (nd/reshape y (nd/get-shape y-hat)))
              (nd/- y-hat (nd/reshape y (nd/get-shape y-hat))))
        2))

(defn load-array [[data labels] batchsize shuffle?]
  (ds/array-dataset {:data data :labels labels
                     :batchsize batchsize :shuffle shuffle?}))

(defn synthetic-data [ndm w b num]
  (let [X (nd/random-normal ndm [num (nd/size w)])
        y (nd/+ (nd/dot X w) b)
        noise (nd/random-normal ndm 0 0.01 (nd/shape y) :float32)]
    [X (nd/+ y noise)]))


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

(defn plot-scatter [filename title x y]
  (-> (c/xy-chart
       {title
        {:x x
         :y y
         :style {:marker-type :circle}}}
       {:title title
        :render-style :scatter})
      (c/spit filename)))


(defn plot-line [filename title x y]
  (-> (c/xy-chart
     {title
      {:x x
       :y y
       :style {:marker-type :none}}})
    (c/spit filename)))

(def plot plot-line)

(defn plot-lines [filename titles x ys]
  (-> (c/xy-chart
       (reduce #(assoc %1 (get %2 0) {:x x :y (get %2 1) :style {:marker-type :none}})
               {} (zipmap titles ys)))
      (c/spit filename)))

(defmacro ps [expr]
  `(print (str ~expr)))

(defmacro psl [expr]
  `(println (str ~expr)))

(defn -main
  [& args]
  (println "clj-d2l"))

(defn load-data-fashion-mnist [batchsize]
  [(-> (FashionMnist/builder)
       (ds/opt-usage :train)
       (ds/set-sampling batchsize true)
       (ds/build)
       (ds/prepare))
   (-> (FashionMnist/builder)
       (ds/opt-usage :test)
       (ds/set-sampling batchsize true)
       (ds/build)
       (ds/prepare))])
