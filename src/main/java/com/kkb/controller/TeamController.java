package com.kkb.controller;

import com.github.pagehelper.PageInfo;
import com.kkb.pojo.Team;
import com.kkb.service.TeamService;
import com.kkb.vo.QueryTeamVO;
import com.kkb.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 *
 * @author APPDE
 */
@Controller
@RequestMapping("team")
@ResponseBody
public class TeamController {
    @Resource
    private TeamService teamService;

/*    如果实体类中的日期类型需要从页面获取数据，避免NULL转换为Date类型出问题
    解决方案1：@InitBinder
    解决方案2：实体类的日期类型属性上添加注解@DateTimeFormat(pattern = "yyyy-MM-dd")
    @InitBinder
    protected void initDateFormatBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResultVO<Team> queryByPage(Integer pageNum, Integer pageSize, QueryTeamVO vo){
        if(pageNum==null || pageNum<=0){
            pageNum=1;
        }
        if(pageSize==null || pageSize<=0){
            pageSize=5;
        }
        PageInfo<Team> teamPageInfo = teamService.queryByPage(pageNum, pageSize, vo);
        return new ResultVO<>(teamPageInfo);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultVO<Team> addTeam(Team team){
        int i = teamService.addTeam(team);
        if(i==1){
            return new ResultVO<Team>();
        }
        return new ResultVO<>(500,"服务器内部异常，请稍后再试！");
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResultVO<Team> queryById(@PathVariable("id") Integer teamId){
        Team team = teamService.queryById(teamId);
        return new ResultVO<>(team);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResultVO<Team> updateTeam(@PathVariable("id") Integer teamId,Team team){
        System.out.println("updateTeam--team-------------"+team);
        team.setTeamId(teamId);
        int i = teamService.updateTeam(team);
        if(i==1){
            return new ResultVO<Team>();
        }
        return new ResultVO<>(500,"服务器内部异常，请稍后再试！");
    }
    @RequestMapping(value ="{id}",method = RequestMethod.DELETE)
    public ResultVO<Team> deleteTeam(@PathVariable("id") Integer teamId){
        int i = teamService.deleteTeam(teamId);
        if(i==1){
            return new ResultVO<Team>();
        }
        return new ResultVO<>(500,"服务器内部异常，请稍后再试！");
    }
    @RequestMapping(value = "{id}",method = RequestMethod.POST)
    public ResultVO<Team> uploadLogo(@RequestParam("logo")MultipartFile myFile, HttpServletRequest request,
                                     @PathVariable("id") Integer teamId){
        String path=request.getServletContext().getRealPath("/img/uploadFile/");
        String originalFilename = myFile.getOriginalFilename();
        String randomName=UUID.randomUUID().toString().replace("-","");
        int index=originalFilename.lastIndexOf(".");
        String hz=originalFilename.substring(index);
        String logoName=randomName+hz;
        int num=0;
        try {
            myFile.transferTo(new File(path+"/"+logoName));
            System.out.println("上传成功！"+path+"/"+logoName);
            Team team=new Team();
            team.setTeamId(teamId);
            team.setTeamLogo(logoName);
            num=teamService.updateTeam(team);
            if(num==1){
                return new ResultVO<Team>();
            }else{
                return new ResultVO<>(500,"服务器内部异常，请稍后再试！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultVO<>(500,"图片上传出现问题！"+e.getMessage());
        }

    }
}
