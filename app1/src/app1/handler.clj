(ns app1.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [app1.query :as q]))


(def app
  (api    {:swagger     {:ui "/"      :spec "/swagger.json"      :data {:info {:title "App1"                    :description "Compojure Api example"}
                                                                        :tags [{:name "api", :description "some apis"}]}}}

          (context "/api" []
            :tags ["api"]

            (GET "/todos" []
              (ok (q/get-todos )))
            (GET "/api/todos/:id" []
              :path-params [id :- s/Int]
              (ok (q/get-todo id)))
            (POST "/api/todos" []
              :body [title-body q/TitleBody]
              (let [{:keys [title]} title-body]
                (ok (q/add-todo title))))
            (PUT "/api/todos" []
              :body [todo-body q/TodoBody]
              (let [{:keys [id title is_complete]} todo-body]
                (ok {:updated (q/update-todo id title is_complete)})))
            (DELETE "/api/todos/:id" []
              :path-params [id :- s/Int]
              (ok {:deleted (q/delete-todo id)}))

            )))