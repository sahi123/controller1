package controller1

class User {

    String username
    String fullname
    String email
    Integer age
    Date dateCreated = new Date()
    Date lastUpdated = new Date()

    static constraints = {
    fullname nullable: true
    email nullable: true, email: true
    }
}
