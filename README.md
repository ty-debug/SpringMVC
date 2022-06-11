# SpringMVC

MVC：模型（dao，service） 视图 （jsp） 控制器（servlet）

## 1、常规依赖

```xml

<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.2.0.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.5</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>jsp-api</artifactId>
        <version>2.2</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
</dependencies>
```

## 2、文件导出格式异常的一般处理方式

```xml

<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
        </resource>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
        </resource>
    </resources>
</build>
```

## SpringMVC执行原理

## **3、简要分析执行流程**

1、DispatcherServlet表示前置控制器，是整个SpringMVC的控制中心。用户发出请求，DispatcherServlet接收请求并拦截请求。

我们假设请求的url为 : http://localhost:8080/SpringMVC/hello

如上url拆分成三部分：

http://localhost:8080服务器域名

SpringMVC部署在服务器上的web站点

hello表示控制器

通过分析，如上url表示为：请求位于服务器localhost:8080上的SpringMVC站点的hello控制器。

2、HandlerMapping为处理器映射。DispatcherServlet调用HandlerMapping,HandlerMapping根据请求url查找Handler。

3、HandlerExecution表示具体的Handler,其主要作用是根据url查找控制器，如上url被查找控制器为：hello。

4、HandlerExecution将解析后的信息传递给DispatcherServlet,如解析控制器映射等。

5、HandlerAdapter表示处理器适配器，其按照特定的规则去执行Handler。

6、Handler让具体的Controller执行。

7、Controller将具体的执行信息返回给HandlerAdapter,如ModelAndView。

8、HandlerAdapter将视图逻辑名或模型传递给DispatcherServlet。

9、DispatcherServlet调用视图解析器(ViewResolver)来解析HandlerAdapter传递的逻辑视图名。

10、视图解析器将解析的逻辑视图名传给DispatcherServlet。

11、DispatcherServlet根据视图解析器解析的视图结果，调用具体的视图。

12、最终视图呈现给用户。

每次以Maven方式创建SpringMVC项目时都需要注意修改Artifacts里的lib，新建lib包 点+号导入

![1650289092662](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1650289092662.png)

## 4、复用配置代码

#### web.xml

```xml
    <!--注册DispatcherServlet-->
<servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!--关联一个SpringMvc的一个配置文件：【servlet-name】-servlet.xml-->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc-servlet.xml</param-value>
    </init-param>
    <!--启动级别1-->
    <load-on-startup>1</load-on-startup>
</servlet>

        <!--/ 匹配所有请求：（不包括.jsp）-->
        <!--/* 匹配所有请求：（包括.jsp）-->
<servlet-mapping>
<servlet-name>springmvc</servlet-name>
<url-pattern>/</url-pattern>
</servlet-mapping>


        <!--SpringMVC乱码过滤-->
<filter>
<filter-name>encoding</filter-name>
<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
<init-param>
    <param-name>encoding</param-name>
    <param-value>utf-8</param-value>
</init-param>
</filter>
<filter-mapping>
<filter-name>encoding</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping>
```

#### 注解开发时的springmvc-servlet.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--自动扫描包，让指定包下的注解生效-->
    <context:component-scan base-package="com.zjh.controller"/>
    <!--让SpringMVC不处理静态资源-->
    <mvc:default-servlet-handler/>

    <!--支持mvc注解驱动
        在spring中一般采用@RequestMapping注解来完成映射关系
        要想使@RequestMapping生效
        需要向上下文中注册DefaultAnnotationHandlerMapping
        和一个AnnotationMethodHandlerAdapter实例
        这两个实例分别在类级别和方法级别处理。
        而annotation-driver配置帮助我们自动完成以上实例的注入-->
    <!--解决json乱码问题-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!--视图解析器：DispatcherServlet给他的ModelAndView-->
    <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!--前缀-->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!--后缀-->
        <property name="suffix" value=".jsp"/>
    </bean>

</beans>
```

#### HelloController.java

测试代码

```java

@RestController //这样写会使得返回的hello变为字符串
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("msg", "Hello SpringMVC Annotation");
//        返回的结果和配置文件里拼接而成 hello.jsp
        return "hello";
    }
}
```

Restful风格一般写法记录

```java
//    普通方式    ：http://localhost:8080/springmvc_04_controler_war_exploded/add?a=1&b=2
//    Restful   ：http://localhost:8080/springmvc_04_controler_war_exploded/add/a/b

//    @RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.GET)
@GetMapping("/add/{a}/{b}")
public String test(@PathVariable int a,@PathVariable int b,Model model){

        int res=a+b;

        model.addAttribute("msg",res);
        return"test";
        }
```

关于地址栏传参

```java
    @RequestMapping("/user/t1")
public String test1(@RequestParam("username") String name,Model model){

        System.out.println("前端参数："+name);

        model.addAttribute("msg",name);

        return"test";
        }

//    Restful
@RequestMapping("/user/t2/{name}")
public String test2(@PathVariable String name,Model model){

        System.out.println("前端参数："+name);

        model.addAttribute("msg",name);

        return"test";
        }

    /*
        接收前端用户传递的参数，判断参数的名字，假设名字直接在方法上，可以直接试用
        假设传递的是一个对象，匹配对象中的字段名，名字一致则ok 不一致则为null
     */

@GetMapping("/user/t3")
public String test3(User user){

        System.out.println("前端参数："+user);

        return"test";
        }
```



