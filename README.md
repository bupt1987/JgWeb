JgWeb
=====

JgFramework的例子，需要导入JgFramework才能运行
JgFramework 见：https://github.com/bupt1987/JgFramework

注意事项
======
~~~
1、开发是在Eclipse下开发的,需要maven支持，jdk在1.7以上
2、启动使用tomcat或者直接运行com.zhaidaosi.game.serve.BootStart 中的main方法。
3、如果是tomcat的访问http://127.0.0.1/JgWeb，如果是运行main方法的，用浏览器打开src/main/webapp/index.html即可
4、注意新建数据库，sql在docs目录下，还要注意数据库用户名密码，在src/main/java/jdbc.properties
~~~

配置文件
======
~~~
见src/main/java/下
1、spring的配置文件：applicationContext.xml和services.xml
2、框架配置文件：jgframework.properties，默认配置见JgFramework框架中的default_jgframework.properties
3、数据库配置：jdbc.properties
~~~

