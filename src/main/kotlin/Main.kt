import kotlin.math.sin

fun main() {
    print("Введите постфиксное выражение для вычисления: ")
    val s: String = readLine().toString()
    println("Результат: ${calc(s)}")
}

fun calc(s: String): Double {
    // Преобразуем исходную строку в подходящий нам массив операндов и операций
    val exp = s.split(" ").map{f -> f.trim()}
    val st = mutableListOf<Double>() // Создаём стек для чисел
    for (i in exp.indices) {
        try {
            when (exp[i]) { // Проходимся по всем элементам массива
                "+" -> {  // Если встречается "+", то вынимаем 2 последних числа из стека и добавляем в конец их сумму
                    val r = st.removeLast()
                    val l = st.removeLast()
                    st.add(l + r)
                }
                "-" -> { // Если встречается "-", то вынимаем 2 последних числа из стека и добавляем в него их разность
                    val r = st.removeLast()
                    val l = st.removeLast()
                    st.add(l - r)
                }
                "*" -> {  // Если встречается "*", то вынимаем 2 последних числа из стека и добавляем в него их произведение
                    val r = st.removeLast()
                    val l = st.removeLast()
                    st.removeLast()
                    st.add(l * r)
                }
                "/" -> {  // Если встречается "/", то вынимаем 2 последних числа из стека и добавляем в конец их частное
                    val r = st.removeLast()
                    val l = st.removeLast()
                    st.add(l / r)
                }
                "sin" -> { // Если встречается "sin", то вынимаем последнее число из стека и добавляем в конец sin от него
                    val x = st.removeLast()
                    st.add(sin(x))
                }
                else -> st.add(exp[i].toDouble()) // В остальных случаях добавляем число в стек
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