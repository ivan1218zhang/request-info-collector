# api-info-collector - Java轮子<br> 适用于收集网络请求参数并统计的场景
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Essentials
----------

* [STAR](https://github.com/ivan1218zhang/request-info-collector#star)
* [Quick start](https://github.com/ivan1218zhang/request-info-collector#quick-start)
* [Releases](https://github.com/ivan1218zhang/request-info-collector/releases)

## STAR

### 情境(situation)

网站数据的统计在这个时代是具有价值的，可以用来图形化展示或者是做用户画像，关于数据统计的需求也相应增加，所以我想为这些场景写个轮子。

有那么一种场景，假如我们的项目正常的运行着，这时候想要加一个功能：用户只要访问了接口，就把用户id记下来，之后把用户访问的接口分布图形化地展示出来。这个时候用我写的轮子就能轻松的在原有的项目上实现这个功能，当然这个轮子是针对于使用SpringBoot开发的项目。

还有一种场景是用于数据中台的，我们把业务项目的一些数据收集起来，共享出去，原有的业务项目又能产生新的价值。

### 任务(task)

让用SpringBoot开发后端的程序员，更关注于自己想要收集的数据，不用去考虑怎么存数据，怎么统计数据的事。轮子会帮他们做好存数据和统计数据的功能的。

### 行动(action)
这是我第一次写框架类的项目，所以在写的时候会研究一些框架他们是怎么做的。

项目由两部分组成，数据收集和数据统计，同时项目还提供了前端页面，用于数据展示和提供统计接口的api文档。<br>
数据的存储是依赖Redis的，因为数据收集的操作多，如果每个接口都去访问MySql这样的数据库的话，业务的效率就比较低，访问数据存取发生在内存的Redis则更快，而且存储的数据格式不固定，适合非关系型数据库。

#### 数据收集
在有`@RestController`注解的类中，在需要统计的方法上打上自定义注解`@Collector`，利用AOP对打上注解的方法添加收集数据的功能，同时`@Collector`中还有一些属性，默认都为false，比如ip，如果ip=true,意思就是会收集发送请求的客户端的ip地址。

那么怎么知道哪些数据要收集呢？分为两种情况。
1. 简单的收集<br>
比如方法中的某个参数，直接收集他`toString()`的值。<br>
注解的属性`fieldName`用于指定收集数据的字段名，可以用来跨接口统计，比如获取某个字段名的接口访问次数的分布。
```java
public @interface Collected {
    String fieldName() default "";
}
```
```java
@Collector
@RequestMapping("/test")
String test(@Collected(fieldName = "name") String name){
    return "test";
}
```
2. 复杂的收集<br>
有时候数据的收集不只是简单的把参数存到数据库就行了，或者是接口方法没有使用一个一个简单的参数，而是一个RequestBody的对象，那么就需要这个对象实现了轮子定义的接口`CollectedVO`,这个接口定义了一个方法，返回一个Map，这就是要收集的键值对。
```java
public interface CollectedVO {
    /**
     * 获取要收集的数据 让开发者自己去实现
     * @return
     */
    Map<String,String> getCollectedMap();
}
```
```java
@Collector(ip = true, isSuccess = true)
@RequestMapping("/test")
String test(TestReqVO reqVO){
    return "test";
}
```
```java
public class TestReqVO implements CollectedVO{
    private String name;
    private String id;

    @Override
    public Map<String, String> getCollectedMap() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("oneUserId",id);
        hashMap.put("myValue",name+"开发者自定义处理"+id)
        return hashMap;
    }
}
```
数据收集完以后还要存储到Redis中，这些则在`CollectorAspect`这个类中实现，详细逻辑可以去看源码，比较简单不一一赘述。

#### 数据统计
数据统计是，轮子自定义了Controller，用户可以通过这些Controller的方法获得统计的数据，目前设计只提供了一些常用的统计：根据单个或者多个字段统计接口分布、某个接口下的某个字段分布、根据单个或者多个字段统计某个字段的分布。

用户可以访问前端页面，前端页面有关于Controller的一些文档和测试请求，让用户对这个轮子的Controller有更好的理解，也是让这个轮子作为数据中台的作用有更好的发挥。

可以查看线上demo对数据统计有一个直观的感受。

### 结果(result)
<a href="http://demo.nobugnolife.com/request-info-collector/">线上demo</a>


目前的话，只是把自己脑子里所设想的一个东西给做出来了，其实还有很多的功能没有完善好，比如存储选择的数据库只能是Redis，也许可以把这个功能抽象出来，让他支持更多的数据库。统计的功能做的也比较简陋，只是次数的统计，还需要更巧妙的设计让他能有更多的统计方法，比如平均值、最大最小值、占比等等。

## Quick start

### 开启服务
**@EnableRequestInfoCollector**

使用轮子必须在SpringBoot启动类中使用这个注解

```java
@SpringBootApplication
@EnableRequestInfoCollector
public class TestBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestBootApplication.class,args);
    }
}
```

### Redis配置
```java
@Configuration
public class TestConfig {
    @Bean
    public RedisClient test(){
        RedisConfig redisConfig=new RedisConfig();
        redisConfig.setAddress("localhost");
        redisConfig.setPort(6379);
        redisConfig.setPassword("");
        return RedisClient.create(redisConfig);
    }
}
```

### 前端相关的配置
不是必须配置，轮子提供默认的bean，相关配置默认开启（属性为true）
```java
@Configuration
public class TestConfig {
    @Bean
    public CacheConfig cacheConfig(){
        CacheConfig cacheConfig=new CacheConfig();
        cacheConfig.setDefaultUrls(true);
        cacheConfig.setDefaultFields(false);
        cacheConfig.setDefaultFieldValues(true);
        return cacheConfig;
    }
}
```

### 收集数据
**@Collector**

在想要收集数据的接口方法上打上这个注解，这个注解中提供了一些属性，`ip`、`isSuccess`、`startTime`、`endTime`，是轮子提供的一些功能，可以选择是否开启（设置为true），开启就会把ip地址、接口运行是否成功、开始时间、结束时间统计。

```java
@Collector(ip = true, isSuccess = true)
@RequestMapping("/test")
String test(){
    return "test";
}
```

**@Collected**

用于收集简单数据。

```java
@Collector(ip = true, isSuccess = true)
@RequestMapping("/test")
String test(){
    return "test";
}
```

### 更灵活的收集
```java
@Collector(ip = true, isSuccess = true)
@RequestMapping("/test")
String test(TestReqVO reqVO){
    return "test";
}
```
```java
public class TestReqVO implements CollectedVO{
    private String name;
    private String id;

    @Override
    public Map<String, String> getCollectedMap() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("oneUserId",id);
        hashMap.put("myValue",name+"开发者自定义处理"+id)
        return hashMap;
    }
}
```

### 访问前端页面
/request-info-collector.html#/index