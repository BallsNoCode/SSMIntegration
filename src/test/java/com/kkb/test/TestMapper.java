package com.kkb.test;

import com.github.pagehelper.PageInfo;
import com.kkb.mapper.TeamMapper;
import com.kkb.pojo.Game;
import com.kkb.pojo.Team;
import com.kkb.service.GameService;
import com.kkb.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * ClassName: TestMapper
 * 测试类
 * @author wanglina
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestMapper {
    @Resource
    private TeamMapper teamMapper;
    @Resource
    private TeamService teamService;

    @Resource
    private DataSource dataSource;

    @Resource
    private GameService gameService;

    @Test
    public void t(){
        System.out.println(dataSource);
    }
    @Test
    public void test03(){
        List<Game> games = gameService.queryAll();
        for (Game game : games) {
            System.out.println(game);
        }
    }
    @Test
    public void test02(){
        PageInfo<Team> teamPageInfo = teamService.queryByPage(1, 5, null);
        System.out.println(teamPageInfo);
    }
    @Test
    public void test01(){
        Team team = teamMapper.selectByPrimaryKey(1001);
        System.out.println(team);
    }
}
