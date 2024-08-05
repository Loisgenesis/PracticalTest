package com.genesis.practicaltest


fun main() {
    println("The answer is ${safeDivide(null, 2)}") // returns null
    val text = "This is a kotlin test"
    println("Number of vowels: ${text.countVowels()}") //returns 6
}

fun safeDivide(a: Int?, b: Int?): Int? {
    return if (a != null && b != null) {
        if (b != 0) a / b else null //check to avoid division by zero
    } else {
        null
    }
}

fun String.countVowels(): Int {
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    return this.count { num ->
        num in vowels
    }
}



