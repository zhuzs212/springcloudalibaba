
### 定时任务
    1.基于@Scheduled注解和@EnableScheduling注解的单线程实现；
    - cron 表达式语法： 
     格式： [秒] [分] [小时] [日] [月] [周] [年]
     序号	说明	    是否必填	  允许填写的值	      允许的通配符
      1	     秒	      是	    0-59	            , - * /
      2	     分	      是	    0-59	            , - * /
      3	     小时	  是	    0-23  	            , - * /
      4	     日	      是	    1-31	            , - * ? / L W
      5	     月	      是	    1-12 or JAN-DEC     , - * /
      6	     周	      是	    1-7 or SUN-SAT      , - * ? / L #
      7	     年	      否	  empty 或 1970-2099    , - * /
      
    - fixedDelay
      上一次执行完毕时间点之后多长时间再执行
      
      fixedDelayString
      与 fixedDelay 意思相同，只是使用字符串的形式。唯一不同的是支持占位符：
      在 application.yml 配置文件中添加如下配置：
      time:
        fixedDelay: 5000
      @Scheduled(fixedDelayString = "${time.fixedDelay}")
      
    - fixedRate
      上一次开始执行时间点之后多长时间再执行
      
      fixedRateString
      与 fixedRate 意思相同，只是使用字符串的形式。唯一不同的是支持占位符
      
    - initialDelay
      第一次延迟多长时间后再执行
      initialDelayString
      与 initialDelay 意思相同，只是使用字符串的形式。唯一不同的是支持占位符
      
      
    2.基于线程池的多线程实现
    - 自定义线程池工厂类

### 阻塞队列
    阻塞队列提供了四种操作方法：
    
      方法/处理方式      抛出异常      返回特殊值      一直阻塞      超时退出
      插入方法           add(e)       offer(e)      put(e)       offer(e,time,unit)
      移除方法          remove(e)     poll(e)       take(e)      poll(time,unit)
      检查方法          element()     peek()        不可用        不可用
      
      
    - 抛出异常  : 当队列满时，再向队列中插入元素，则会抛出IllegalStateException异常。
                 当队列空时，再向队列中获取元素，则会抛出NoSuchElementException异常。
                 
    - 返回特殊值: 当队列满时，向队列中添加元素，则返回false，否则返回true。
                 当队列为空时，向队列中获取元素，则返回null，否则返回元素。
    - 一直阻塞  : 当阻塞队列满时，如果生产者向队列中插入元素，则队列会一直阻塞当前线程，直到队列可用或响应中断退出。
                 当阻塞队列为空时，如果消费者线程向阻塞队列中获取数据，则队列会一直阻塞当前线程，直到队列空闲或响应中断退出。
                 
    - 超时退出  : 当队列满时，如果生产线程向队列中添加元素，则队列会阻塞生产线程一段时间，超过指定的时间则退出返回false。
                 当队列为空时，消费线程从队列中移除元素，则队列会阻塞一段时间，如果超过指定时间退出返回null。
    
    1.是一个用数组实现的有界阻塞队列，此队列按照先进先出（FIFO）的原则对元素进行排序。
      支持公平锁和非公平锁。
      注：
      每一个线程在获取锁的时候可能都会排队等待，如果在等待时间上，先获取锁的线程的请求一定先被满足，那么这个锁就是公平的。
      反之，这个锁就是不公平的。公平的获取锁，也就是当前等待时间最长的线程先获取锁


### shiro
    1.认证过滤器：
    anon：无需认证即可访问，游客身份。
    authc：必须认证（登录）才能访问。
    authcBasic：需要通过 httpBasic 认证。
    user：不一定已通过认证，只要是曾经被 Shiro 记住过登录状态的用户就可以正常发起请求，比如 rememberMe。
    
    2.授权过滤器:
    perms：必须拥有对某个资源的访问权限（授权）才能访问。
    role：必须拥有某个角色权限才能访问。
    port：请求的端口必须为指定值才可以访问。
    rest：请求必须是 RESTful，method 为 post、get、delete、put。
    ssl：必须是安全的 URL 请求，协议为 HTTPS。

