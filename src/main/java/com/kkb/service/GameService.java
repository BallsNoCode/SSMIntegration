package com.kkb.service;

import com.kkb.mapper.GameMapper;
import com.kkb.mapper.TeamMapper;
import com.kkb.pojo.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: GameService
 * 赛程管理的业务逻辑层
 * @author wanglina
 * @version 1.0
 */
@Service
public class GameService {
    @Resource
    private GameMapper gameMapper;
    @Resource
    private TeamMapper teamMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Game>  queryAll(){
        List<Game> games = gameMapper.selectByExample(null);
        for (Game game : games) {
            game.setHomeTeam(teamMapper.selectByPrimaryKey(game.getHomeTeamId()));//根据主队id查询到的主队信息赋值给当前的比赛
            game.setVisitingTeam(teamMapper.selectByPrimaryKey(game.getVisitingTeamId()));
        }
        return  games;
    }
}
