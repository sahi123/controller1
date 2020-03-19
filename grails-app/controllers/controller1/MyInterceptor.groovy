package controller1

class MyInterceptor {

    MyInterceptor() {
        matchAll()
                .excludes(controller: ~/(test|user)/)
                .excludes(controller: 'employee', action: 'index')
//        match(controller: "book", action: 'index')
    }

    boolean before() {
        println "inside Before MyInterceptor>>>>>>>"
        println "controllerName : ${controllerName}\t actionName: ${actionName}"
        true
    }

    boolean after() {
        println "inside After MyInterceptor>>>>>>>"
        println "controllerName : ${controllerName}\t actionName: ${actionName}"
        println "view>>>>$view"
        println "Before model>>>>$model"
        if (controllerName.equals('user') && actionName.equals('index')) {
            model.userCount = 1
            model.userList = [model.userList?.first()]
            println "After modifying model>>>>$model"
        }
        true
    }

    void afterView() {
        println "inside After view MyInterceptor>>>>>>>"
        println "view>>>>$view"
        println "model>>>>$model"
        // no-op
    }
}
