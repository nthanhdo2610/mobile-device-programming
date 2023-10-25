package online.cs.miu.edu

fun scan():String{
    var str ="";
    while ( str==""){
        str = readLine().toString()
    }
    return str;
}

fun main() {
    println("3.a")
    println("Enter number:")
    val a = scan().toInt()
    printDigits(a)
    println("-----------------------")

    val arr = intArrayOf(1, 2, 3, 4, 5, 6)
    println("3.b")
    println(sumOddSquared(arr))
    println("-----------------------")

    println("3.c")
    println("Enter weight:")
    val weight = (readlnOrNull() ?: "0").toInt()
    println(calculateWeight("Uranus", weight))
}

// 3.a Create a function to print the last digit and first digit of the given Int value.
// Get integer input from the console.
fun printDigits(number: Int) {
    val str = number.toString()
    print(str.first())
    println(str.last())
}

// 3.b Write a function to find the sum of odd squared values in the given array of integers.
fun sumOddSquared(list: IntArray): Int {
    var result = 0
    list.forEach { item ->
        if (item % 2 != 0) {
            result += item * item
        }
    }
    return result
}

// 3.c Write a Program using when expression to find the weight of a person in various planets
// according to the choice of user input from the console. Assume inputs in pounds.
fun calculateWeight(planet: String, weight: Int): Double {
    return when (planet) {
        "Venus" -> weight * 0.78
        "Mars" -> weight * 0.39
        "Jupiter" -> weight * 2.65
        "Saturn" -> weight * 1.17
        "Uranus" -> weight * 1.05
        else -> weight * 1.23
    }
}
