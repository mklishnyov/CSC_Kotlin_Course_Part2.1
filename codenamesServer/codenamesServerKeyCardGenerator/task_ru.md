Создайте интерфейс SAM для генерации ключевых карточек:

- Добавьте value class `KeyCardCell` в пакет `jetbrains.kotlin.course.codenames.keyCard` в файле `KeyCardModel.kt` с одним полем `type: KeyCardType`.
- Добавьте интерфейс SAM `KeyCardGenerator` в пакет `jetbrains.kotlin.course.codenames.utils` с функцией `generateData`, которая ничего не принимает и возвращает `List<KeyCardCell>`.

<div class="hint" title="Нажмите здесь, если вы нажали 'Проверить' и нашли ошибку компиляции">

Если у вас ошибка компиляции и вы еще не решили этот шаг, пожалуйста, решите задачу и попробуйте снова. Это ожидаемое поведение, так как код требует наличие value class `KeyCardCell`, но он не существует.
</div>

Если у вас возникли трудности, **подсказки помогут вам решить эту задачу**.

----

### Подсказки

<div class="hint" title="Нажмите здесь, чтобы узнать о аннотации JvmInline для value классов">

Не забудьте использовать аннотацию `JvmInline` с value class `KeyCardCell`:
```kotlin
@JvmInline
value class KeyCardCell(...)
```
</div>

<div class="hint" title="Нажмите здесь, чтобы узнать о импорте классов из другого пакета">

Класс `KeyCardCell` определен в пакете `jetbrains.kotlin.course.codenames.keyCard`, но `KeyCardGenerator` должен быть определен в `jetbrains.kotlin.course.codenames.utils`.
Чтобы использовать `KeyCardCell` в возвращаемом типе функции `generateData`, не забудьте импортировать класс `KeyCardCell`:

```kotlin
import jetbrains.kotlin.course.codenames.keyCard.KeyCardCell
```
</div>