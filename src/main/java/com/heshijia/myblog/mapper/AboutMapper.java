package com.heshijia.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heshijia.myblog.pojo.About;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface AboutMapper extends BaseMapper<About> {

    List<About> selectBlogConditionPage(HashMap<String, Object> map);



    About selectAboutInfo();

/**
 * 按照id查询关于我信息*/
    About selectAboutById(long id);
/**
 * 修改关于我信息*/
    void updateAboutInfo(About about);


    /**
     * 插入关于我信息
     * */
    void insertAboutInfo(About about);

    /**
     * 查找已发布的关于我的信息
     * */
    Integer queryPublished();

    /**
     * 修改发布状态
     * */
    void updatePublished(@Param("id") Integer id, @Param("status") Boolean status);

    void aboutDeleteById(long id);

}
