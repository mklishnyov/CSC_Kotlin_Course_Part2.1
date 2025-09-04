### Определение

Вы уже коротко знакомы с [`List`](https://kotlinlang.org/docs/collections-overview.html#list), который хранит список объектов одного типа, таких как `List<Int>`. Второй популярный тип коллекции — это [`Map`](https://kotlinlang.org/docs/collections-overview.html#map). Он хранит пары "ключ-значение" так, что все ключи различны, но значения могут повторяться. `Map` очень похож на адресную книгу, где вы можете найти соответствующий адрес для каждого человека. Соответственно, один и тот же адрес может встречаться несколько раз, но каждый человек будет в книге только один раз.

В Kotlin, если вы хотите создать _изменяемый_ `Map`, то вам нужно указать это _явно_, потому что по умолчанию создается _только для чтения_ коллекция, и позднее не удастся добавить в неё новые элементы.

Чтобы создать новую карту, вы можете использовать `mapOf` для _только для чтения_ коллекции или `mutableMapOf` для _изменяемой_ коллекции:

```kotlin
val readOnlyMap = mapOf<Int, String>(1 to "one", 2 to "two")
readOnlyMap.put(3 to "three") // ОШИБКА

val mutableMap =  mutableMapOf<Int, String>(1 to "one", 2 to "two")
mutableMap.put(3 to "three") // ОК
```

Чтобы создать пустую _изменяемую_ карту, необходимо использовать функцию `mutableMapOf` вместо `emptyMap`, используемой для _только для чтения_ карты:

```kotlin
val emptyMutableMapError: MutableMap<Int, Int> = emptyMap() // ОШИБКА

val emptyMutableMapOk: MutableMap<Int, Int> =  mutableMapOf() // ОК
```

#### встроенные функции

Вы можете найти много полезных встроенных функций для работы с картами в [официальной документации Kotlin](https://kotlinlang.org/docs/map-operations.html). Рассмотрим несколько базовых, которые могут помочь вам решить эту задачу.

<div class="hint" title="Нажмите, чтобы узнать о встроенном свойстве `keys`">

Если вам нужно получить все _ключи_ из карты только для чтения или изменяемой карты, вы можете использовать свойство [_keys_](https://kotlinlang.org/docs/map-operations.html#retrieve-keys-and-values):
  ```kotlin
  val readOnlyMap = mapOf<Int, String>(1 to "one", 2 to "two")
  for (key in readOnlyMap.keys) {
      println(key) // Выведет 1 и 2
  }
  ```
</div>

<div class="hint" title="Нажмите, чтобы узнать о встроенном свойстве `values`">

Если вам нужно получить все _значения_ из карты только для чтения или изменяемой карты, вы можете использовать свойство [_values_](https://kotlinlang.org/docs/map-operations.html#retrieve-keys-and-values):
  ```kotlin
  val mutableMap = mutableMapOf<Int, String>(1 to "one", 2 to "two")
  for (value in mutableMap.values) {
      println(value) // Выведет "one" и "two"
  }
  ```
</div>

<div class="hint" title="Нажмите, чтобы узнать, как получить значение по ключу">

Если вам нужно получить значение по ключу, можно использовать [следующую конструкцию](https://kotlinlang.org/docs/map-operations.html#retrieve-keys-and-values):
  ```kotlin
  val readOnlyMap = mapOf<Int, String>(1 to "one", 2 to "two")
  println(readOnlyMap[1]) // one
  ```

Однако она может вернуть `null`, если ключ не существует:
  ```kotlin
  val readOnlyMap = mapOf<Int, String>(1 to "one", 2 to "two")
  println(readOnlyMap[3]) // null
  ```
В таком случае вы можете использовать механизм [безопасности null](https://kotlinlang.org/docs/null-safety.html), обсужденный в прошлом модуле, для обработки ситуации:

  ```kotlin
  val readOnlyMap = mapOf<Int, String>(1 to "one", 2 to "two")
readOnlyMap[3]?.let {
      println(it) // Ничего не выведет, потому что readOnlyMap[3] это null
  }

  println(readOnlyMap[3] ?: "Некорректный ключ") // "Некорректный ключ", потому что readOnlyMap[3] это null
  println(readOnlyMap[2] ?: "Некорректный ключ") // "two", потому что readOnlyMap[2] не null
  ```

</div>