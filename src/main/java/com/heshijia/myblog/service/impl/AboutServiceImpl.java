package com.heshijia.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heshijia.myblog.mapper.AboutMapper;
import com.heshijia.myblog.mapper.BlogMapper;
import com.heshijia.myblog.pojo.About;
import com.heshijia.myblog.pojo.Blog;
import com.heshijia.myblog.service.AboutService;
import com.heshijia.myblog.utils.MarkDownUtils;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 */

@Service
public class AboutServiceImpl extends ServiceImpl<AboutMapper, About> implements AboutService {

    @Autowired
private AboutMapper aboutMapper;

    @Override
    public List<About> queryAboutCondition(HashMap<String, Object> map) {

        return aboutMapper.selectBlogConditionPage(map);
    }

    @Override
    public About queryaboutinfo() {
       About about =  aboutMapper.selectAboutInfo();
       return about;
    }

    @Override
    public About queryAboutById(long id) {
       About about= aboutMapper.selectAboutById(id);

        return about;
    }

    @Override
    public Boolean updateAbout(About about) {

        try {
            Date date = new Date();
            SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String edittime = str.format(date);
            about.setEdittime(edittime);
            if (about.getPublished() && aboutMapper.queryPublished()!=null){
                aboutMapper.updatePublished(aboutMapper.queryPublished(),false);
            }
            aboutMapper.updateAboutInfo(about);
        }catch (Exception e){
            return false;
        };


        return true;

    }

    @Override
    public Boolean insertAbout(About about) {
        try {
            Date date = new Date();
            SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createtime = str.format(date);
            about.setCreatetime(createtime);
            about.setEdittime(createtime);
            if (about.getPublished() && aboutMapper.queryPublished()!=null){
                    aboutMapper.updatePublished(aboutMapper.queryPublished(),false);
            }
            aboutMapper.insertAboutInfo(about);

        }catch (Exception e){
            System.out.println(e);
            return false;
        };


        return true;
    }

    @Override
    public int aboutDeleteById(long id) {
        try {
            aboutMapper.aboutDeleteById(id);
            return 1;
        }catch (Exception e){

            return -1;
        }


    }
}
