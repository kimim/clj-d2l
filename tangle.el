#!/usr/bin/emacs --script
;;; this script tangles all the clojure code to files

(require 'org)
(require 'cl)
(mapcar 'org-babel-tangle-file
        (file-expand-wildcards "*.org"))

;; calculus.clj => [clj-d2l.calculus :as calculus] => insert to core_test.clj
(setq requires
     (reduce 'concat
             (mapcar (lambda (x)
                       (format "[clj-d2l.%1$s :as %1$s]\n\t\t"
                               (replace-regexp-in-string "_" "-" x)))
                     (mapcar 'file-name-base
                             (file-expand-wildcards "src/clj_d2l/*.clj")))))
(setq temp-test (format "(ns clj-d2l.temp-test\n\t(:require%s))
(deftest t-test
  (is (= 1 1)))" requires))

(write-region temp-test nil "test/clj_d2l/temp_test.clj")
