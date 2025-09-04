### Задание

Пакет `jetbrains.kotlin.course.alias.team` уже содержит обычный класс `TeamService`.
Он отвечает за игровую логику для команд. В этом задании вам нужно реализовать несколько вещей, чтобы оживить игру:

- Добавьте свойство `identifierFactory` типа `IdentifierFactory` для генерации идентификаторов для каждой команды.
  Не забудьте добавить значение по умолчанию (просто создайте новый экземпляр класса `IdentifierFactory`).
- Добавьте объект-компаньон в класс `TeamService` и объявите переменную `teamsStorage` для хранения всех предыдущих команд.
  Тип хранилища должен быть `MutableMap`, который отображает `Identifier` на `Team`. Не забудьте инициализировать его с помощью пустой карты.
- Реализуйте метод `generateTeamsForOneRound`.
  Метод должен генерировать список команд и также сохранять их всех в карту `teamsStorage`.
  Чтобы создать новые команды, вам нужно использовать `identifierFactory` из класса `TeamService` для генерации нового идентификатора.
  Мы должны создать этот метод для сохранения результатов игры для таблицы лидеров.

Если у вас возникнут трудности, **подсказки помогут вам решить это задание**.

<div class="hint" title="Нажмите, чтобы узнать, как преобразовать метод generateTeamsForOneRound из выраженной формы в обычную">

Метод `generateTeamsForOneRound` можно реализовать как в одной строке, так и в нескольких строках, 
поэтому при необходимости смело заменяйте 
```kotlin
fun generateTeamsForOneRound(teamsNumber: Int): List<Team> = TODO("Not implemented yet")
``` 
на 
```kotlin
fun generateTeamsForOneRound(teamsNumber: Int): List<Team> { 
  TODO("Not implemented yet") 
}
```

</div>

----

### Подсказки

<div class="hint" title="Нажмите, чтобы узнать, как импортировать Identifier">

Чтобы использовать `Identifier` и `IdentifierFactory`, вам нужно импортировать их в начале файла с классом `TeamService`:

  ```kotlin
  package jetbrains.kotlin.course.alias.team

  import jetbrains.kotlin.course.alias.util.Identifier
  import jetbrains.kotlin.course.alias.util.IdentifierFactory
  ```
</div>

<div class="hint" title="Нажмите, чтобы узнать, как создать класс IdentifierFactory">

Поскольку класс `IdentifierFactory` имеет значение по умолчанию для свойства `counter`,
вам не нужно устанавливать его в конструкторе:

  ```kotlin
  val identifierFactory = IdentifierFactory() // ПРАВИЛЬНО
  ```
</div>

<div class="hint" title="Нажмите, чтобы узнать, как сгенерировать список с командами">

Как мы упоминали в первом модуле, вы можете сгенерировать новый список с `N` элементами, используя следующую конструкцию:
  ```kotlin
  List(N) { Team(...) }
  ```
</div>

<div class="hint" title="Нажмите, чтобы узнать об использовании встроенной функции putIfAbsent">

При работе с `map` вы можете использовать встроенную функцию `putIfAbsent`, чтобы вставить новое значение, если оно еще не присутствует в `map`:
  ```kotlin
  val myMap = mutableMapOf<Int, String>()
  if (1 !in myMap.keys) {
      myMap[1] = "one"
  }
  ```
Это то же самое, что и:
  ```kotlin
  val myMap = mutableMapOf<Int, String>()
  myMap.putIfAbsent(1, "one")
  ```
</div>

<div class="hint" title="Нажмите, чтобы узнать об использовании встроенной функции forEach">

Если вам нужно обработать каждый элемент в коллекции, например, в списке или карте,
вы можете использовать встроенную функцию `forEach` вместо цикла `for`.
В таком случае вам нужно написать действие в фигурных скобках:
  ```kotlin
  val teams = List(N) { Team(...) }
  for (team in teams) {
    teamsStorage.putIfAbsent(it.id, it)
  }
  ```
Это то же самое, что и:
  ```kotlin
  val teams = List(N) { Team(...) }
  teams.forEach { teamsStorage.putIfAbsent(it.id, it) }
  ```
</div>