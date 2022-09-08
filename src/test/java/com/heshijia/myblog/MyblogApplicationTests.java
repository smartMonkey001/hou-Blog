package com.heshijia.myblog;

import com.heshijia.myblog.pojo.User;
import com.heshijia.myblog.utils.MD5Utils;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MyblogApplicationTests {



    @Test
    public void fun (){
        User user=new User();
        User user1=new User();
        User user2=new User();
        user.setAvatar("0000000");
        user1.setAvatar("1111111");
        user2.setAvatar("2222222");
        user.setUsername("sb");
        user.setUsername("sb1");
        user.setUsername("sb2");
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user1);
        list.add(user2);
        for (User user3 :list) {
            user3.setUsername("hhhhhh");
        }
        System.out.println(list);
    }
}
