package com.heshijia.myblog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heshijia.myblog.mapper.FriendMapper;
import com.heshijia.myblog.pojo.Friend;
import com.heshijia.myblog.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 */

@Service
public class FriendServiceImpl  implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public List<Friend> queryFriendCondition(HashMap<String, Object> map) {

        return friendMapper.selectBlogConditionPage(map);
    }

    @Override
    public Friend queryfriendinfo() {
        Friend friend = friendMapper.selectFriendInfo();
        return friend;
    }

    @Override
    public Friend queryFriendById(long id) {
        Friend friend = friendMapper.selectFriendById(id);
        return friend;
    }

    @Override
    public Boolean updateFriend(Friend friend) {

        try {
            Date date = new Date();
            SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String edittime = str.format(date);
            friend.setEdittime(edittime);
//            if (friend.getPublished() && friendMapper.queryPublished() != null) {
//                friendMapper.updatePublished(friendMapper.queryPublished(), false);
//            }
            friendMapper.updateFriendInfo(friend);
        } catch (Exception e) {
            return false;
        }
        ;


        return true;

    }

    @Override
    public Boolean insertFriend(Friend friend) {
        try {
            Date date = new Date();
            SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createtime = str.format(date);
            friend.setCreatetime(createtime);
            friend.setEdittime(createtime);
            if (friend.getPublished() && friendMapper.queryPublished() != null) {
                friendMapper.updatePublished(friendMapper.queryPublished(), false);
            }
            friendMapper.insertFriendInfo(friend);

        } catch (Exception e) {
//            System.out.println(e);
            return false;
        }
        ;


        return true;
    }

    /**
     * 删除友链*/
    @Override
    public int friendDeleteById(long id) {
        try {
            friendMapper.friendDeleteById(id);
            return 1;
        }catch (Exception e){
            return -1;
        }
    }
}

