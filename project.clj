(defproject clj-d2l "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 ;;[clj-djl "0.1.2"]
                 [com.github.kimim/clj-djl "master-SNAPSHOT"]
                 [dm3/stopwatch "0.1.1"]
                 [tech.tablesaw/tablesaw-core "0.38.1"]
                 [tech.tablesaw/tablesaw-jsplot "0.38.1"]
                 [com.hypirion/clj-xchart "0.2.0"]]
  :repositories [["jitpack" "https://jitpack.io"]]
  :main ^:skip-aot clj-d2l.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
