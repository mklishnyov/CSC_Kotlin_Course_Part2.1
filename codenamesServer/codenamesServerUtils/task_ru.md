Пришло время практики!
В этом задании вам предстоит создать утилиту для хранения основных настроек игры!

### Задание

Создайте объект `Utils` в пакете `jetbrains.kotlin.course.codenames.utils` для хранения общих настроек игры:

- Добавьте несколько констант в объект `Utils` для хранения общих констант:
  - `N = 5`, 
  - `TOTAL_NUMBER = N * N`, 
  - `PINK_CARDS_NUMBER = 8`, 
  - `VIOLET_CARDS_NUMBER = 9`, 
  - `GRAY_CARDS_NUMBER = 7`, 
  - `BLACK_CARDS_NUMBER = 1`.
  
  Переменная `N` будет использоваться только внутри объекта `Utils`.
- Добавьте блок `init` в объект `Utils`, чтобы проверить, что сумма `PINK_CARDS_NUMBER`, `VIOLET_CARDS_NUMBER`, `GRAY_CARDS_NUMBER` и `BLACK_CARDS_NUMBER` точно равна `TOTAL_NUMBER`.
  Если условие неверно, необходимо выбросить ошибку `IllegalArgumentException`.

Если у вас возникнут трудности, **подсказки помогут вам решить это задание**.

----

### Подсказки

<div class="hint" title="Нажмите, чтобы узнать о модификаторах доступа">
  
  Только переменную-константу `N` нужно сделать `private`, потому что мы будем использовать другие переменные в будущих заданиях, например, для создания ключевой карты в игре.
</div>

<div class="hint" title="Нажмите, чтобы узнать о встроенной функции `require`">
  
Не забудьте использовать встроенную функцию [`require`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require.html), которая помогает проверять определенные условия и выбросит ошибку `IllegalArgumentException`, если это необходимо:

```kotlin
object Utils {
    ...
  
    init {
      val sum = ...
      if (sum != TOTAL_NUMBER) {
          throw IllegalArgumentException("Общее количество карт в игре должно быть: $TOTAL_NUMBER")
      }
    }
}
```

Это то же самое, что и 

```kotlin
object Utils {
    ...
  
    init {
      require(sum == TOTAL_NUMBER) { "Общее количество карт в игре должно быть: $TOTAL_NUMBER" }
    }
}
```

</div>