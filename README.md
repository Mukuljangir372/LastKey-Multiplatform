# LastKey-Multiplatform
🔑 LastKey is a kotlin compose multi-platform application for managing passwords, notes etc. 

---

### Tech Stack
1. Compose Multiplatform
2. Kotlin Coroutines
3. Ktor
4. SQLDelight
5. Kodein (DI)
6. Napier (Logging)
7. Ktlint (Lint)
8. androidx-Datastore (Key-Pair values)
9. Voyager (Navigation)
10. Mockative (Testing)
11. KSP
12. Kotlin AtomicFu (Atomic Operations)
13. Kotlin Serialization
14. Kotln DateTime
15. Kotlin Test (Testing)
16. Okio (By Square)
17. Gradle Version Catalog

---

### Architecture

LastKey following MVVM architecture with multi-module approach.

---

### Contribution

Contributions are appreciated, but must conform to LastKey Guidelines.

#### Branch naming

Branch names must respect the pattern `type/description-of-the-change`.

*Type* must be one of the following:

* `chore` for changes not related to the Kotlin source code, for example a change in the build config
* `doc` for changes related to source code documentation, or external document, like the README
* `feat` for a new feature for the app
* `fix` for bug fixes
* `refactor` for improving one or more unit of code, without impacting the behaviour of the app
* `test` for everything related to test ( add a new test suite, add a new test into an already existing test suite or improve/modify the performance or the behaviour of an already existing test )

_description of change_ must be a concise and meaningful description of what is expected by the change apported; words must be separated by a dash `-`

The whole name of the branch must be lower case.

#### Code style and pattern
Make sure, you run `./gradlew ktlintformat` before your pull request.
