package controller1

class Book {
    String name
    String title
    String author

    static constraints = {
    author nullable: true
    }
}
