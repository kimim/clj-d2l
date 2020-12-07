(defproject clj-d2l "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 ;;[clj-djl "0.1.2"]
                 [clj-djl "0.1.3"]
                 [dm3/stopwatch "0.1.1"]
                 [com.hypirion/clj-xchart "0.2.0"]
                 [techascent/tech.ml.dataset "5.00-beta-14"]
                 [cnuernber/dtype-next "6.00-beta-9"]
                 [scicloj/tablecloth "4.04"]]
  :repositories [["jitpack" "https://jitpack.io"]]
  :main ^:skip-aot clj-d2l.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
