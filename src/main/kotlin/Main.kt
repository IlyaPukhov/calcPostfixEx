import kotlin.math.sin
import kotlin.math.pow

fun main() {
    print("Введите постфиксное выражение для вычисления: ")
    val s: String = readLine().toString()
    println("Результат: ${calcPostfixEx(s)}")
}

fun calcPostfixEx(s: String): Float {
    // Преобразуем исходную строку в подходящий нам массив операндов и операций
    val exp = s.split(" ").map { f -> f.trim() }
    val st = mutableListOf<Float>() // Создаём стек для чисел
    var r: Float
    var l: Float
    for (i in exp.indices) {
        try {
            when (exp[i]) { // Проходимся по всем элементам массива
                "+" -> {  // Если встречается "+", то вынимаем 2 последних числа из стека и добавляем в конец их сумму
                    r = st.removeLast()
                    l = st.removeLast()
                    st.add(l + r)
                }
                "-" -> { // Если встречается "-", то вынимаем 2 последних числа из стека и добавляем в него их разность
                    r = st.removeLast()
                    l = st.removeLast()
                    st.add(l - r)
                }
                "*" -> {  // Если встречается "*", то вынимаем 2 последних числа из стека и добавляем в него их произведение
                    r = st.removeLast()
                    l = st.removeLast()
                    st.add(l * r)
                }
                "/" -> {  // Если встречается "/", то вынимаем 2 последних числа из стека и добавляем в конец их частное
                    r = st.removeLast()
                    l = st.removeLast()
                    st.add(l / r)
                }
                "^" -> { // Если встречается "^", то вынимаем 2 последних числа из стека и добавляем в конец предпоследнее число в степени последнего
                    r = st.removeLast()
                    l = st.removeLast()
                    st.add(l.pow(r))
                }
                "sin" -> { // Если встречается "sin", то вынимаем последнее число из стека и добавляем в конец sin от него
                    r = st.removeLast()
                    st.add(sin(r))
                }
                else -> st.add(exp[i].toFloat()) // В остальных случаях добавляем число в стек
            }
        } catch (e: NoSuchElementException) { // Если количество чисел в стеке недостаточно для операции, выдаём ошибку
            throw Error("Неверное выражение!")
        }
    }
    if (st.size != 1) { // Если после всех операций в стеке осталось более 1 числа, выдаём ошибку
        throw Error("Неверное выражение!")
    }
    return st[0] // Если в стеке осталось 1 число, то возвращаем его (это и есть результат выражения)
}
