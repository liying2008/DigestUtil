# DigestUtil & CryptoUtil

[![Maven Central Version](https://img.shields.io/maven-central/v/cc.duduhuo.util/digest-util?label=digest-util)](https://central.sonatype.com/artifact/cc.duduhuo.util/digest-util)
[![Maven Central Version](https://img.shields.io/maven-central/v/cc.duduhuo.util/digest-util-ktx?label=digest-util-ktx)](https://central.sonatype.com/artifact/cc.duduhuo.util/digest-util-ktx)
[![Maven Central Version](https://img.shields.io/maven-central/v/cc.duduhuo.util/crypto-util?label=crypto-util)](https://central.sonatype.com/artifact/cc.duduhuo.util/crypto-util)

**Digest** / **Hmac** / **Base64** / **Crypto** utils for Java(Kotlin) and Android.


## digest-util

### 1. Download

- Use Gradle  

```kotlin  
implementation("cc.duduhuo.util:digest-util:2.1.0")
```

- Use Maven  

```xml  
<dependency>
  <groupId>cc.duduhuo.util</groupId>
  <artifactId>digest-util</artifactId>
  <version>2.1.0</version>
</dependency>
```

> If you use **Kotlin**, you can use `digest-util-ktx` instead. `digest-util-ktx` is a Kotlin extension library for `digest-util` and it provides some simple and easy-to-use extension methods.

- Use Gradle

```kotlin  
implementation("cc.duduhuo.util:digest-util-ktx:2.1.0")
```

- Use Maven

```xml  
<dependency>
  <groupId>cc.duduhuo.util</groupId>
  <artifactId>digest-util-ktx</artifactId>
  <version>2.1.0</version>
</dependency>
```

### 2. Use

Call the static method in `Digest` / `Hmac` / `Base64` / `CRC32` .

- **Kotlin Demo**

```kotlin
println("============== Base64 ==============")
println("[Base64] encode = " + "abc".base64().encodeToString())
println("[Base64] decode = " + "YWJj".base64().decodeToString())
println("[Base64] encode = " + File("build.gradle.kts").base64().encodeToString())

println("============== Digest ==============")
println("[Digest] md2 = " + "abc".md2().hex())
println("[Digest] md5 = " + "abc".md5().hex())
println("[Digest] sha1 = " + "abc".sha1().hex())
println("[Digest] sha224 = " + "abc".sha224().hex())
println("[Digest] sha256 = " + "abc".sha256().hex())
println("[Digest] sha384 = " + "abc".sha384().hex())
println("[Digest] sha512 = " + "abc".sha512().hex())
println("[Digest] sha512_224 = " + "abc".sha512_224().hex())
println("[Digest] sha512_256 = " + "abc".sha512_256().hex())
println("[Digest] sha3_224 = " + "abc".sha3_224().hex())
println("[Digest] sha3_256 = " + "abc".sha3_256().hex())
println("[Digest] sha3_384 = " + "abc".sha3_384().hex())
println("[Digest] sha3_512 = " + "abc".sha3_512().hex())
// File digest
println("[Digest] sha256 = " + File("build.gradle.kts").sha256().hex())

println("============== Hmac ==============")
val key = "a key".toByteArray()
println("[Hmac] hmacMd5 = " + "abc".hmacMd5(key).hex())
println("[Hmac] hmacSha1 = " + "abc".hmacSha1(key).hex())
println("[Hmac] hmacSha224 = " + "abc".hmacSha224(key).hex())
println("[Hmac] hmacSha256 = " + "abc".hmacSha256(key).hex())
println("[Hmac] hmacSha384 = " + "abc".hmacSha384(key).hex())
println("[Hmac] hmacSha512 = " + "abc".hmacSha512(key).hex())
// File digest
println("[Hmac] hmacSha256 = " + File("build.gradle.kts").hmacSha256(key).hex())

println("============== CRC32 ==============")
println("[CRC32] value = " + "abc".crc32())
println("[CRC32] value = " + File("build.gradle.kts").crc32())
```

- **Java Demo**

```java
System.out.println("============== Base64 ==============");
System.out.println("[Base64] encode = " + Base64.encodeToString("abc"));
System.out.println("[Base64] decode = " + Base64.decodeToString("YWJj"));
System.out.println("[Base64] encode = " + Base64.encodeToString(new File("build.gradle.kts")));

System.out.println("============== Digest ==============");
System.out.println("[Digest] md2 = " + Hex.hex(Digest.md2("abc")));
System.out.println("[Digest] md5 = " + Hex.hex(Digest.md5("abc")));
System.out.println("[Digest] sha1 = " + Hex.hex(Digest.sha1("abc")));
System.out.println("[Digest] sha224 = " + Hex.hex(Digest.sha224("abc")));
System.out.println("[Digest] sha256 = " + Hex.hex(Digest.sha256("abc")));
System.out.println("[Digest] sha384 = " + Hex.hex(Digest.sha384("abc")));
System.out.println("[Digest] sha512 = " + Hex.hex(Digest.sha512("abc")));
System.out.println("[Digest] sha512_224 = " + Hex.hex(Digest.sha512_224("abc")));
System.out.println("[Digest] sha512_256 = " + Hex.hex(Digest.sha512_256("abc")));
System.out.println("[Digest] sha3_224 = " + Hex.hex(Digest.sha3_224("abc")));
System.out.println("[Digest] sha3_256 = " + Hex.hex(Digest.sha3_256("abc")));
System.out.println("[Digest] sha3_384 = " + Hex.hex(Digest.sha3_384("abc")));
System.out.println("[Digest] sha3_512 = " + Hex.hex(Digest.sha3_512("abc")));
// File digest
System.out.println("[Digest] sha256 = " + Hex.hex(Digest.sha256(new File("build.gradle.kts"))));

System.out.println("============== Hmac ==============");
byte[] key = "a key".getBytes();
System.out.println("[Hmac] hmacMd5 = " + Hex.hex(Hmac.hmacMd5("abc", key)));
System.out.println("[Hmac] hmacSha1 = " + Hex.hex(Hmac.hmacSha1("abc", key)));
System.out.println("[Hmac] hmacSha224 = " + Hex.hex(Hmac.hmacSha224("abc", key)));
System.out.println("[Hmac] hmacSha256 = " + Hex.hex(Hmac.hmacSha256("abc", key)));
System.out.println("[Hmac] hmacSha384 = " + Hex.hex(Hmac.hmacSha384("abc", key)));
System.out.println("[Hmac] hmacSha512 = " + Hex.hex(Hmac.hmacSha512("abc", key)));
// File Hmac
System.out.println("[Hmac] hmacSha256 = " + Hex.hex(Hmac.hmacSha256(new File("build.gradle.kts"), key)));

System.out.println("============== CRC32 ==============");
System.out.println("[CRC32] value = " + CRC32.getValue("abc"));
System.out.println("[CRC32] value = " + CRC32.getValue(new File("build.gradle.kts")));
```

### 3. ChangeLog

- digest-util: [CHANGELOG](digest-util/CHANGELOG.md)
- digest-util-ktx: [CHANGELOG](digest-util-ktx/CHANGELOG.md)


## crypto-util

### 1. Download

- Use Gradle  

```kotlin  
implementation("cc.duduhuo.util:crypto-util:1.0.0")
```

- Use Maven  

```xml  
<dependency>
  <groupId>cc.duduhuo.util</groupId>
  <artifactId>crypto-util</artifactId>
  <version>1.0.0</version>
</dependency>
```

### 2. Use

You can create `AES` / `DES` / `TripleDES` / `Blowfish` / `RC2` / `RC4` / `ChaCha20` / `ChaCha20Poly1305` instance and call the `encrypt` / `decrypt` method.

It provides a rich configuration items, as well as intuitive defaults .

> Pure JDK internal implementation, with **NO** third-party dependencies.

- **Java Demo**

```java
package cc.duduhuo.util.sample;

import cc.duduhuo.util.crypto.AES;
import cc.duduhuo.util.crypto.Blowfish;
import cc.duduhuo.util.crypto.ChaCha20;
import cc.duduhuo.util.crypto.ChaCha20Poly1305;
import cc.duduhuo.util.crypto.DES;
import cc.duduhuo.util.crypto.RC2;
import cc.duduhuo.util.crypto.RC4;
import cc.duduhuo.util.crypto.TripleDES;

public class CryptoUtilDemo {
    public static void aes() {
        System.out.println("============== AES/GCM ==============");
        String plaintext = "中文abc123";
        String key = "0123456789abcdef";
        AES.GCM cipher = new AES.GCM();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[AES/GCM] encrypted: " + ct); // [AES/GCM] encrypted: aRsw9ZBEsyVPJR+oLJ4s8yeGQkX76NLGAOaxqjqyyzhdKn3hq9C13w==
        String pt = cipher.decrypt(ct, key);
        System.out.println("[AES/GCM] decrypted: " + pt); // [AES/GCM] decrypted: 中文abc123
        // 还支持 AES/ECB、AES/CBC、AES/CTR、AES/CFB、AES/OFB 等模式
    }

    public static void des() {
        System.out.println("============== DES/CBC ==============");
        String plaintext = "中文abc123";
        String key = "01234567";
        DES.CBC cipher = new DES.CBC();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[DES/CBC] encrypted: " + ct); // [DES/CBC] encrypted: 7t9J4VIa2TiZtV+SmNlYYF6EaSNnbjdE
        String pt = cipher.decrypt(ct, key);
        System.out.println("[DES/CBC] decrypted: " + pt); // [DES/CBC] decrypted: 中文abc123
        // 还支持 DES/ECB、DES/CTR、DES/CFB、DES/OFB 等模式
    }

    public static void tripleDes() {
        System.out.println("============== Triple DES/CBC ==============");
        String plaintext = "中文abc123";
        String key = "0123456789abcdef01234567";
        TripleDES.CBC cipher = new TripleDES.CBC();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[Triple DES/CBC] encrypted: " + ct); // [Triple DES/CBC] encrypted: 78Rn4ZSWREOFBlwhDCJkOmiXXNQNz8do
        String pt = cipher.decrypt(ct, key);
        System.out.println("[Triple DES/CBC] decrypted: " + pt); // [Triple DES/CBC] decrypted: 中文abc123
        // 还支持 DESede/ECB、DESede/CTR、DESede/CFB、DESede/OFB 等模式
    }

    public static void blowfish() {
        System.out.println("============== Blowfish/CBC ==============");
        String plaintext = "中文abc123";
        String key = "01234567";
        Blowfish.CBC cipher = new Blowfish.CBC();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[Blowfish/CBC] encrypted: " + ct); // [Blowfish/CBC] encrypted: 6k2MvcN3a1APzrsYlUZSuCkd7S7Ejd0f
        String pt = cipher.decrypt(ct, key);
        System.out.println("[Blowfish/CBC] decrypted: " + pt); // [Blowfish/CBC] decrypted: 中文abc123
        // 还支持 Blowfish/ECB、Blowfish/CTR、Blowfish/CFB、Blowfish/OFB 等模式
    }

    public static void rc2() {
        System.out.println("============== RC2/CBC ==============");
        String plaintext = "中文abc123";
        String key = "01234567";
        RC2.CBC cipher = new RC2.CBC();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[RC2/CBC] encrypted: " + ct); // [RC2/CBC] encrypted: 3uZ0Y8JENBRx6a2vpTGzCnbHSg5oBWb8
        String pt = cipher.decrypt(ct, key);
        System.out.println("[RC2/CBC] decrypted: " + pt); // [RC2/CBC] decrypted: 中文abc123
        // 还支持 RC2/ECB、RC2/CTR、RC2/CFB、RC2/OFB 等模式
    }

    public static void rc4() {
        System.out.println("============== RC4 ==============");
        String plaintext = "中文abc123";
        String key = "01234567";
        RC4 cipher = new RC4();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[RC4] encrypted: " + ct); // [RC4] encrypted: dW1e+dCgRFJTYolp
        String pt = cipher.decrypt(ct, key);
        System.out.println("[RC4] decrypted: " + pt); // [RC4] decrypted: 中文abc123
    }

    public static void chacha20() {
        System.out.println("============== ChaCha20 ==============");
        String plaintext = "中文abc123";
        String key = "0123456789abcdef0123456789abcdef";
        ChaCha20 cipher = new ChaCha20();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[ChaCha20] encrypted: " + ct); // [ChaCha20] encrypted: O43JS57JHA6m/OtlC/E4lSFU95KwwKkB
        String pt = cipher.decrypt(ct, key);
        System.out.println("[ChaCha20] decrypted: " + pt); // [ChaCha20] decrypted: 中文abc123
    }

    public static void chacha20Poly1305() {
        System.out.println("============== ChaCha20-Poly1305 ==============");
        String plaintext = "中文abc123";
        String key = "0123456789abcdef0123456789abcdef";
        ChaCha20Poly1305 cipher = new ChaCha20Poly1305();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[ChaCha20-Poly1305] encrypted: " + ct); // [ChaCha20-Poly1305] encrypted: s3q6HsDD8kTQANjgU5D6ynec8ElKuy6eSF5Jc1WVf54eGE5dKZnejw==
        String pt = cipher.decrypt(ct, key);
        System.out.println("[ChaCha20-Poly1305] decrypted: " + pt); // [ChaCha20-Poly1305] decrypted: 中文abc123
    }

    public static void main(String[] args) {
        aes();
        des();
        tripleDes();
        blowfish();
        rc2();
        rc4();
        chacha20();
        chacha20Poly1305();
    }
}
```

### 3. ChangeLog

- crypto-util: [CHANGELOG](crypto-util/CHANGELOG.md)


## Author

Email: [liruoer2008@yeah.net](mailto:liruoer2008@yeah.net)


## License

[MIT](LICENSE)
