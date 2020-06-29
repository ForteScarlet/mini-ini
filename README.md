<div align="center" style="text-align: center; margin:0 auto;">
<h1>mini-ini</h1>
<a href="https://codebeat.co/projects/github-com-fortescarlet-mini-ini-master"><img alt="codebeat badge" src="https://codebeat.co/badges/2380d6ed-4d5d-4e62-a7d6-79c77a9cbddb" /></a> 
<a href="https://repo1.maven.org/maven2/love/forte/mini-ini/"><img alt="version" src="https://img.shields.io/maven-central/v/love.forte/mini-ini" /></a>
</div>


## What is this?
This is a mini util for `.ini` file.

>My English is poor, but I still try to edit comments in English. I hope you can understand.
> 
>Most come from google translation.
> 
>如果你会中文，那太好了，我们能够很好的交流。或许你可以看看[中文README](./README_CN.md)
> 
>and hope a star, Thanks~  ♪(´▽｀)

github: https://github.com/ForteScarlet/mini-ini

gitee : https://gitee.com/ForteScarlet/mini-ini

## How to use?
First, Click the star button in the upper right corner to make some hidden content take effect. :P


### Create project
Create project with:

> version: [![img](https://img.shields.io/maven-central/v/love.forte/mini-ini)](https://repo1.maven.org/maven2/love/forte/mini-ini/)

Maven:
```xml
<dependency>
    <groupId>love.forte</groupId>
    <artifactId>mini-ini</artifactId>
    <version>${version}</version>
</dependency>
```

Gradle:
```
compile group: 'love.forte', name: 'mini-ini', version: '${version}'
```

### Read ini
You can see part of the code that appears below from [Demo1](./src/test/java/com/forte/test/Demo1.java).

See [test.ini](./src/test/resources/test.ini)
```java
// Get input stream
InputStream iniInput = Demo1.class.getClassLoader().getResourceAsStream("test.ini");

// Read by default buffered reader
IniReader ir = new BufferedIniReader();
Ini ini = ir.read(iniInput);

// show
System.out.println(ini);

// to properties and show
ini.toProperties().forEach((k, v) -> {
    System.out.println(k + "=" + v);
});
```


### Create/Write ini
You can see part of the code that appears below from [Demo2](./src/test/java/com/forte/test/Demo2.java).

```java
 // Create an IniBuilder and
IniBuilder b = new IniBuilder()
        .plusComment("this is a test ini")
        .skipLine(2)
        .plusSection("sec1", "this is a section")
        .plusProperty("key1", "value")
        .plusProperty("key2", "value")
        .plusProperty("key3", "value")
        .plusProperty("key4", "value")
        .plusProperty("key5", "value")
        .plusProperty("key6", "value")
        .plusSection("sec2")
        .plusProperty("key1", "value")
        .plusProperty("key2", "value")
        .plusProperty("key3", "value")
        .plusProperty("key4", "value")
        .plusProperty("key5", "value")
        .plusProperty("key6", "value")
;

// Build ini
final Ini ini = b.build();

// show
System.out.println(ini);

// Write to file
ini.write(Paths.get("F:\\test3.ini"), true);
```

## Features 
* `IniElement` implements the interface `java.io.Serializable`, which can be serialized. (1.1.0)
* `Ini` inherits `ArrayList<IniElement>`, which can be serialized and used as a list. 
* `IniProperty` implements the interface `Map.Entry<String, String>`. 
* `IniSection` implements the interface `List<IniProperty>`, which can be used as a list.

## Customize
You can customize (implement the interface) to achieve some additional parsing methods.

I provide some easy-to-implement (probably) interfaces to support user customization. 
If you want, you can also open source your additional implementations. If you open source, you can tell me, I will show the address in the README.


some interface or abstract class:

Ini elements like `IniElement`、`BaseElement` or `IniComment`(Default implementation is `IniCommentImpl`)、`IniProperty`(Default implementation is `IniPropertyImpl`)、`IniSection`(Default implementation is `IniSectionImpl`).

Ini reader like `IniReadable`、`IniReader` or Refer to the default implementation `BufferedIniReader`.

Ini formatter like `IniFormatter`、`IniFormatterFactory`、`ElementFormatter`.

You can refer to [Demo3](./src/test/java/com/forte/test/Demo3.java).

## Attention
- This project has no additional dependencies, so you should be able to use it directly by importing the jar package.

- By default, when parsing an ini file, the content of the ini file is divided into several parts:
```ini
# comment
[section1] # comment
# comment
property_key1=property_value
property_key2=property_value
property_key3=property_value
[section2] # comment
property_key1=property_value
property_key2=property_value
property_key3=property_value
```
- By default, the rules are:
    - section is wrapped by [], and there can be comments after the end.
    - comment starts with `#` and can be at the beginning of a line or the end of a section.
    - Property is a key-value pair under section, and cannot be followed by a comment.



## I found a bug
Oh! nice! You can tell me through the issue, or submit the bug through branch submission. try to use simple English or Chinese. Thanks.


## end
if you like this, Give me a star or a little pocket money~
if you don't, May consider helping me improve the code 

Reward me:

<br>

afdian: https://afdian.net/@ForteScarlet

<br>

alipay：

<img src="./img/支付宝收款.jpg" height=600px>

<br>

wechat：

<img src="./img/微信收款.png" height=600px>



My level is limited, please understand if there are any problems.

<br>

<div align="center" style="text-align: center; margin:0 auto;">
<p><b>license: Apache License 2.0</b></p>
</div>



