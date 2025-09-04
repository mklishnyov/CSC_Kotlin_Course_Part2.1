### Изменяемый список

Вы уже знакомы с коллекциями `List` и `Map`. 
Так же как и `Map`, `List` может быть _изменяемым_ или _только для чтения_, что должно быть указано явно.

В Kotlin, если вы хотите создать _изменяемый_ `List`, необходимо указать это _явно_, 
потому что по умолчанию создается коллекция _только для чтения_,
и добавление новых элементов в нее будет невозможно.

Для создания нового списка можно использовать `listOf` для коллекции _только для чтения_ или `mutableListOf` для _изменяемой_:

```kotlin
val readOnlyList = listOf<Int>(1, 2, 3)
readOnlyList.add(4) // ОШИБКА

val mutableList = mutableListOf<Int>(1, 2, 3)
mutableList.add(4) // ОК
```

Для создания пустого _изменяемого_ списка нужно использовать функцию `mutableListOf`, а не `emptyList`, которая используется для списка _только для чтения_:

```kotlin
val emptyMutableListError: MutableList<Int> = emptyList() // ОШИБКА

val emptyMutableListOk: MutableList<Int> = mutableListOf() // ОК
```

### проверка некоторых условий

При разработке приложений часто необходимо поддерживать истинность некоторых инвариантных значений. 
Например, если мы работаем с игровыми картами и знаем максимальное количество карт,
список карт не может превышать это максимальное значение.

Для обработки такого случая можно использовать встроенную функцию [`require`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require.html), которая выбрасывает исключение IllegalArgumentException, если значение ложно:

```kotlin
class GameCard(private val capacity: Int = 5) {
    companion object {
        const val MAX_NUMBER_OF_CARDS = 10 // ОК
    }
}

fun foo(cards: List<GameCard>) {
    require(cards.size <= GameCard.MAX_NUMBER_OF_CARDS) { "Максимальное количество карт: ${GameCard.MAX_NUMBER_OF_CARDS}" }
}
```