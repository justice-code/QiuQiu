# QiuQiu
期待。
* 一款基于Mybatis的分库分表中间件（Sql重写）。
* 当前实现的功能 -- 基于传入参数和个人配置，重写sql的schema。
* 依赖：Java8
* 测试：运行```org.eddy.ApplicationStart```类的main函数
* [中文指南](https://github.com/justice-code/QiuQiu/wiki)

## 0.2
* 支持多参数绑定
``` java
    @KeyParam({"begin", "end"})
    List<User> selectUsers(@Param("begin") Integer begin, @Param("end") Integer end);
```
* 增加默认绑定参数，mapper（mapper + 接口名）
``` xml
<rule table="user" column="id" javaType="java.lang.Integer" class="org.eddy.xml.rule.impl.UserComparator">
        <data schema="schema1" table="user" script="! 'UserMapper.selectUsers'.equals(mapper) &amp;&amp; 1 == params[0]"/>
        <data schema="schema2" table="user" script="! 'UserMapper.selectUsers'.equals(mapper) &amp;&amp; 2 == params[0]"/>
        <data schema="schema3" table="user" script="! 'UserMapper.selectUsers'.equals(mapper) &amp;&amp; 3 == params[0]"/>
        <data schema="schema4" table="user" script="! 'UserMapper.selectUsers'.equals(mapper) &amp;&amp; 4 == params[0]"/>
        <data schema="schema5" table="user" script="params[0] > 0 &amp;&amp; params[1] &lt; 5"/>
    </rule>
```
* 参数绑定需自定义ognl表达式，参数来源为mapper接口入参