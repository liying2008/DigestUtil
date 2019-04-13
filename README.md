# DigestUtil

[![Download](https://api.bintray.com/packages/liying2008/DigestUtil/DigestUtil/images/download.svg)](https://bintray.com/liying2008/DigestUtil/DigestUtil/_latestVersion)
[![Maven Central](https://img.shields.io/maven-central/v/cc.duduhuo.util/digest-util.svg)](https://mvnrepository.com/artifact/cc.duduhuo.util/digest-util)
[![license](https://img.shields.io/github/license/liying2008/DigestUtil.svg)](https://github.com/liying2008/DigestUtil/blob/master/LICENSE)
[![简书](https://img.shields.io/badge/简书-独毒火-brightgreen.svg)](http://www.jianshu.com/u/14ab91761183)

A digest utils library written in Kotlin (For Java and Android) .

## 1. Download

### Use Gradle  

```gradle  
implementation 'cc.duduhuo.util:digest-util:1.1.1'
```

### Or Maven  

```xml  
<dependency>
  <groupId>cc.duduhuo.util</groupId>
  <artifactId>digest-util</artifactId>
  <version>1.1.1</version>
  <type>pom</type>
</dependency>
```

## 2. Use

Call the static method in `Digest` / `Base64` / `AES` / `DES` .

**In Kotlin**

```kotlin
println("============== Base64 ==============")
println("base64 = " + Base64.encode("abc"))

println("============== Digest ==============")
println("md2 = " + Digest.md2Hex("abc", true))
println("md5 = " + Digest.md5Hex("abc", true))
println("sha1 = " + Digest.sha1Hex("abc", true))
println("sha224 = " + Digest.sha224Hex("abc", true))
println("sha256 = " + Digest.sha256Hex("abc", true))
println("sha384 = " + Digest.sha384Hex("abc", true))
println("sha512 = " + Digest.sha512Hex("abc", true))
// File digest
println("sha256 = " + Digest.sha256Hex(File("build.gradle"), true))

println("============== CRC32 ==============")
println("crc32 = " + CRC32.getValue("abc"))
println("crc32 = " + CRC32.getValue(File("build.gradle")))
```

**In Java**

```java
System.out.println("============== Base64 ==============");
System.out.println("base64 = " + Base64.encode("abc"));

System.out.println("============== Digest ==============");
System.out.println("md2 = " + Digest.md2Hex("abc", true));
System.out.println("md5 = " + Digest.md5Hex("abc", true));
System.out.println("sha1 = " + Digest.sha1Hex("abc", true));
System.out.println("sha224 = " + Digest.sha224Hex("abc", true));
System.out.println("sha256 = " + Digest.sha256Hex("abc", true));
System.out.println("sha384 = " + Digest.sha384Hex("abc", true));
System.out.println("sha512 = " + Digest.sha512Hex("abc", true));
// File digest
System.out.println("sha256 = " + Digest.sha256Hex(new File("build.gradle"), true));

System.out.println("============== CRC32 ==============");
System.out.println("crc32 = " + CRC32.getValue("abc"));
System.out.println("crc32 = " + CRC32.getValue(new File("build.gradle")));
```

## 3. ChangeLog

Updated date: 2019-04-13

[CHANGELOG](CHANGELOG.md)

## 4. Author

Email: [liruoer2008@yeah.net](mailto:liruoer2008@yeah.net)

## 5. License

[MIT](LICENSE)
