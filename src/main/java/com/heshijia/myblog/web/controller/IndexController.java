package com.heshijia.myblog.web.controller;

import com.heshijia.myblog.mapper.TagMapper;
import com.heshijia.myblog.pojo.About;
import com.heshijia.myblog.pojo.Blog;
import com.heshijia.myblog.pojo.Friend;
import com.heshijia.myblog.pojo.Tag;
import com.heshijia.myblog.service.AboutService;
import com.heshijia.myblog.service.impl.AboutServiceImpl;
import com.heshijia.myblog.service.impl.BlogServiceImpl;
import com.heshijia.myblog.service.impl.TagServiceImpl;
import com.heshijia.myblog.utils.MarkDownUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    TagServiceImpl tagServiceimp;

    @Autowired
    AboutServiceImpl aboutService;

    /**
     * 博客主页
     * @return
     */
    @RequestMapping("/toIndex")
    public  String toIndex(){
        return "blog/index";
    }
    /**
     * 博客标签页
     * @return
     */
    @RequestMapping("/toTags")
    public  String toTags(Model model,@RequestParam(defaultValue ="0")  String TagId){
        List<Tag> tags = tagServiceimp.queryTagListAndCount();
        Integer  TagsCount= tagServiceimp.queryTagCount();
        model.addAttribute("TagsCount",TagsCount);
        model.addAttribute("Id",Long.parseLong(TagId));
        model.addAttribute("tags",tags);
        return "blog/tags";
    }
    /**
     * 博客详情页
     * @return
     */
    @RequestMapping("/toBlog/{id}")
    public  String toBlog(@PathVariable String id,Model model) {
        Blog blog = blogService.queryBlogDetailsById(Long.parseLong(id));
        model.addAttribute("blog",blog);
        return "blog/blog";
    }
    /**
     * 博客归档页
     * @return
     */
    @RequestMapping("/toArchive")
    public  String toArchive(Model model){
        Map<String, List<Blog>> ArchiveMap = blogService.queryBlogArchive( );
        model.addAttribute("ArchiveMap",ArchiveMap);
        return "blog/archive";
    }

    /**
     * 博客关于我页
     * @return
     */
    @RequestMapping("/toAbout")
    public  String toAbout(Model model){
//        Blog  AboutBlog = blogService.queryBlogDetailsByType("2");
//        Blog blog = blogService.queryBlogDetailsById(AboutBlog.getId());

        About about= aboutService.queryaboutinfo();
        String content = about.getContent();
        about.setContent(MarkDownUtils.markdownToHtml(content));
        model.addAttribute("blog",about);
        return "blog/about";
    }
    /**
     * 博客友联页
     * @return
     */
    @RequestMapping("/toLink")
    public  String toLink(Model model){
        List<Friend> friends = blogService.queryLinkByPublished("1");
//        List<Friend> friendsMk = new ArrayList<>();
        for (Friend friend :friends) {
            String content = friend.getContent();
            friend.setContent(MarkDownUtils.markdownToHtml(content));
//            friendsMk.add(friend);
        }


        model.addAttribute("blog",friends);

        return "blog/link";
    }
    /**
     * 登录页
     * @return
     */
    @RequestMapping("/toLogin")
    public  String toLogin(){
        return "admin/login";
    }

    /**
     * 未授权页面返回
     * @return
     */
    @RequestMapping("/toUnauthorized")
    public  String toUnauthorized(){
        return "error/403";
    }


}
