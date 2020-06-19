<div align="center" style="text-align: center; margin:0 auto;">
<h1>mini-ini</h1>
<a href="https://codebeat.co/projects/github-com-fortescarlet-mini-ini-master"><img alt="codebeat badge" src="https://codebeat.co/badges/2380d6ed-4d5d-4e62-a7d6-79c77a9cbddb" /></a> 
<a href="https://repo1.maven.org/maven2/love/forte/mini-ini/"><img alt="version" src="https://img.shields.io/maven-central/v/love.forte/mini-ini" /></a>
</div>

## 这是啥
这是一个用于操作`.ini`格式文件的工具。

> My English is poor, but I still try to edit comments in English. I hope you can understand.
> If you want to read crappy English, you can see [Englich readme](./README.md)
> 
> 给我个星星啦~  ♪(´▽｀)

github: https://github.com/ForteScarlet/mini-ini

gitee : https://gitee.com/ForteScarlet/mini-ini

## 怎么用？
首先，点击右上角的星标按钮，以展示隐藏内容。（滑稽


## 创建项目
这是肯定的啦。你需要一个创建一个项目，并导入相关依赖：

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


### 读取 ini
下述的Demo代码你可以从 [Demo1](./src/test/java/com/forte/test/Demo1.java)文件中找到。

测试用的ini文件是：[test.ini](./src/test/resources/test.ini)

```java
// 得到输入流
InputStream iniInput = Demo1.class.getClassLoader().getResourceAsStream("test.ini");

// 通过默认的bufferedIniReader类读取ini文件
IniReader ir = new BufferedIniReader();
Ini ini = ir.read(iniInput);

// 打印展示
System.out.println(ini);

// 转化为properties文件并展示
ini.toProperties().forEach((k, v) -> {
    System.out.println(k + "=" + v);
});
```


### 创建/输出 ini
下述的Demo代码你可以从 [Demo2](./src/test/java/com/forte/test/Demo2.java)文件中找到。


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

## 自定义
可能我提供的默认解析类等无法满足你的需求，这时候你可以通过实现定义的接口来进行自定义。

我提供了一些（大概）便于实现的接口来支持使用者的自定义。如果你想，你也可以将你的额外实现开源出来。如果你开源了，可以告诉我，我会将地址展示在README中。

一些接口和抽象类的指路：

ini元素相关：父类接口/抽象类: `IniElement`、`BaseElement`,  具体元素的接口和默认实现：`IniComment`(默认实现：`IniCommentImpl`)、`IniProperty`(默认实现：`IniPropertyImpl`)、`IniSection`(默认实现：`IniSectionImpl`)。

ini读取相关：父类接口：`IniReadable`、`IniReader` 或参考默认实现：`BufferedIniReader`。

Ini解析器相关：`IniFormatter`、`IniFormatterFactory`、`ElementFormatter`。

你或许可以参考 [Demo3](./src/test/java/com/forte/test/Demo3.java)。

## 注意

- 该项目没有其他依赖项，因此您应该可以通过导入jar包直接使用它。

- 默认情况下，在解析ini文件时，ini文件的内容分为以下几部分：
```ini
# 注释
[节1] # 标题(节)后的注释
# 注释, 下面是节1的键值对儿
property_key1=property_value
property_key2=property_value
property_key3=property_value
[节2] # 注释
property_key1=property_value
property_key2=property_value
property_key3=property_value
```
- 默认情况下，规则为：
    - 节(section)是由`[]`括起来的。结尾处可以有注释。
    - 注释以 `#` 开头，可以在一行的开头或节的结尾。
    - 属性是本节下的键/值对，并且不能在其后加上注释。

## 我发现了bug
哦! niiiice！您可以通过issue告诉我，或者通过分支提交等方式提交bug。请尽可能使用中文或者简易的英文。


## 结尾
如果你喜欢这个项目，你可以点个star或者打赏一些零花钱以资鼓励~
如果你不喜欢这个项目，你可以帮助我完善项目或者提出一些建议。



资助我:

<br>

爱发电: https://afdian.net/@ForteScarlet

<br>

支付宝：

<img src="./img/支付宝收款.jpg" height=600px>

<br>

微信：

<img src="./img/微信收款.png" height=600px>



个人水平有限，如果问题尚请谅解。

<br>

<div align="center" style="text-align: center; margin:0 auto;">
<p><b>license: Apache License 2.0</b></p>
</div>


