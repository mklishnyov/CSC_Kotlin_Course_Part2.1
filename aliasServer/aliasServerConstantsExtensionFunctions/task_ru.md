### Константные переменные

Чтобы помочь компилятору Kotlin оптимизировать ваш код, вы можете использовать модификатор `const` для переменных, которые остаются неизменными – [константы времени компиляции](https://kotlinlang.org/docs/properties.html#compile-time-constants). Константы могут иметь только базовые (примитивные) типы, такие как `String`, `Int` и другие. Кроме того, они не могут быть инициализированы путем вызова функций или подобным образом. И последнее, но не менее важное: константы могут использоваться только внутри `companion objects` (или просто внутри `objects`, о которых мы поговорим позже):

```kotlin
class GameCard(private val capacity: Int = 5) {
  companion object {
    private const val MAX_NUMBER_OF_CARDS_OK = 10 // ОК
    private const val MAX_NUMBER_OF_CARDS_NOT_OK = foo() // ОШИБКА

    private fun foo() = 10
  }
}
```

Как видно из примера, в Kotlin принят особый стиль кода для именования констант времени компиляции – все буквы должны быть заглавными, а слова в имени разделены подчеркиванием.

### Функции-расширения

#### Определение

В Kotlin вы можете добавлять новые функции-члены к уже существующим классам. Это делается через специальные объявления, называемые [расширениями](https://kotlinlang.org/docs/extensions.html). Это полезно, если, например, у вас нет доступа к оригинальному классу, но вы хотите добавить новую функцию. Рассмотрим пример – допустим, нам нужно подсчитать количество букв в строке. Мы можем сделать это следующим образом:

```kotlin
fun getNumberOfLetters(s: String, letter: Char) = s.count { it == letter }

fun main() {
  println(getNumberOfLetters("photothermoelasticity", 'o')) // 3
}
```

Однако мы также можем создать _функцию-расширение_, чтобы не передавать строку в качестве аргумента функции:
```kotlin
fun String.getNumberOfLetters(letter: Char) = this.count { it == letter }

fun main() {
  println("photothermoelasticity".getNumberOfLetters('o')) // 3
}
```

В этом случае мы добавили новую функцию в класс `String`. Основное отличие в реализации следующее: 1) мы используем `this` вместо передаваемого строкового параметра; 2) нам не нужно передавать строку в качестве аргумента, и мы можем напрямую вызывать функцию для типа `String`.

#### Доступ

Важно отметить, что эта функциональность работает только для функций, которые _уже не существуют в оригинальном классе_. Если вы определяете новую функцию, которая уже определена в классе, будет вызвана оригинальная реализация:

```kotlin
fun String.isEmpty() = true

fun main() {
    println("photothermoelasticity".isEmpty()) // false, потому что вызвана оригинальная функция isEmpty
}
```

Кроме того, если вы определите новую функцию-расширение _внутри_ класса, она не будет доступна за его пределами:

```kotlin
class Example {
    fun String.countLetters(letter: Char) = this.count { it == letter }
    
    fun foo(string: String) {
        string.countLetters('a') // ОК
    }
}

fun main() {
    println("photothermoelasticity".countLetters()) // ОШИБКА
}
```

<div class="hint" title="Нажмите, чтобы узнать о случаях с несколькими ключевыми словами `this` внутри одного класса">

Когда вы добавляете новую функцию-расширение _внутри_ класса, у вас есть несколько _контекстов_ для ключевого слова `this`. Вы можете указать, какой контекст вам нужно использовать в текущем случае:

  ```kotlin
  class Example(private val toDrop: Int) {
    fun String.countLetters(letter: Char) = this.count { it == letter } // this == String
  
    fun String.dropAndCountLetters(letter: Char) = 
        this.drop(this@Example.toDrop).count { it == letter } // this == String, this@Example == Example
  }
  ```
</div>