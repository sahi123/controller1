package controller1

class TestController {

//    static defaultAction = "hello"

    def testForward() {
        println  "I am test1"
        forward  controller:'user',action: "index"
//        forward(controllerName:'user',actionName:'index')
    }

    def testRedirect() {
        println  "I am test1"
        redirect  controller:'user',action: "index"
//        forward(controllerName:'user',actionName:'index')
    }

    def hello() {
        render "I am hello"
    }

    def index() {
        render "I am index>>>>>>>>>"
    }

    def getValue(){
        println  "I am getValue"
        render "I am getValue->>>>>forwarded request"
    }

}
