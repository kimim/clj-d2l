;;; this script tangles all the clojure code to files


(mapcar 'org-babel-tangle-file
        (file-expand-wildcards "*.org"))

;; calculus.clj => [clj-d2l.calculus :as calculus] => insert to core_test.clj