# mybatis 通用查询

通用的mybatis查询，使用一些简单的注解标注，来简化api查询. 在请求体实体类/字段上做上标注，标注要查询的字段和类型，然后实现相关的查询功能。

## 1. 使用方式

在请求载体类上标注```@QueryEntity```, 在字段上标注```@QueryColumn```或者其他的该包下的其他特殊的注解类型.

下面是个简单的例子，会在查询sql后添加对 column 字段的查询 ```and column = ${name}```

```java

@QueryEntity // 标注这是个可以通用查询的实体类
public class Payload {
    @QueryStringItem(columnName = "column")
    private String name;
}
```

* __@QueryColumn:__ 所有的字段查询注解都会通过spring转换成该注解，该注解标注了要查询的字段的表名/字段简称和要查询类型（字符串精确匹配/模糊，数值等）。
* __@QueryStringColumn:__ 字符串精确查询；
* __@QueryStringFuzzyColumn:__ 字符串模糊查询，后缀模糊;
* __@QueryStringFullFuzzyColumn:__ 字符串全模糊查询;
* __@QueryStringListColumn:__ 字符串列表查询;