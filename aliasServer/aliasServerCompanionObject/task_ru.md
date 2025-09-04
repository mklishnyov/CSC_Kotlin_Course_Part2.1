#### Определение

Классы могут содержать переменные или функции, которые специфичны для этого класса, но не требуют использования конкретного состояния экземпляра класса и могут применяться в общем виде.

Например, у нас есть класс `GameCard`, и мы хотим хранить максимальное количество карт, которые можно сгенерировать. Такая переменная может быть помещена в [`companion object`](https://kotlinlang.org/docs/object-declarations.html#companion-objects) внутри этого класса и затем доступна _непосредственно_ через имя класса:

```kotlin
class GameCard(private val capacity: Int = 5) {
    companion object {
        val maxNumberOfCards = 10
    }
}

fun main() {
    println(GameCard.maxNumberOfCards) // 10
    println(GameCard().maxNumberOfCards) // ОШИБКА
}
```

#### Модификаторы доступа

Если вы используете модификатор доступа `private` **внутри** объекта-компаньона, он будет доступен **внутри** внешнего класса, но **не снаружи**:

```kotlin
class GameCard(private val capacity: Int = 5) {
    companion object {
        private val maxNumberOfCards = 10
    }
    
    fun foo() {
        println(maxNumberOfCards) // OK
    }
}

fun main() {
    println(GameCard.maxNumberOfCards) // ОШИБКА
}
```