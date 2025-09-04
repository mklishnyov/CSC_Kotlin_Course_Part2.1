#### Общее определение

Вы уже знакомы с [`companion objects`](https://kotlinlang.org/docs/object-declarations.html#companion-objects).

В Kotlin также существуют [`objects`](https://kotlinlang.org/docs/object-declarations.html#object-declarations-overview). Чтобы лучше понять, что они представляют, вам нужно познакомиться с паттерном [Singleton](https://ru.wikipedia.org/wiki/Одиночка_(шаблон_проектирования)).

Проще говоря, `objects` позволяют создать класс, который всегда будет иметь ровно один экземпляр. Рассмотрим настольную игру как пример. Допустим, у нас есть настройки игры, такие как количество карт определенного цвета или максимальное количество раундов. Кроме того, настройки могут управлять цветовой темой, используемой для игрового поля. В таком случае мы могли бы определить новый _класс_ `Settings` и создать его _ровно один раз_, поскольку не может быть нескольких экземпляров `Settings` в игре, так как они могут конфликтовать друг с другом. В этом случае `object` может помочь, потому что если мы создадим `object` вместо `класса`, мы будем уверены, что в игре не будет других экземпляров `Settings`.

#### Определение Kotlin

**Objects** могут действовать как такие особые сущности, подобные `Settings`. В Kotlin вам нужно использовать ключевое слово [`object`](https://kotlinlang.org/docs/object-declarations.html#object-declarations-overview), чтобы определить новый объект:

```kotlin
object Settings
```

#### Свойства и методы

Как и с классами, вы можете определить свойства или методы внутри объектов, однако, для их использования вам не нужно создавать новый экземпляр класса, вызывая его конструктор. Это связано с определением объектов — может существовать только один экземпляр:

```kotlin
object Settings {
    val theme: String = "light"
}

// Нам не нужно создавать новый экземпляр, чтобы получить свойство
fun main() {
    println(Settings.theme) // light
}
```

С методами ситуация аналогичная:

```kotlin
object Settings {
    var theme: String = "light"
    
    fun changeTheme(newTheme: String) {
        theme = newTheme
    }
}

// Нам не нужно создавать новый экземпляр, чтобы вызвать метод
fun main() {
    println(Settings.theme) // light
    Settings.changeTheme("dark")
    println(Settings.theme) // dark
}
```

#### Использование

В остальном, объекты могут использоваться как классы — вы можете использовать модификаторы доступа или использовать объект как тип:

```kotlin
object Settings {
    private var theme: String = "light"
}

fun main() {
    println(Settings.theme) // ОШИБКА, потому что это приватное
}
```

Или:

```kotlin
object Settings {
    private var theme: String = "light"
    
    fun printTheme() {
        println("Тема игры: $theme")
    }
}

class Game(val settings: Settings)

fun main() {
    val game = Game()
    println(game.settings.printTheme()) // Тема игры: light
}
```