(ns clj-d2l.utils)

(defmacro pp [expr]
  `(print (str ~expr)))
