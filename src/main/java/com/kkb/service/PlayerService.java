package com.kkb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.mapper.PlayerMapper;
import com.kkb.pojo.Player;
import com.kkb.pojo.PlayerExample;
import com.kkb.vo.QueryPlayerVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author APPDE
 */
@Service
public class PlayerService {

    @Resource
    private PlayerMapper playerMapper;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PageInfo<Player> queryByPage(Integer pageNum, Integer pageSize, QueryPlayerVo vo) {
        PlayerExample example = new PlayerExample();
        PlayerExample.Criteria criteria = example.createCriteria();
        if (vo.getPlayerName() != null && !"".equals(vo.getPlayerName().trim())) {
            criteria.andPlayerNameLike("%" + vo.getPlayerName().trim() + "%");
        }
        if (vo.getPlayerNum() != null) {
            criteria.andPlayerNumEqualTo(vo.getPlayerNum());
        }
        if (vo.getLocation() != -1) {
            if (vo.getLocation() == 0) {
                criteria.andLocationLike("前锋");
            }
            if (vo.getLocation() == 1) {
                criteria.andLocationLike("后卫");
            }
        }
        if (vo.getBeginDate() != null) {
            criteria.andBirthdayGreaterThanOrEqualTo(vo.getBeginDate());
        }
        if (vo.getEndDate() != null) {
            criteria.andBirthdayLessThanOrEqualTo(vo.getEndDate());
        }
        if (vo.getNationality() != null && !"".equals(vo.getNationality().trim())) {
            criteria.andNationalityEqualTo(vo.getNationality().trim());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Player> players = playerMapper.selectByExample(example);
        return new PageInfo<>(players);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Player queryById(int playerId) {
        return playerMapper.selectByPrimaryKey(playerId);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int addPlayer(Player player) {
        return playerMapper.insertSelective(player);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updatePlayer(Player player) {
        return playerMapper.updateByPrimaryKeySelective(player);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int detectPlayer(int playerId) {
        Player player = playerMapper.selectByPrimaryKey(playerId);
        player.setIsDel(1);
        return playerMapper.updateByPrimaryKeySelective(player);
    }
}
