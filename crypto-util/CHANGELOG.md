## v1.0.0 (2026-06-08)

- `AES.encrypt()` -> `new AES.ECB().keyLength(16).keyPadding((byte) 0).encrypt()`， `decrypt` 同理。
- `DES.encrypt()` -> `new DES.ECB().keyPadding((byte) 0).encrypt()`， `decrypt` 同理。
- 扩展 `AES`, `DES` 功能，提供 `CBC`, `CFB`, `OFB`, `CTR`, `GCM`（仅AES） 等工作模式的支持。
- 支持更多的加密算法，如 `Blowfish`, `ChaCha20`, `ChaCha20Poly1305`, `TripleDES`, `RC2`, `RC4` 等。
- 各个加密算法的工具类都提供了灵活的配置项，以及符合规范的默认值。
- 完善测试用例，确保加解密输出结果正确无误。
- 需要特别指出的是：如果是新项目请选择安全性更高的加密算法，如 `AES/GCM`, `ChaCha20Poly1305`，本库提供的 `DES/Blowfish/TripleDES/RC2/ChaCha20/RC4` 仅用于无法迁移的老系统。
- 移除对 Kotlin 的依赖，使用 Java 语言重写。
- **【破坏性更新】** 最低支持的JDK版本为 11 。
