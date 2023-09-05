package ru.zewius.flp.one

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("Введите n: ")
    val n = scanner.nextInt()

    val solutions = Solution().solveNQueens(n)

    println("Вариантов решения: ${solutions.size}")
    for (solution in solutions) {
        for (row in solution) {
            println(row)
        }
        println()
    }
}
