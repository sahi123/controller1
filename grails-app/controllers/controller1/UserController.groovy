package controller1

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        println "Inside user->>>index"
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model: [userCount: User.count()]
    }

    def show(User user) {
        println "Inside User->>>show"
        respond user
    }

    def create() {
        println "Inside create()"
        println "flash.message>>>>>>${flash.message}"
        respond new User(params)
    }

    @Transactional
    def save(User user) {
        println "Inside save()"
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view: 'create'
            return
        }

        user.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                println "flash.message>>>>>>>>${flash.message}"
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(User user) {
        respond user
    }

    @Transactional
    def update(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view: 'edit'
            return
        }

        user.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: OK] }
        }
    }

    @Transactional
    def delete(User user) {

        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        user.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    //http://localhost:8080/user/customSave?age=25&username=psahi&email=prashant.sahi@rxlogix.com&fullname=Prashant%20Sahi
    @Transactional
    def customSave() {
        println "Inside customSave Action()"
        println "Params : ${params}"
        User user = new User()
        bindData(user, params, [include: ['username', 'age', 'fullname', 'email']])
//        bindData(user,params,[include:['username','age']])
//        bindData(user,params,[exclude:['email']])
        user.save(flush: true)
        redirect user
    }

    //http://localhost:8080/user/customUpdate?id=2&age=235&username=sahi&email=prashant1.sahi@rxlogix.com&fullname=Prashant-Sahi
    @Transactional
    def customUpdate() {
        println "Inside customUpdate Action()"
        println "Params : ${params}"
        User user = User.get(params.id)
        user.properties = params
//                bindData(user,params,[include:['username','age'],exclude:['email']])
//        bindData(user,params,[include:['username','age']])
//        bindData(user,params,[exclude:['email']])
        user.save(flush: true)
        redirect user
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
