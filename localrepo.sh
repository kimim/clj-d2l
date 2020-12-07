#!/bin/sh
export version=0.1.0-SNAPSHOT
lein uberjar
lein pom
lein localrepo install target/clj-d2l-$version.jar clj-d2l $version
mv pom.xml ~/.m2/repository/clj-d2l/clj-d2l/$version/clj-d2l-$version.pom
