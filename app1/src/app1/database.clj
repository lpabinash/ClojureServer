
(ns app1.database
  (:require [korma.db :as korma]))

(def db-connection-info (korma/mysql                          {:classname "com.mysql.cj.jdbc.Driver"                           :subprotocol "mysql"                           :user "root"
                                                               :subname "//localhost:3306/todo"}))
(korma/defdb db db-connection-info)