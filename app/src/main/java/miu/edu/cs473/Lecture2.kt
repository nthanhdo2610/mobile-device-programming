package miu.edu.cs473

// Regular Approach
fun sum(x: Int, y: Int): Int {
    return x + y
}

// Kotlin Approach 1
fun sum1(x: Int, y: Int): Int = x + y

// Kotlin Approach 2
fun sum2(x: Int, y: Int) = x + y

fun displayStrings(vararg strings: String) {
    for (string in strings) {
        println(string)
    }
}

fun buildMessageFor(name: String = "Customer", count: Int = 0): String {
    return ("$name, you are customer number $count")
}

// Kotlin Primary Constructors
class Person(var name: String, var age: Int) {
    var profession: String = "Not Mentioned"

    // Kotlin Secondary Constructors
    constructor (name: String, age: Int, profession: String) : this(name, age) {
        this.profession = profession
    }

    fun printPersonDetails() {
        println("$name whose profession is $profession, is $age years old.")
    }
}

open class BankAccount {
    var accountNumber = 0
    var accountBalance = 0.0

    constructor(number: Int, balance: Double) {
        accountNumber = number
        accountBalance = balance
    }

    open fun displayBalance() { // To Override use open
        println("Number $accountNumber")
        println("Current balance is $accountBalance")
    }
}

class SavingsAccount : BankAccount {
    var interestRate: Double = 0.0

    constructor(accountNumber: Int, accountBalance: Double) :
            super(accountNumber, accountBalance)

    constructor(accountNumber: Int, accountBalance: Double, rate: Double) :
            super(accountNumber, accountBalance) {
        interestRate = rate
    }

    fun calculateInterest(): Double {
        return interestRate * accountBalance
    }

    override fun displayBalance() // Override from parent
    {
        println("Number $accountNumber")
        println("Current balance is $accountBalance")
        println("Prevailing interest rate is $interestRate")
    }
}

interface MyInterface {
    val test: Int // abstract

    // val pass:Int = 83 // Compilation error
    val pass: Int get() = 83 // Initialize using get()

    // Abstract method
    fun print(): String

    // default implementation
    fun hello(name: String) {
        println("Hello there, $name!")
    }
}

class InterfaceImp : MyInterface {
    override val test: Int = 25
    override fun print() = "Kotlin"
}

fun main(args: Array<String>) {

    val obj = InterfaceImp()
    println("test = ${obj.test}")
    print("Calling hello(): ")
    obj.hello("Tim")
    print("Calling and printing print(): ")
    println(obj.print())

//    val savings1 = SavingsAccount(12311, 600.00, 0.07)
//    println(savings1.calculateInterest())
//    savings1.displayBalance()

//    var person_1 = Person("David",25, "Teaching")
//    person_1.printPersonDetails()

    // Calling Functions
//    val message1 = buildMessageFor("John",10) //Valid
//    val message2 = buildMessageFor("John") // Valid
//    val message3 = buildMessageFor(10) // Invalid
//    val message4 = buildMessageFor(count = 10) // Valid

//    displayStrings("one", "two", "three", "four")

//    val name = "John"
//    val count = 5
//    fun displayString() {
//        for (index in 0..count) {
//            println(name)
//        }
//    }
//    displayString()

//    println("Language ArrayList")
//    var llist = ArrayList<String>(arrayListOf
//        ("C++","Java","HTML"))
//    llist.add("Kotlin")
//    llist.add("Scala")
//    llist.add("Ruby")
//    println(llist)

//    var b: String? = "Hello" // variable is declared as nullable
//    var blen:Int = b!!.length
//    println("b is : $b")
//    println("b length is : $blen")
//    b = null
//    println("b is : $b")
//    blen = b!!.length // Throws NullPointerException
//    println("b length is : $blen")

//    var b: String? = " Domenic" // variable is declared as nullable
//    val len = b?.length ?: -1
//    println("length is : $len")
//    b= null
//    val noname = b?.length ?:"No one knows me"
//    println("Name is : $noname")

//    var a:String?="Domenic"
//    a=null
//    println(a)
//    val x = 1
//    when (x) {
//        1 -> print("x == 1")
//        2 -> print("x == 2")
//        else -> { // Note the block
//            print("x is neither 1 nor 2")
//        }
//    }

//    for (i in 1..10 step 2) { print(i) }

//    var daysOfWeek = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
//    repeat(2){
//        daysOfWeek.forEach{
//            println(it)
//        }
//    }
//    for((i,v) in daysOfWeek.withIndex()){
//        println("$i : $v")
//    }
//    for(i in daysOfWeek){
//        println(i)
//    }

//    println("What is your name?")
//    val name = readln()
//    println("Hello, my name is $name")

//    val x: String = """   |Kotlin
//    |supports
//    |Multiline
//    |Strings""".trimMargin()
//    println(x)

//    val x = "Domenic"
//    val y = "My name is $x"
//    println(y)
//    println("My name is $x with the length ${x.length}")

//    val s: String = "Hello"
//    println("Length of string $s is ${s.length}")
//    println("Init cap of string is ${s.capitalize()}")
//    println("Lower case is ${s.toLowerCase()}")
//    println("Upper case is ${s.toUpperCase()}")
}