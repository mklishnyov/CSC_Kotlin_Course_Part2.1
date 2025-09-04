Цель этого шага — реализовать классы `Word` и `WordService`.

Прежде всего, создайте класс значений `Word` с одним свойством `String` `word` для хранения слова в пакете `jetbrains.kotlin.course.words.generator.word` в файле `WordModel.kt`.

Далее найдите уже добавленный класс `WordService` в пакете `jetbrains.kotlin.course.words.generator.word` и модифицируйте его:
- Добавьте объект-компаньон в класс `WordService` и объявите переменную `numberOfWords` для хранения количества слов в игре. Инициализируйте эту переменную как _размер_ предопределенного списка слов `words` (который определен в файле `Words.kt` пакета `jetbrains.kotlin.course.words.generator.util`).
- Реализуйте функцию `generateNextWord`: если список `words` _пуст_, выбросьте ошибку; иначе получите первый элемент из списка `words` и удалите его из списка, затем создайте новый `Word` и верните его.

<div class="hint" title="Нажмите на меня, если нажали Проверить и нашли ошибку компиляции">

Если у вас ошибка компиляции и вы еще не решили этот шаг, пожалуйста, выполните задание и попробуйте снова. Это ожидаемое поведение, так как код требует класс `Word`, но он не существует.
</div>

Если у вас возникли трудности, **подсказки помогут решить это задание**.

----

### Подсказки

<div class="hint" title="Нажмите на меня, чтобы узнать, почему мы используем класс значений">

Конечно, мы можем просто использовать тип `String` или создать псевдоним типа для типа `String`. Все эти варианты, несомненно, будут работать в нашем случае. Однако _цель_ этого курса состоит в том, чтобы показать вам силу Kotlin, чтобы вы могли в будущем выбрать вариант, который вам более всего подходит.
</div>

<div class="hint" title="Нажмите на меня, чтобы узнать, почему numberOfWords не является константным значением">

Мы не можем пометить переменную `numberOfWords` ключевым словом `const`, поскольку мы используем `words.size` из изменяемого списка `words`, который потенциально может быть изменен.
</div>

<div class="hint" title="Нажмите на меня, чтобы узнать о встроенной функции `isEmpty`">

Если вам нужно проверить, пуст ли список или нет, вы можете либо проверить его размер, либо использовать встроенную функцию [isEmpty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/is-empty.html):

  ```kotlin
  val numbers = listOf(1, 2, 3)
  if (numbers.size == 0) {
      TODO()
  }
  ```
Это **то же самое**, что и

  ```kotlin
  val numbers = listOf(1, 2, 3)
  if (numbers.isEmpty()) {
      TODO()
  }
  ```
</div>

<div class="hint" title="Нажмите на меня, чтобы узнать о встроенной функции `removeFirst`">

Если вам нужно получить первый элемент из изменяемого списка и затем удалить его, вы можете использовать встроенную функцию [removeFirst](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/remove-first.html):

```kotlin
fun main() {
    val numbers = mutableListOf(2, 3, 4, 5)
    val first = numbers.first()
    numbers.drop(1)
    println(first) // 2
    println(numbers) // [3, 4, 5]
}
```
Это **то же самое**, что и

```kotlin
fun main() {
    val numbers = mutableListOf(2, 3, 4, 5)
    val first = numbers.removeFirst()
    println(first) // 2
    println(numbers) // [3, 4, 5]
}
```
</div>