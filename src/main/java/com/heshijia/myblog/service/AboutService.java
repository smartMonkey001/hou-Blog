package com.heshijia.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heshijia.myblog.pojo.About;
import com.heshijia.myblog.pojo.Blog;

import java.util.HashMap;
import java.util.List;

public interface AboutService extends IService<About> {

    List<About> queryAboutCondition(HashMap<String, Object> hashMap);

    About queryaboutinfo();

    About queryAboutById(long id);


    Boolean updateAbout(About about);


    Boolean insertAbout(About about);

    int aboutDeleteById(long parseLong);

}
