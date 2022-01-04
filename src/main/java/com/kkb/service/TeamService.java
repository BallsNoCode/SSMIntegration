package com.kkb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.mapper.TeamMapper;
import com.kkb.pojo.Team;
import com.kkb.pojo.TeamExample;
import com.kkb.vo.QueryTeamVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: TeamService
 * 球队的业务逻辑层
 *
 * @author wanglina
 * @version 1.0
 */
@Service
public class TeamService {
    @Resource
    private TeamMapper teamMapper;

    /**
     * 多条件分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param vo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PageInfo<Team> queryByPage(Integer pageNum, Integer pageSize, QueryTeamVO vo) {
        //多条件
        TeamExample example = new TeamExample();
        //创建条件的容器
        TeamExample.Criteria criteria = example.createCriteria();
        if (vo != null) {
            if (vo.getTeamName() != null && !"".equals(vo.getTeamName().trim())) {
                criteria.andTeamNameLike("%" + vo.getTeamName().trim() + "%");
            }
            if (vo.getChineseName() != null && !"".equals(vo.getChineseName().trim())) {
                criteria.andChineseNameLike("%" + vo.getChineseName().trim() + "%");
            }
            if (vo.getCoach() != null && !"".equals(vo.getCoach().trim())) {
                criteria.andCoachLike("%" + vo.getCoach().trim() + "%");
            }
            if (vo.getBeginDate() != null) {
                criteria.andCreateTimeGreaterThanOrEqualTo(vo.getBeginDate());
            }
            if (vo.getEndDate() != null) {
                criteria.andCreateTimeLessThanOrEqualTo(vo.getEndDate());
            }
            if (vo.getArea() != null && vo.getArea() != -1) {
                criteria.andAreaEqualTo(vo.getArea());
            }
        }
        //分页
        PageHelper.startPage(pageNum, pageSize);
        List<Team> list = teamMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 添加一个球队
     *
     * @param team
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int addTeam(Team team) {
        return teamMapper.insertSelective(team);
    }

    /**
     * 根据主键查询
     *
     * @param teamId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Team queryById(int teamId) {
        return teamMapper.selectByPrimaryKey(teamId);
    }

    /**
     * 根据主键更新一个球队
     *
     * @param team
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int updateTeam(Team team) {
        return teamMapper.updateByPrimaryKeySelective(team);
    }

    /**
     * 根据主键删除------逻辑删除：实际开发中一般不允许物理删除
     *
     * @param teamId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int deleteTeam(int teamId) {
        Team team = teamMapper.selectByPrimaryKey(teamId);
        team.setIsDel(1);
        return teamMapper.updateByPrimaryKeySelective(team);
    }
}
