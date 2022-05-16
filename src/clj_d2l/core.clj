(ns clj-d2l.core
  (:require [clj-djl.ndarray :as nd]
            [clj-djl.training.dataset :as ds])
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


(defmacro ps [expr]
  `(print (str ~expr)))

(defmacro psl [expr]
  `(println (str ~expr)))

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

(defn -main
  [& args]
  (println "clj-d2l"))
