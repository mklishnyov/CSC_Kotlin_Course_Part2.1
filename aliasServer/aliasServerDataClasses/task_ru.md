### Классы данных

#### Определение

Не редкость создание классов, основной целью которых является хранение данных. В таких классах некоторые стандартные функции и некоторые утилитарные функции часто могут автоматически выводиться из данных: например, то, как будет выглядеть строковое представление экземпляра этого класса. В Kotlin такие классы называются классами данных и помечаются ключевым словом [`data`](https://kotlinlang.org/docs/data-classes.html). Такие классы должны иметь хотя бы одно свойство:

```kotlin
data class GameCard() // ОШИБКА
```

```kotlin
data class GameCard(private val capacity: Int = 5) // ОК
```

Как упоминалось выше, классы данных имеют несколько реализованных функций. Рассмотрим пример функции `toString`, которая позволяет получать строковое представление экземпляра класса:

```kotlin
class GameCard(private val capacity: Int = 5) // ОК

fun main() {
    println(GameCard()) // package.GameCard@6d03e736
}
```

Сравните:

```kotlin
data class GameCard(private val capacity: Int = 5) // ОК

fun main() {
  println(GameCard()) // GameCard(capacity=5)
}
```

Полный список переопределенных функций можно найти в [официальной документации](https://kotlinlang.org/docs/data-classes.html); мы постепенно познакомимся с ними в этом модуле.

#### Свойства вне конструктора

В классе данных (как и в обычных классах), вы можете определять свойства не только внутри основного конструктора, но и вне его. В этом случае пользователю не нужно напрямую передавать значение для этого свойства:

```kotlin
data class GameCard(
    private val id: Int, 
    private val capacity: Int = 5
) {
    val name: String = "Card#${id + 1}"
}

fun main() {
    val card = GameCard(3)
    println(card.name) // Card#4
}
```

Однако, свойства, определенные вне конструктора, **не участвуют** во **всех** автоматически реализуемых функциях класса данных:

```kotlin
data class GameCard(
    private val id: Int, 
    private val capacity: Int = 5
) {
    val name: String = "Card#${id + 1}"
}

fun main() {
    val card = GameCard(3)
    println(card) // GameCard(id=3, capacity=5)
}
```

Сравните с:

```kotlin
data class GameCard(
    private val id: Int,
    private val capacity: Int = 5,
    val name: String = "Card#${id + 1}"
)

fun main() {
    val card = GameCard(3)
    print(card) // GameCard(id=3, capacity=5, name=Card#4)
}
```