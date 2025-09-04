Пакет `jetbrains.kotlin.course.card.trainer.card` уже имеет стандартный класс `CardService`.
Он отвечает за игровую логику для карт.
В этом задании вам нужно реализовать этот сервис, чтобы оживить игру.

Прежде всего, добавьте простой генератор случайных чисел в объект-компаньон для генерации случайных последовательностей карт:
- Добавьте переменную `randomCardGenerator` с типом `CardSequenceGenerator` и реализуйте функцию `generateCards`.
Эта функция должна использовать предопределённую карту `countries`, которая хранит пары: столица и страна.
Вам нужно преобразовать все пары в `Card`, затем _перемешать_ этот список и вернуть его из функции.

Затем добавьте новую функцию `generateNewCardsSequence` в объект-компаньон, которая использует `randomCardGenerator`,
вызывает функцию `generateCards` и преобразует результат в _изменяемый_ список.

Затем добавьте новую изменяемую переменную `cards` в объект-компаньон для хранения текущего списка карт и
инициализируйте её, вызывая функцию `generateNewCardsSequence`.

Наконец, реализуйте две функции:

- `getNextCard`, которая проверяет список из переменной `cards`, чтобы удостовериться, что он не пуст,
затем удаляет первый элемент из этого списка и возвращает его.
- `startNewGame`, которая просто помещает новую последовательность карт в переменную `cards` - вы
можете использовать функцию `generateNewCardsSequence`. Затем она возвращает первую карту - вы можете вызвать функцию `getNextCard`.

После решения этого задания вы можете попробовать поиграть в игру, но кнопка `Завершить игру` не будет работать:

![Текущее состояние приложения](../../utils/src/main/resources/images/states/memoryTrainer/state1.gif)

Если у вас возникнут трудности, **подсказки помогут вам решить это задание**.

----

### Подсказки

<div class="hint" title="Нажмите, чтобы узнать о определении функции для интерфейсов SAM">

Лучше использовать определение функции для интерфейсов SAM:
```kotlin
val randomCardGenerator = CardSequenceGenerator {
    // Реализация функции generateCards
}
```
</div>

<div class="hint" title="Нажмите, чтобы узнать о встроенной функции `map`">

Вы можете использовать встроенную функцию [`map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/map.html), чтобы изменить каждую пару в коллекции карты:
  
  ```kotlin
  val numbers = mapOf("one" to 1,"two" to 2, "three" to 3)
  val squared = numbers.map { (key, value) ->
      MyClass(key, value)
  } // [MyClass("one", 1), MyClass("two", 2), MyClass("three", 3)]
  ```
</div>

<div class="hint" title="Нажмите, чтобы узнать о встроенной функции `shuffled`">

Иногда нужно случайно перемешать содержимое списка: например,
чтобы изменить порядок карт в исходном списке.
Для этого вы можете либо сгенерировать разные позиции карт из исходного списка и построить новый список,
либо использовать встроенную функцию [`shuffled`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/shuffled.html):

  ```kotlin
  val numbers = listOf(1, 2, 3, 4, 5, 6)
  println(numbers.shuffled()) // 1, 2, 3, 4, 5, 6 в случайном порядке
  ```
</div>

<div class="hint" title="Нажмите, чтобы узнать о главной разнице между изменяемыми и только для чтения списками">

Мы более подробно рассмотрим коллекции в следующем модуле; пока достаточно знать некоторые основные факты:
1) Мы можем создавать как только для чтения, так и изменяемые списки.
Если вы инициализируете список только для чтения, вы можете только читать его элементы.
Если вы создаёте изменяемый список, вы можете изменять его элементы после инициализации списка:

```kotlin
val readOnlyNumbers = listOf(1, 2, 3, 4, 5, 6)
readOnlyNumbers.add(7) // ОШИБКА
println(readOnlyNumbers[2]) // ОК

val mutableNumbers = mutableListOf(1, 2, 3, 4, 5, 6)
readOnlyNumbers.add(7) // ОК
println(readOnlyNumbers[2]) // ОК
```

2) Вы можете преобразовать список только для чтения в изменяемый:
```kotlin
val readOnlyNumbers = listOf(1, 2, 3, 4, 5, 6)
val mutableNumbers = readOnlyNumbers.toMutableList()
readOnlyNumbers.add(7) // ОК
println(readOnlyNumbers[2]) // ОК
```
</div>

<div class="hint" title="Нажмите, чтобы узнать о встроенной функции `isNotEmpty`">

В случае, если вам нужно проверить, что список не пуст, вы можете либо проверить его размер, либо использовать встроенную функцию [isNotEmpty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-not-empty.html):

  ```kotlin
  val numbers = listOf(1, 2, 3)
  if (numbers.size != 0) {
      TODO()
  }
  ```
Это то же **самое**, что и

  ```kotlin
  val numbers = listOf(1, 2, 3)
  if (numbers.isNotEmpty()) {
      TODO()
  }
  ```
</div>

<div class="hint" title="Нажмите, чтобы узнать о встроенной функции `require`">

Чтобы проверить определённое условие и выбросить ошибку `IllegalArgumentException`, если это необходимо, вы можете использовать встроенную функцию [`require`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require.html):

```kotlin
fun foo(a: int) {
    if (a < 5) {
        throw IllegalArgumentException("Сообщение об ошибке")
    }
}
```

Это то же самое, что и

```kotlin
fun foo(a: int) {
    require (a >= 5) { "Сообщение об ошибке" }
}
```
Обратите внимание, что нужно использовать _противоположное_ условие!
</div>

<div class="hint" title="Нажмите, чтобы узнать о встроенной функции `removeFirst`">

Если вам нужно получить первый элемент из изменяемого списка и затем удалить его, вы можете использовать встроенную функцию [`removeFirst`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/remove-first.html):

```kotlin
fun main() {
    val numbers = mutableListOf(2, 3, 4, 5)
    val first = numbers.first()
    numbers.drop(1)
    println(first) // 2
    println(numbers) // [3, 4, 5]
}
```
Это то же самое, что и

```kotlin
fun main() {
    val numbers = mutableListOf(2, 3, 4, 5)
    val first = numbers.removeFirst()
    println(first) // 2
    println(numbers) // [3, 4, 5]
}
```
</div>