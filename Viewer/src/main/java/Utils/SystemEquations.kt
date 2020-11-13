import kotlin.math.abs


/**
 * Система уравнений вида Ax = B
 */
object SystemEquations {
    sealed class Exception(message: String): RuntimeException(message) {
        class NoSolution: Exception("No solution")
        class ManySolution: Exception("Many solution")
    }

    /**
     * Решение методом гаусса
     */
    object Gauss {
        /**
         * Основная функция
         */
        fun calculate(a: List<List<Double>>, b: List<List<Double>>): List<Double> {
            val matrix = mergeMatrix(a, b)

            this.directExpulsion(matrix)
            this.checkDecision(matrix)

            // Нормирование
            for (i in matrix.indices) {
                val c = matrix[i][i]
                matrix[i] = matrix[i].map { it / c }.toMutableList()
            }

            val equally = mutableListOf<Double>()
            // Востановить вектор B, во время прямого исключение вектор B был изменен
            for (i in b.indices) { equally.add(matrix[i][matrix[i].size - 1]) }

            return this.reverseLookup(matrix, equally)
        }

        /**
         * Проверить решение
         */
        private fun checkDecision(matrix: MutableList<MutableList<Double>>) {
            val doubleNull = 1.000000000000000E-15

            // Проверка строки на нулевые элементы
            for (i in matrix.size - 1 downTo 0) {
                var countNotNull = 0
                for(j in matrix.indices) {
                    if (abs(matrix[i][j]) > abs(doubleNull)) {
                        countNotNull++
                    }
                }

                // a11   a12 .. a1n-2   a1n-1   a1n
                // 0     a21 .. a2n-2   a2n-1   a2n
                //..    ..   .. ..      ..      ..
                // 0     0   .. 0       ann-1   ann
                if (countNotNull == 1 && i == matrix.size - 1) return

                // a11   a12 .. a1n-2   a1n-1   a1n
                // 0     a21 .. a2n-2   a2n-1   a2n
                //..    ..   .. ..      ..      ..
                // 0     0   .. 0       0       ann
                if (countNotNull == 0) {
                    if (matrix[i][matrix[i].size - 1] != 0.0) throw Exception.NoSolution()
                }

                // a11   a12 .. a1n-2   a1n-1   a1n
                // 0     a21 .. a2n-2   a2n-1   a2n
                //..    ..   .. ..      ..      ..
                // 0     0   .. 0       0       0
                if (countNotNull == matrix.size - 1 - i) throw Exception.ManySolution()
            }
        }

        /**
         * Обратная подстановка
         */
        fun reverseLookup(matrix: List<List<Double>>, equally: MutableList<Double>): List<Double> {
            val x = Array(size = equally.size) { 0.0 }

            for (i in matrix.size - 1 downTo 0) {
                x[i] = equally[i]
                for (j in i downTo 0) {
                    equally[j] = equally[j] - matrix[j][i] * x[i]
                }
            }

            return x.toList()
        }

        /**
         * Прямое исключение
         * @return Определитель
         */
        fun directExpulsion(matrix: MutableList<MutableList<Double>>): Double {
            var sign = 0
            for (i in matrix.indices) {
                for (j in matrix.size - 1 downTo i + 1) {
                    sign += rotate(i, j, matrix)
                    minus(matrix, j, j - 1, matrix[j][i], matrix[j - 1][i])
                }
            }
            var det = 1.0
            for (i in matrix.indices) {
                det *= matrix[i][i]
            }
            return det * if (sign % 2 == 1) -1 else 1
        }

        private fun rotate(i: Int, j: Int, matrix: MutableList<MutableList<Double>>): Int {
            var countRotate = false
            // Поиск максимального значения в строке
            val index = findMaxCoefficientInLine(i, matrix)
            // Выполнить перестоновку
            if (index != j - 1) {
                countRotate = true
                val source = matrix[index]
                matrix[index] = matrix[j - 1]
                matrix[j - 1] = source
            }

            return if (countRotate) 1 else 0
        }

        /**
         * Отнять от [i] строки [j] умноженную на [mul] и делённую на [div]
         */
        private fun minus(matrix: MutableList<MutableList<Double>>, i: Int, j: Int, mul: Double, div: Double) {
            for (index in 0 until matrix[i].size) {
                matrix[i][index] -= matrix[j][index] * mul / div
            }
        }

        /**
         * Поиск строки с максимальным коэффициентом
         */
        private fun findMaxCoefficientInLine(j: Int, A: List<List<Double>>): Int {
            var max = abs(A[j][j])
            var index = j

            for (i in j until A.size) {
                if (abs(A[i][j]) > max) {
                    max = A[i][j]
                    index = i
                }
            }
            return index
        }

    }

    /**
     * Вычислить обратную матрицу методом гаусса
     */
    fun calculateReverseMatrixByGauss(matrix: MutableList<MutableList<Double>>): MutableList<MutableList<Double>>{
        // Построение единичной матрицы
        val e = mutableListOf<MutableList<Double>>()
        for (i in matrix.indices) {
            e.add(mutableListOf())
            for(j in matrix.indices) {
                e[i].add(if (i == j) 1.0 else 0.0)
            }
        }

        // Объеденить матрицы и выполнить прямое исключение
        val directedMatrix = mergeMatrix(matrix, e)
        Gauss.directExpulsion(directedMatrix)

        // Нормирование
        for (i in directedMatrix.indices) {
            val c = directedMatrix[i][i]
            directedMatrix[i] = directedMatrix[i].map { it / c }.toMutableList()
        }

        // Развить матрицу пополам
        val temp = directedMatrix.map {
            it.withIndex().partition { elem -> elem.index < matrix.size - 1 / 2 }
        }

        // Разбить матрицы
        val firstPart = temp.map { it.first }.map { it.map { elem -> elem.value } }
        val secondPart = MatrixM(temp.map { it.second }.map {
            it.map { elem -> elem.value }.toMutableList()
        }.toMutableList()).getTransposedMatrix().getMatrix()

        // Вычисление обратной матрицы, путем обратного хода для каждого элемента
        val res = mutableListOf<MutableList<Double>>()
        secondPart.forEach { res.add(Gauss.reverseLookup(firstPart, it).toMutableList()) }

        return MatrixM(res).getTransposedMatrix().getMatrix()
    }

    /**
     * Решить систему линейных алгебраических уравнений методом гаусса
     */
    fun calculateByGauss(a: List<List<Double>>, b: List<List<Double>>): List<Double> {
        if (a.size != b.size) {
            throw RuntimeException("Matrix is not correct1")
        }
        val size = a[0].size
        if (size != a.size) throw RuntimeException("Matrix is not correct2")
        a.forEach { if (it.size != size) throw RuntimeException("Matrix is not correct3") }

        return Gauss.calculate(a, b)
    }

    /**
     * Дополнить матрицу a матрицей b
     */
    fun mergeMatrix(a: List<List<Double>>, b: List<List<Double>>): MutableList<MutableList<Double>> {
        val matrix = mutableListOf<MutableList<Double>>()
        a.forEach { matrix.add(it.toMutableList()) }
        for (i in a.indices) matrix[i].addAll(b[i])

        return matrix
    }

    /**
     * Определить невязву
     */
    fun getResidual(a: List<List<Double>>, b: List<Double>, x: List<Double>): MutableList<Double> {
        val residual = mutableListOf<Double>()

        a.forEachIndexed { i, list ->
            var equally = 0.0
            list.forEachIndexed { j, d ->
                equally += d * x[j]
            }
            residual.add(b[i] - equally)
        }
        return residual
    }
}