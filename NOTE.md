# Note

This repository only stores an initial build for Kotlin/Multiplatform using Gradle.


The code currently cannot be done due to the limitations of the platforms and libraries used:
```markdown
  1. The Kotlin/Native platform does not support multi-threaded coroutines (ref. #462);
  2. The Ktor library does not support ~~web~~sockets on the Kotlin/Native platform (ref. #1215);
  3. The Ktor/Network library does not support the Kotlin/Native platform for Windows.
```


For now, the forecast of libraries being used is:

```markdown
  1. Serialization extension for Kotlin/Multiplatform: kotlinx.serialization;
  2. Sockets extension for Kotlin/Multiplatform: ktor/network;
  3. **Web**Sockets extension for Kotlin/Multiplatform: ktor/features/websockets;
  4. Atomic operations extension for Kotlin/Multiplatform: kotlinx.atomicfu;
  5. Logging extension for Kotlin/Multiplatform: MicroUtils/kotlin-logging.
```


We intend to use as little external code as possible to ensure a lightweight library.

The goal of the library is to have code that is performant, but at the same time gives users the freedom to create their
own abstractions, so we intend to divide it into 5 modules, each of which has its own
*default* abstractions:

* `Low-level` Cache;
* `Low-level` [Gateway][gateway];
* `Low-level` [Rest API][intro];
* `High-level` Core;

## Reference

`#462` https://github.com/Kotlin/kotlinx.coroutines/issues/462

`#1215` https://github.com/ktorio/ktor/issues/1215

**The last update to this file was at March 1, 2021.**

[gateway]: https://discord.com/developers/docs/topics/gateway

[intro]: https://discord.com/developers/docs/intro
