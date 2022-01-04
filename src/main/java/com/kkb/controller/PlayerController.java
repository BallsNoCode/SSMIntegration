package com.kkb.controller;

import com.github.pagehelper.PageInfo;
import com.kkb.pojo.Player;
import com.kkb.service.PlayerService;
import com.kkb.vo.QueryPlayerVo;
import com.kkb.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author APPDE
 */
@Controller
@RequestMapping("player")
@ResponseBody
public class PlayerController {

    @Resource
    private PlayerService playerService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResultVO<Player> queryByPage(Integer pageNum, Integer pageSize, QueryPlayerVo vo){
        System.out.println(vo);
        if(pageNum==null || pageNum<=0){
            pageNum=1;
        }
        if(pageSize==null || pageSize<=0){
            pageSize=5;
        }
        PageInfo<Player> playerPageInfo = playerService.queryByPage(pageNum, pageSize, vo);
        return new ResultVO<>(playerPageInfo);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultVO<Player> addPlayer(Player Player){
        int i = playerService.addPlayer(Player);
        if(i==1){
            return new ResultVO<Player>();
        }
        return new ResultVO<>(500,"服务器内部异常，请稍后再试！");
    }
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResultVO<Player> queryById(@PathVariable("id") Integer PlayerId){
        Player player = playerService.queryById(PlayerId);
        return new ResultVO<>(player);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResultVO<Player> updatePlayer(@PathVariable("id") Integer playerId, Player player){
        System.out.println("updatePlayer--Player-------------"+player);
        player.setPlayerId(playerId);
        int i = playerService.updatePlayer(player);
        if(i==1){
            return new ResultVO<Player>();
        }
        return new ResultVO<>(500,"服务器内部异常，请稍后再试！");
    }
    @RequestMapping(value ="{id}",method = RequestMethod.DELETE)
    public ResultVO<Player> deletePlayer(@PathVariable("id") Integer playerId){
        int i = playerService.detectPlayer(playerId);
        if(i==1){
            return new ResultVO<Player>();
        }
        return new ResultVO<>(500,"服务器内部异常，请稍后再试！");
    }
}
