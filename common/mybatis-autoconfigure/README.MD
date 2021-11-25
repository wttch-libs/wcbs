# data-mybatis-autoconfigure

Mybatis 的 autoconfigure 扩展性不是很好，所以参照 mybatis-autoconfigure 重写，并为其添加部分可扩展功能。

因为想要可以自动添加一些 mapperLocation 等功能，所以才尝试进行重写，如果后面发现更优雅的方式（比如Mybatis插件）再进行修改。

注意：此模块是mybatis-autoconfigure的替代，所以其他使用mybatis的模块需要集成该模块即可，不用集成 mybatis-autoconfigure.