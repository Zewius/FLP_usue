package ru.zewius.flp.one


/**
 * Класс, предоставляющий решение задачи по расстановке N ферзей на шахматной доске.
 *
 * Для решения задачи использовался алгоритм Backtracking
 * https://habr.com/ru/companies/otus/articles/746408
 */
class Solution {

    companion object {
        /** Обозначение пустого шахматного поля */
        const val EMPTY_CELL = '.'

        /** Обозначение шахматного поля с ферзем */
        const val QUEEN_CELL = 'Q'
    }

    /**
     * Функция расстановки N ферзей на шахматной доске.
     *
     * @param n Количество ферзей, а также размер доски (n * n)
     * @return Список всех возможных расстановок ферзей
     */
    fun solveNQueens(n: Int): List<List<String>> {
        val result = mutableListOf<List<String>>()
        val board = createNewBoard(n)
        backtrack(board, 0, result)
        return result
    }

    /**
     * Рекурсивная функция для поиска всех возможных расстановок ферзей.
     *
     * @param board Текущая доска
     * @param row Номер текущей строки
     * @param result Список для сохранения расстановок
     */
    private fun backtrack(board: Array<CharArray>, row: Int, result: MutableList<List<String>>) {
        if (row == board.size) {
            result.add(board.map { cell -> cell.joinToString("") })
            return
        }

        for (column in board.indices) {
            if (isValid(board, row, column)) {
                board[row][column] = QUEEN_CELL
                backtrack(board, row + 1, result)
                board[row][column] = EMPTY_CELL
            }
        }
    }

    /**
     * Проверяет, является ли текущая клетка допустимой для расстановки ферзя.
     *
     * @param board Текущая доска
     * @param row Номер текущей строки
     * @param column Номер текущего столбца
     * @return true, если клетка допустима для расстановки ферзя, иначе false
     */
    private fun isValid(board: Array<CharArray>, row: Int, column: Int): Boolean {

        // Проверка на наличие ферзя в строке
        for (rowInder in 0..<row) {
            if (board[rowInder][column] == QUEEN_CELL) {
                return false
            }
        }

        // Проверка на наличие ферзя по главной диагонали (↖)
        var i = row - 1
        var j = column - 1
        while (i >= 0 && j >= 0) {
            if (board[i][j] == QUEEN_CELL) {
                return false
            }
            i--
            j--
        }

        // Проверка на наличие ферзя по побочной диагонали (↗)
        i = row - 1
        j = column + 1
        while (i >= 0 && j < board.size) {
            if (board[i][j] == QUEEN_CELL) {
                return false
            }
            i--
            j++
        }

        return true
    }

    /**
     * Создание пустой доски для расстановки ферзей
     *
     * @param n Размер доски (n * n)
     * @return Заполненная пустыми полями доска
     */
    private fun createNewBoard(n: Int): Array<CharArray> {
        return Array(n) { CharArray(n) { EMPTY_CELL } }
    }

}
