package com.heshijia.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heshijia.myblog.pojo.Friend;
import java.util.HashMap;
import java.util.List;

public interface FriendService  {

    List<Friend> queryFriendCondition(HashMap<String, Object> hashMap);

    Friend queryfriendinfo();

    Friend queryFriendById(long id);


    Boolean updateFriend(Friend friend);


    Boolean insertFriend(Friend friend);

    int friendDeleteById(long parseLong);

}
