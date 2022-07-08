#全局处理
##1.全局异常处理

##2.日志记录

##3.接口返回值统一包装

##4.

################################### 规则——非空校验  #########################################################################
##1.获取安全加密的随机数
    SecureRandom sr = new SecureRandom();

##2.非空/null检查
    a)字符串为空或null检查
        String message = null;
        StringUtils.isEmpty(message);
    b)集合为空或null检查
        List<String> list = Lists.newArrayListWithCapacity(10);
        CollectionUtils.isEmpty(list);
        Set<String> set = Sets.newHashSetWithExpectedSize(10);
        CollectionUtils.isEmpty(set);
        Map<String, String> map = Maps.newHashMapWithExpectedSize(10);
        CollectionUtils.isEmpty(map);
    c)数组为空或null检查
        String[] strings = {};
        ObjectUtils.isEmpty(strings);
    d)非字符串、集合、数组对象null检查
        User user = null;
        ObjectUtils.isEmpty(user);
        Integer num = null;
        ObjectUtils.isEmpty(num);

##3.集合初始化
    集合初始化指定大小，最大程度降低容器自动扩容

    a)List初始化
        List<String> list = Lists.newArrayListWithCapacity(10); 优先
        List<String> list = Lists.newArrayList();
        List<String> list = Lists.newLinkedList();
    b)Set初始化
        Set<String> set = Sets.newHashSetWithExpectedSize(10); 优先
        Set<String> set = Sets.newHashSet();
    c)Map初始化
        Map<String, String> map = Maps.newHashMapWithExpectedSize(10); 优先
        Map<String, String> map = Maps.newHashMap();

##4.所有的接口出入参需为包装对象（私有方法除外） 

