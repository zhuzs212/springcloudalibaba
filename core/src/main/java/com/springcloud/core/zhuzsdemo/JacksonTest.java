package com.springcloud.core.zhuzsdemo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO jackson ObjectMapper 序列化 List 根结点名称
 *
 * @author zhuzs
 * @date 2022/9/20 16:15
 */
@Slf4j
public class JacksonTest {
    public static void main(String[] args) {
        //TODO jackson ObjectMapper 序列化 List 根结点名称
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setAge(20);
        user1.setName("u1");
        User user2 = new User();
        user2.setAge(10);
        user2.setName("u2");
        users.add(user1);
        users.add(user2);

        ObjectNode nodes = JsonNodeFactory.instance.objectNode().putPOJO("Users", users);
        log.info("nodes:{}", nodes.toString());


        Object high = 174.5;
        Double d = Double.parseDouble(String.valueOf(high));
        System.out.println(d);
    }
}

@Data
class User {
    @JsonProperty(value = "AGE")
    private Integer age;

    @JsonProperty(value = "NAME")
    private String name;
}
