package com.springcloud.core.zhuzsdemo.aop.jdkproxy;

import com.springcloud.core.zhuzsdemo.aop.UserService;
import com.springcloud.core.zhuzsdemo.aop.UserServiceImpl;
import com.springcloud.core.zhuzsdemo.aop.annotation.UserProxy;
import com.springcloud.core.zhuzsdemo.aop.cglib.CglibProxy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author zhu_zishuang
 * @date 2022/5/18 15:15
 */
@SpringBootTest
@EnableWebMvc
class UserServiceImplTest {
//    @Autowired
//    private UserServiceImpl serviceImpl;

    @Test
    public void test01() {
        ApplicationContext context = new AnnotationConfigApplicationContext(UserProxy.class);
        UserService user = (UserService) context.getBean("userServiceImpl");
//        System.out.println(serviceImpl.add(1,3));
        System.out.println(user.add(1, 3));
    }

    @Test
    public void test02() {
        CglibProxy proxy = new CglibProxy(new UserServiceImpl());
        UserServiceImpl userService = (UserServiceImpl) proxy.getProxy();
        System.out.println(userService.add(1, 4));
    }
    @Test
    public void test03() {
//        /**
//         *     "source": "JS",
//         *     "currency": "01",
//         *     "accountNumber": "02010188000001780"
//         */
//
//        QueryAccountInfoDTO dto = new QueryAccountInfoDTO();
//        dto.setAccountNumber("02010188000001780");
//        dto.setCurrency("01");
//        dto.setSource("JS");
//
//        Result<AccountDetailDTO> result = client.queryAccountInfo(dto);
//        System.out.println("result:" + result);

    }
}
