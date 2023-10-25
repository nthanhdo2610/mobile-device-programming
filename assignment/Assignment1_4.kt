package online.cs.miu.edu

fun main() {
    val book = Book("Book", "Author", 50.0)
    val ebook = EBook("EBook", "EAuthor", 20.0, "pdf", "public", "kindle")
    book.read()
    ebook.read()
    println(book.title)
}

open class Book(val title: String, val author: String, val price: Double) {
    open fun read() {
        println("Reading Paper book")
    }
}

class EBook(
    title: String, author: String, price: Double,
    val pdf: String, val epub: String, val kindle: String
) : Book(title = title, author = author, price = price) {
    override fun read() {
        println("Read from Electronic Device")
    }
}