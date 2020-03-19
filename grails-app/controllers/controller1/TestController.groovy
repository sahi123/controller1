package controller1

class TestController {

//    static defaultAction = "hello"

    def testForward() {
        println "I am testForward"
        forward controller: 'user', action: "index"
    }

    def testRedirect() {
        println "I am testRedirect"
        redirect controller: 'user', action: "show", params: [id: 1]
//        redirect(controllerName:'user',actionName:'index')
    }

    def hello() {
        render "I am hello"
    }

    def index() {
        render "I am index>>>>>>>>>"
    }

    def getValue() {
        println "I am getValue"
        render "I am getValue->>>>>forwarded request"
    }

}
