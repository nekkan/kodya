# Note

This repository only stores an initial build for Kotlin/Multiplatform using Gradle.

The code currently cannot be done due to the limitations of the platforms and libraries used:

* The Kotlin/Native platform does not support multi-threaded coroutines ([#462][multithreaded-coroutines]);
* The Ktor library does not support ~~web~~sockets on the Kotlin/Native platform ([#1215][multithreaded-coroutines]);
* The Ktor/Network library does not support the Kotlin/Native platform for Windows.

For now, the forecast of libraries being used is:

* Serialization extension for Kotlin/Multiplatform: kotlinx.serialization;
* Sockets extension for Kotlin/Multiplatform: ktor/network;
* **Web**Sockets extension for Kotlin/Multiplatform: ktor/features/websockets;
* Atomic operations extension for Kotlin/Multiplatform: kotlinx.atomicfu;
* Logging extension for Kotlin/Multiplatform: MicroUtils/kotlin-logging.

We intend to use as little external code as possible to ensure a lightweight library.

The goal of the library is to have code that is performant, but at the same time gives users the freedom to create their
own abstractions, so we intend to divide it into 5 modules, each of which has its own
*default* abstractions:

* `Low-level` Cache;
* `Low-level` [Gateway][gateway];
* `Low-level` [Rest API][intro];
* `High-level` Core;

**The last update to this file was at March 1, 2021.**

[multithreaded-coroutines]: https://github.com/Kotlin/kotlinx.coroutines/issues/462

[native-websockets]: https://github.com/ktorio/ktor/issues/1215

[gateway]: https://discord.com/developers/docs/topics/gateway

[intro]: https://discord.com/developers/docs/intro
