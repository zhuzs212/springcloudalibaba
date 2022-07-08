package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.RedisError;
import exception.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis 操作工具类
 *
 * @author zhuzishuang
 * @date 2022/5/17
 */
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ObjectMapper mapper;

    /**
     * 设置过期时间
     *
     * @param key   数据key
     * @param time 数据value
     * @throws RedisException
     */
    public void expire(final String key, final int time)
            throws RedisException {
        if (ObjectUtils.isEmpty(key) || time < 0) {
            log.error("入参为空,key={}, value={}", key, time);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 功能描述: 设置过期时间
     *
     * @param key
     * @param time
     * @param timeUnit
     * @return void
     * @author houc
     * @date 2019/10/11 15:46
     */
    public void expire(final String key, final int time, TimeUnit timeUnit)
            throws RedisException {
        if (ObjectUtils.isEmpty(key) || time < 0) {
            log.error("入参为空,key={}, value={}", key, time);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            redisTemplate.expire(key, time, timeUnit);
        } catch (Exception e) {
            log.error("REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 功能描述: 设置过期时间
     *
     * @param key
     * @param expireDate
     * @return void
     * @author houc
     * @date 2019/10/14 14:01
     */
    public void expire(final String key, Date expireDate)
            throws RedisException {
        if (ObjectUtils.isEmpty(key) || expireDate == null) {
            log.error("入参为空,key={}, expireDate={}", key, expireDate);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            redisTemplate.expireAt(key, expireDate);
        } catch (Exception e) {
            log.error("REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 获取过期时间
     * @param key
     * @return
     * @throws RedisException
     */
    public Long getExpire(final String key) throws RedisException {
        if (ObjectUtils.isEmpty(key)) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            return redisTemplate.getExpire(key);
        } catch (Exception e) {
            log.error("REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 向REDIS里面添加String类型的数据
     *
     * @param key   数据key
     * @param value 数据value
     * @throws RedisException
     */
    public void set(final String key, final Object value)
            throws RedisException {
        if (ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(value)) {
            log.error("入参为空,key={}, value={}", key, value);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            log.error("REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 判断redis中是否存在指定的key
     *
     * @param key
     * @return
     * @throws RedisException
     */
    public boolean hasKey(final String key)
            throws RedisException {
        if (ObjectUtils.isEmpty(key)) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("读取REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 从REDIS中读取String类型数据
     *
     * @param key 数据key
     * @throws RedisException
     */
    public <T> T get(final String key, final Class<T> clazz)
            throws RedisException {
        if (ObjectUtils.isEmpty(key)) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            Object obj = redisTemplate.opsForValue().get(key);
            return mapper.readValue(mapper.writeValueAsString(obj), clazz);
        } catch (Exception e) {
            log.error("读取REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    public <T> List<T> gets(final String key, final Class<T> clazz)
            throws RedisException {
        if (ObjectUtils.isEmpty(key)) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            Object obj = redisTemplate.opsForValue().get(key);
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            return mapper.readValue(mapper.writeValueAsString(obj), javaType);
        } catch (Exception e) {
            log.error("读取REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }


    /**
     * 删除传入的keys下的内容，返回删除成功的key的个数
     *
     * @param keys 要删除的key
     * @return
     * @throws RedisException
     */
    public long del(final String... keys)
            throws RedisException {
        if (ObjectUtils.isEmpty(keys) || Arrays.stream(keys).anyMatch(n -> !StringUtils.hasText(n))) {
            try {
                log.error("入参为空,keys={}", mapper.writeValueAsString(keys));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        long result = 0;
        try {
            result = redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(keys));
        } catch (Exception e) {
            log.error("读取REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
        return result;
    }

    /**
     * 向REDIS里面添加String类型的数据
     *
     * @param key     类似存储的命名空间
     * @param hashkey 数据key
     * @param value   数据value
     * @throws RedisException
     */
    public void hset(final String key, final String hashkey, final Object value)
            throws RedisException {
        // 允许value值为空
        if (ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(hashkey)) {
            log.error("入参为空,key={},field={},value={}", key, hashkey, value);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            redisTemplate.opsForHash().put(key, hashkey, value);
        } catch (Exception e) {
            log.error("REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 从REDIS中读取String类型数据
     *
     * @param key     类似存储的命名空间
     * @param hashKey 数据key
     * @throws RedisException
     */
    public <T> T hget(final String key, final String hashKey, final Class<T> clazz)
            throws RedisException {
        if (ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(hashKey)) {
            log.error("入参为空,key={},field={}", key, hashKey);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            Object obj = redisTemplate.opsForHash().get(key, hashKey);
            return mapper.readValue(mapper.writeValueAsString(obj), clazz);
        } catch (Exception e) {
            log.error("读取REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 从REDIS中读取String类型数据 集合类型
     *
     * @param key     类似存储的命名空间
     * @param hashKey 数据key
     * @throws RedisException
     */
    public <T> List<T> hgets(final String key, final String hashKey, final Class<T> clazz)
            throws RedisException {
        if (ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(hashKey)) {
            log.error("入参为空,key={},field={}", key, hashKey);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            Object obj = redisTemplate.opsForHash().get(key, hashKey);
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            return mapper.readValue(mapper.writeValueAsString(obj), javaType);
        } catch (Exception e) {
            log.error("读取REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 从REDIS中读取keys
     *
     * @param key     类似存储的命名空间
     * @throws RedisException
     */
    public Map<Object, Object> hentries(final String key)
            throws RedisException {
        if (ObjectUtils.isEmpty(key)) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            log.error("读取REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 从REDIS中读取keys
     *
     * @param key     类似存储的命名空间
     * @throws RedisException
     */
    public List<Object> hmget(final String key, Collection<Object> hashKeys)
            throws RedisException {
        if (ObjectUtils.isEmpty(key)) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            return redisTemplate.opsForHash().multiGet(key, hashKeys);
        } catch (Exception e) {
            log.error("读取REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 删除Hashes中的key-field
     *
     * @param key
     * @param hashKey
     * @return
     * @throws RedisException
     */
    public Long hdel(final String key, final String hashKey)
            throws RedisException {
        if (ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(hashKey)) {
            log.error("入参为空,key={},field={}", key, hashKey);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            return redisTemplate.opsForHash().delete(key, hashKey);
        } catch (Exception e) {
            log.error("删除REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 判断Hashes中是否存在key-field
     *
     * @param key
     * @param hashKey
     * @return
     * @throws RedisException
     */
    public boolean hexists(final String key, final String hashKey)
            throws RedisException {
        if (ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(hashKey)) {
            log.error("入参为空,key={},field={}", key, hashKey);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            return redisTemplate.opsForHash().hasKey(key, hashKey);
        } catch (Exception e) {
            log.error("查询REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 给某个key生成增序值 固定 + 1
     * @param key 生成的值绑定的key
     * @return 返回生成后的值
     */
    public long increment(String key) {
        if (ObjectUtils.isEmpty(key) ) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
            return counter.incrementAndGet();
        } catch (Exception e) {
            log.error("查询REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 给某个key生成增序值 固定 + 1
     * 值到某个时间节点重置
     * @param key 生成的值绑定的key
     * @param expireTime 过期时间
     * @return 返回生成后的值
     */
    public long increment(String key, Date expireTime) {
        if (ObjectUtils.isEmpty(key) ||  ObjectUtils.isEmpty(expireTime)) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
            counter.expireAt(expireTime);
            return counter.incrementAndGet();
        } catch (Exception e) {
            log.error("查询REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 给某个key生成增序值
     * @param key 生成的值绑定的key
     * @param steps 步进数
     * @return 返回生成后的值
     */
    public long increment(String key, int steps) {
        if (ObjectUtils.isEmpty(key) || 0 == steps) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
            return counter.addAndGet(steps);
        } catch (Exception e) {
            log.error("查询REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 给某个key生成增序值
     * 值到某个时间节点重置
     * @param key 生成的值绑定的key
     * @param steps 步进数
     * @param expireTime 过期时间
     * @return 返回生成后的值
     */
    public long increment(String key, int steps, Date expireTime) {
        if (ObjectUtils.isEmpty(key) || 0 == steps) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
            counter.expireAt(expireTime);
            return counter.addAndGet(steps);
        } catch (Exception e) {
            log.error("查询REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }

    /**
     * 给某个key生成增序值 固定 - 1
     * @param key 生成的值绑定的key
     * @return 返回生成后的值
     */
    public long decrement(String key) {
        if (ObjectUtils.isEmpty(key)) {
            log.error("入参为空,key={}", key);
            throw new RedisException(RedisError.REDIS_PARAM_EXCEPTION);
        }
        try {
            RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
            return counter.decrementAndGet();
        } catch (Exception e) {
            log.error("查询REDIS数据失败，连接异常，请检查相关配置。错误：{}", e.getMessage());
            throw new RedisException(RedisError.REDIS_CONN_EXCEPTION);
        }
    }
}
