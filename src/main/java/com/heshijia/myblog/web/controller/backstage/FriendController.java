package com.heshijia.myblog.web.controller.backstage;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heshijia.myblog.pojo.About;
import com.heshijia.myblog.pojo.Friend;
import com.heshijia.myblog.pojo.Msg;
import com.heshijia.myblog.service.AboutService;
import com.heshijia.myblog.service.FriendService;
import com.heshijia.myblog.utils.MsgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService service;
    /**
     * 跳转友链页面
     * @return
     */
    @RequestMapping ("/toFriend")
    public String toFriend(){


        return "admin/friend";
    }

    /**
     * 搜索，查询，查看当前所有 友链  信息*/
    @GetMapping("/friends")
    @ResponseBody
    public Object queryFriends(String published, String title,
                             @RequestParam(defaultValue = "1")String pageNum
                            ,@RequestParam(defaultValue = "5") String pageSize){


        Msg msg = new Msg();
        try{
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("publishedConditions",published);
            hashMap.put("titleConditions",title);
            PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
            List<Friend> friend = service.queryFriendCondition(hashMap);
            PageInfo<Friend> pageInfo = new PageInfo<>(friend);
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
     * 跳转友链修改页面
     * @return
     */
    @RequestMapping ("/toEditFriend")
    public String toEditFriends(String id,Model model){
        Friend friend = service.queryFriendById(Long.parseLong(id));

        model.addAttribute("blogs",friend);
        return "admin/friend-edit";
    }




    /**
     * 修改友链信息
     */
    @PutMapping("/Friends")
    public String updateFriends(Friend friend,RedirectAttributes redirectAttributes){
        Boolean flag = service.updateFriend(friend);

        if (flag){
            redirectAttributes.addFlashAttribute("msg","添加成功");
        }else {
            redirectAttributes.addFlashAttribute("msg","添加失败");
        }

        return "redirect:/friend/toFriend";

    }

    /**
     * 添加友链信息
     */

    @PostMapping("/editFriends")
    public String saveFriends(Friend friend, RedirectAttributes redirectAttributes){


        Boolean flag = service.insertFriend(friend);

        if (flag){
            redirectAttributes.addFlashAttribute("msg","添加成功");
        }else {
            redirectAttributes.addFlashAttribute("msg","添加失败");
        }

        return "redirect:/friend/toFriend";

    }


    /**
     * 删除友链信息
     */

    @DeleteMapping("/delete")
    @ResponseBody
    public Object delete(String id){
        Msg msg = new Msg( );
        System.out.println(id );
        try{
            int i = service.friendDeleteById(Long.parseLong(id));
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
     * 跳转友链发布页面
     * @return
     */
    @RequestMapping ("/toFriendsInput")
    public String toBlogsInput(){

        return "admin/friend-input";
    }
}
