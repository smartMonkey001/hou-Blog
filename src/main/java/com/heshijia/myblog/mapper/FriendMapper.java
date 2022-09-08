package com.heshijia.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heshijia.myblog.pojo.About;
import com.heshijia.myblog.pojo.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface FriendMapper extends BaseMapper<About> {

    List<Friend> selectBlogConditionPage(HashMap<String, Object> map);



    Friend selectFriendInfo();

/**
 * 按照id查询友链信息*/
Friend selectFriendById(long id);
/**
 * 修改友链信息*/
    void updateFriendInfo(Friend friend);


    /**
     * 插入友链信息
     * */
    void insertFriendInfo(Friend friend);

    /**
     * 查找已发布的友链的信息
     * */
    Integer queryPublished();

    /**
     * 修改发布状态
     * */
    void updatePublished(@Param("id") Integer id, @Param("status") Boolean status);

    /**
     * 删除友链
     * */
    void friendDeleteById(long id);

}
