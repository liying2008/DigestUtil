## v2.1.0 (2026-07-05)

- 新增 `Hmac` 系列工具方法（含 `HmacMD5` / `HmacSHA1` / `HmacSHA224` / `HmacSHA256` / `HmacSHA384` / `HmacSHA512`）。

## v2.0.0 (2026-06-08)

- 新增 `SHA-512/224`, `SHA-512/256`, `SHA3`系列 算法。
- 扩展 `Base64`, `CRC32`, `Digest` 功能，优化调用方式。
- 移除对 Kotlin 的依赖，使用 Java 语言重写。
- Kotlin 项目可依赖 `cc.duduhuo.util:digest-util-ktx` 扩展库，提供更简单易用且符合直觉的使用方式。
- **【破坏性更新】** 加密相关工具类（`AES`、`DES`）迁移到新库 `cc.duduhuo.util:crypto-util` 。
- **【破坏性更新】** 最低支持的JDK版本为 11 。

## v1.1.1 (2019-04-13)

- 去掉方法上的 `@Throws` 注解。

## v1.1.0 (2018-11-24)

- `Digest` 工具支持传入 `File` 对象；
- 支持 `CRC32` 校验和计算；
- 支持 `Android` 平台。

## v1.0.1 (2018-04-25)

- 更改 `DES` / `AES` 生成 `SecretKey` 的方式。

## v1.0.0 (2018-04-25)

- 首次提交，新增 `Digest` / `Base64` / `AES` / `DES` 。
