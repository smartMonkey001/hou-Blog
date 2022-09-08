package com.heshijia.myblog.web.controller.backstage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heshijia.myblog.pojo.About;
import com.heshijia.myblog.pojo.Blog;
import com.heshijia.myblog.pojo.Msg;
import com.heshijia.myblog.pojo.Tag;
import com.heshijia.myblog.service.AboutService;
import com.heshijia.myblog.service.impl.AboutServiceImpl;
import com.heshijia.myblog.utils.MsgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private AboutService service;
    /**
     * 跳转关于我管理页面
     * @return
     */
    @RequestMapping ("/toAbout")
    public String toAbout(){


        return "admin/about";
    }

    /**
     * 搜索，查询，查看当前所有 关于我 信息*/
    @GetMapping("/abouts")
    @ResponseBody
    public Object queryBlogs(String published, String title,
                             @RequestParam(defaultValue = "1")String pageNum
                            ,@RequestParam(defaultValue = "5") String pageSize){


        Msg msg = new Msg();
        try{
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("publishedConditions",published);
            hashMap.put("titleConditions",title);
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
            List<About> about = service.queryAboutCondition(hashMap);
            PageInfo<About> pageInfo = new PageInfo<>(about);
            HashMap<String, Object> MsghashMap = msg.getHashMap( );
            MsghashMap.put("pageList",pageInfo);
            return  msg;
        }catch (Exception e){
            e.printStackTrace();
            msg.setCode(MsgCode.FAIL_CODE);
            msg.setMessage("系统太忙了,请稍后再查");
            return msg;
        }
    }




    /**
     * 跳转关于我修改页面
     * @return
     */
    @RequestMapping ("/toEditAbout")
    public String toEditBlogs(String id,Model model){
        About about = service.queryAboutById(Long.parseLong(id));

        model.addAttribute("blogs",about);
        return "admin/about-edit";
    }




    /**
     * 修改关于我信息
     */
    @PutMapping("/Abouts")
    public String updateAbouts(About about,RedirectAttributes redirectAttributes){
        Boolean flag = service.updateAbout(about);

        if (flag){
            redirectAttributes.addFlashAttribute("msg","添加成功");
        }else {
            redirectAttributes.addFlashAttribute("msg","添加失败");
        }

        return "redirect:/about/toAbout";

    }

    /**
     * 添加关于我信息
     */

    @PostMapping("/editAbouts")
    public String saveAbouts(About about,RedirectAttributes redirectAttributes){


        Boolean flag = service.insertAbout(about);

        if (flag){
            redirectAttributes.addFlashAttribute("msg","添加成功");
        }else {
            redirectAttributes.addFlashAttribute("msg","添加失败");
        }

        return "redirect:/about/toAbout";

    }

    /**
     * 删除关于我信息
     */

    @DeleteMapping("/delete")
    @ResponseBody
    public Object delete(String id){
        Msg msg = new Msg( );
        System.out.println(id );
        try{
            int i = service.aboutDeleteById(Long.parseLong(id));
            if (i>0){
                msg.setCode(MsgCode.SUCCESS_CODE);
            }else {
                msg.setCode(MsgCode.FAIL_CODE);
                msg.setMessage("删除博客信息失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.setCode(MsgCode.FAIL_CODE);
            msg.setMessage("系统繁忙,删除失败请稍后再试");
            return  msg;
        }
        return msg;
    }
    /**
     * 跳转关于我发布页面
     * @return
     */
    @RequestMapping ("/toAboutsInput")
    public String toBlogsInput(Model model){

        return "admin/about-input";
    }
}
