(ns app1.query
  (:require [app1.database :refer [db]]
            [korma.core :refer :all]
            [schema.core :as s]))

(s/defschema TitleBody
  {:title s/Str})

(s/defschema TodoBody
  {:id s/Int
   :title s/Str
   :is_complete s/Bool})


(defentity items)

(defn get-todos []
  (select items))

(defn add-todo [title]
  (insert items
          (values {:title title})))

(defn delete-todo [id]
  (delete items
          (where {:id id})))

(defn update-todo [id title is-complete]
  (update items
          (set-fields {:title       title
                       :is_complete is-complete})
          (where {:id id})))

(defn get-todo [id]
  (first    (select items
                    (where {:id id}))))

