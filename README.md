DigestUtil
=======
[![Download](https://api.bintray.com/packages/liying2008/DigestUtil/DigestUtil/images/download.svg)](https://bintray.com/liying2008/DigestUtil/DigestUtil/_latestVersion)
[![license](https://img.shields.io/github/license/liying2008/DigestUtil.svg)](https://github.com/liying2008/DigestUtil/blob/master/LICENSE)
[![简书](https://img.shields.io/badge/简书-独毒火-brightgreen.svg)](http://www.jianshu.com/u/14ab91761183)

引入(Download)
----
## Use Gradle  
```gradle  
compile 'cc.duduhuo.util:digest-util:1.0.1'
```

## Or Maven  
```xml  
<dependency>
  <groupId>cc.duduhuo.util</groupId>
  <artifactId>digest-util</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

使用(Usage)
----
#### 1. 在 <code>build.gradle</code> 中配置 **Kotlin** 的开发环境，例如：
```gradle
apply plugin: 'kotlin'

buildscript {
    ext.kotlin_version = '1.1.60'

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}

dependencies {
    compile "org.jetbrains.kotlin:kotlin-stdlib-jre8:$kotlin_version"
}
```

#### 2. 直接调用 <code>Digest</code> / <code>Base64</code> / <code>AES</code> / <code>DES</code> 中的静态方法

**In Kotlin**
```kotlin
println("============== Digest ==============")
println("md2 = " + Digest.md2Hex("abc", true))
println("md5 = " + Digest.md5Hex("abc", true))
println("sha1 = " + Digest.sha1Hex("abc", true))
println("sha224 = " + Digest.sha224Hex("abc", true))
println("sha256 = " + Digest.sha256Hex("abc", true))
println("sha384 = " + Digest.sha384Hex("abc", true))
println("sha512 = " + Digest.sha512Hex("abc", true))
println("hex = " + Digest.hex("abc", true))

println("============== AES ==============")
val input1 = "abc"
val seed1 = "12345678"
val e1 = AES.encrypt(input1, seed1)
println("AES encrypt = $e1")
val d1 = AES.decrypt(e1, seed1)
println("AES decrypt = $d1")

println("============== DES ==============")
val input2 = "abc"
val key2 = "12345678"
val e2 = DES.encrypt(input2, key2)
println("DES encrypt = $e2")
val d2 = DES.decrypt(e2, key2)
println("DES decrypt = $d2")
```

**In Java**
```java
System.out.println("============== Digest ==============");
System.out.println("md2 = " + Digest.md2Hex("abc", true));
System.out.println("md5 = " + Digest.md5Hex("abc", true));
System.out.println("sha1 = " + Digest.sha1Hex("abc", true));
System.out.println("sha224 = " + Digest.sha224Hex("abc", true));
System.out.println("sha256 = " + Digest.sha256Hex("abc", true));
System.out.println("sha384 = " + Digest.sha384Hex("abc", true));
System.out.println("sha512 = " + Digest.sha512Hex("abc", true));
System.out.println("hex = " + Digest.hex("abc", true));

System.out.println("============== AES ==============");
String input1 = "abc";
String seed1 = "12345678";
String e1 = AES.encrypt(input1, seed1);
System.out.println("AES encrypt = " + e1);
String d1 = AES.decrypt(e1, seed1);
System.out.println("AES decrypt = " + d1);

System.out.println("============== DES ==============");
String input2 = "abc";
String key2 = "12345678";
String e2 = DES.encrypt(input2, key2);
System.out.println("DES encrypt = " + e2);
String d2 = DES.decrypt(e2, key2);
System.out.println("DES decrypt = " + d2);
```

更新日志(ChangeLog)
----
[点击查看更新日志](CHANGELOG.md)

作者(Author)
---- 
简书：[独毒火](http://www.jianshu.com/u/14ab91761183)

邮箱：[liruoer2008@yeah.net](mailto:liruoer2008@yeah.net)

日期(Date)
----
2017-11-18

License
----
[MIT](LICENSE)