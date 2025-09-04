В этой версии игры мы будем генерировать только уникальные ключевые карты. Улучшите объект `Utils` в пакете `jetbrains.kotlin.course.codenames.utils`:

- Добавьте поле `previousAttempts` с типом `MutableList<List<KeyCardCell>>` и инициализируйте это поле через пустой изменяемый список. Мы будем хранить ранее сгенерированные ключевые карты, чтобы избежать дубликатов.
- Добавьте поле `uniqueKeyCardGenerator` в объект `Utils` с типом `KeyCardGenerator`.

Реализуйте функцию `generateData` для поля `uniqueKeyCardGenerator` в объекте `Utils`. Поведение функции `generateData` должно быть следующим:
1) Сгенерировать новый `List<KeyCardCell>`: поместите `number` каждого `KeyCardType` в этот список и перемешайте его.
2) Затем проверьте, использовалась ли эта комбинация `List<KeyCardCell>` ранее (её нет в `previousAttempts`), добавьте этот список в `previousAttempts` и верните этот список.
3) Если сгенерированный список был использован ранее, повторите генерацию, пока не будет сгенерирован новый список (которого нет в `previousAttempts`).

Наконец, добавьте новый класс `KeyCard` в пакет `jetbrains.kotlin.course.codenames.keyCard` для хранения ключевого класса. Добавьте одно неизменяемое поле в основной конструктор: `cells: List<KeyCardCell>` и инициализируйте его по умолчанию через `Utils.uniqueKeyCardGenerator.generateData()`.

Если у вас возникнут трудности, **подсказки помогут вам решить эту задачу**.

----

### Подсказки

<div class="hint" title="Click me to learn about initializing an empty mutable list">

Чтобы инициализировать поле `previousAttempts`, вам нужно создать новый пустой изменяемый список:
```kotlin
object Utils {
    ...

    private val previousAttempts: MutableList<List<KeyCardCell>> = mutableListOf()
}
```
</div>

<div class="hint" title="Click me to learn about defining a function for SAM interfaces">

Лучше использовать определение функции для интерфейсов SAM:
```kotlin
val uniqueKeyCardGenerator = KeyCardGenerator {
    // Реализация функции generateData
}
```
</div>

<div class="hint" title="Click me to learn how to get all values from an enum class">

Чтобы получить _все_ значения из enum-класса, вы можете использовать встроенную функцию [values](https://kotlinlang.org/docs/enum-classes.html#working-with-enum-constants). Рассмотрите пример:

```kotlin
enum class KeyCardType(val number: Int) {
    Pink(Utils.PINK_CARDS_NUMBER),
    Violet(Utils.VIOLET_CARDS_NUMBER),
    ...
}

fun main() {
    val enumValuesBase = listOf(KeyCardType.Pink, KeyCardType.Violet, ...)
    // тоже самое, что
    val enumValuesSmart = KeyCardType.entries
}
```
</div>

<div class="hint" title="Click me to learn about the `map` built-in function">

Вы уже знакомы с несколькими встроенными функциями для списков. 
Время познакомиться со встроенной функцией [`map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/map.html).

Эта функция позволяет применять некое _преобразование_ к каждому элементу списка и возвращать новый, изменённый список. 
Это эквивалентно использованию цикла и созданию нового списка из оригинального. Рассмотрите пример:

```kotlin
fun main() {
    val initialList = KeyCardType.entries
    val modifiedList = mutableListOf<List<KeyCardType>>()

    for (key in initialList) {
        modifiedList.add(List(key.number) { key })
    }

    println(modifiedList) // [[Pink, Pink, Pink, Pink, Pink, Pink, Pink, Pink], [Violet, Violet, Violet, Violet, Violet, Violet, Violet, Violet, Violet], [Gray, Gray, Gray, Gray, Gray, Gray, Gray], [Black]]
}
```

То же самое, что и:

```kotlin
fun main() {
    val initialList = KeyCardType.entries
    val modifiedList = initialList.map { key -> List(key.number) { key } }
    
    println(modifiedList) // [[Pink, Pink, Pink, Pink, Pink, Pink, Pink, Pink], [Violet, Violet, Violet, Violet, Violet, Violet, Violet, Violet, Violet], [Gray, Gray, Gray, Gray, Gray, Gray, Gray], [Black]]
}
```

Вы также можете опустить `key` и использовать имя по умолчанию `it`:
```kotlin
fun main() {
    val initialList = KeyCardType.entries
    val modifiedList = initialList.map { List(it.number) { it } }
    
    println(modifiedList) // [[Pink, Pink, Pink, Pink, Pink, Pink, Pink, Pink], [Violet, Violet, Violet, Violet, Violet, Violet, Violet, Violet, Violet], [Gray, Gray, Gray, Gray, Gray, Gray, Gray], [Black]]
}
```
</div>

<div class="hint" title="Click me to learn how to flatten a list of lists into a single list">

Рассмотрите ситуацию, когда у нас есть список списков, например, `List<List<KeyCardType>>`, 
и нам нужно получить список, состоящий из всех элементов всех списков оригинального списка:
```kotlin
// [[Pink, Pink], [Violet, Violet], [Gray], [Black]] -> [Pink, Pink, Violet, Violet, Gray, Black]
```

Мы можем сделать это с помощью циклов:
```kotlin
fun main() {
    val initialList = listOf(
        listOf(KeyCardType.Pink, KeyCardType.Pink),
        listOf(KeyCardType.Violet, KeyCardType.Violet),
        listOf(KeyCardType.Gray),
        listOf(KeyCardType.Black)
    )
    val finalList = mutableListOf<KeyCardType>()

    for (keyList in initialList) {
        for (key in keyList) {
            finalList.add(key)
        }
    }

    println(finalList) // [Pink, Pink, Violet, Violet, Gray, Black]
}
```

Однако, Kotlin имеет встроенную функцию [`flatten`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/flatten.html) для того же:
```kotlin
fun main() {
    val initialList = listOf(
        listOf(KeyCardType.Pink, KeyCardType.Pink),
        listOf(KeyCardType.Violet, KeyCardType.Violet),
        listOf(KeyCardType.Gray),
        listOf(KeyCardType.Black)
    )
    val finalList = initialList.flatten()

    println(finalList) // [Pink, Pink, Violet, Violet, Gray, Black]
}
```
</div>

<div class="hint" title="Click me to learn about the `shuffled` built-in function">

Иногда вам нужно случайно перемешать содержимое списка: например,
чтобы изменить порядок слов в оригинальном списке.
Для этого вы можете либо сгенерировать разные позиции слов из оригинального списка и построить новый,
либо использовать встроенную функцию [`shuffled`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/shuffled.html):

  ```kotlin
  val numbers = listOf(1, 2, 3, 4, 5, 6)
  println(numbers.shuffled()) // 1, 2, 3, 4, 5, 6 в другом случайном порядке
  ```
</div>

<div class="hint" title="Click me to learn how to check if an element is in a list">

Чтобы проверить, есть ли элемент в списке, вы можете использовать оператор `in`:
```kotlin
fun main() {
    val initialList = listOf(
        KeyCardType.Pink,
        KeyCardType.Pink,
        KeyCardType.Black
    )

    println(KeyCardType.Pink in initialList) // true
    println(KeyCardType.Violet in initialList) // false
}
```

То же самое можно применить к списку списков, в этом случае функция вернёт `true`, если оригинальный список содержит 
точно такой же список, который имеет тот же размер и содержит элементы в том же порядке:

```kotlin
fun main() {
    val initialList = listOf(
        listOf(KeyCardType.Pink, KeyCardType.Pink, KeyCardType.Black),
        listOf(KeyCardType.Violet, KeyCardType.Gray, KeyCardType.Violet)
    )

    println(listOf(KeyCardType.Pink, KeyCardType.Pink, KeyCardType.Black) in initialList) // true
    println(listOf(KeyCardType.Pink, KeyCardType.Black, KeyCardType.Pink) in initialList) // false из-за разного порядка
    println(listOf(KeyCardType.Pink, KeyCardType.Pink) in initialList) // false
}
```
</div>