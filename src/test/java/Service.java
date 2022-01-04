import com.github.pagehelper.PageInfo;
import com.kkb.pojo.Game;
import com.kkb.pojo.Team;
import com.kkb.service.GameService;
import com.kkb.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class Service {

    @Resource
    private TeamService teamService;
    @Resource
    private GameService gameService;

    @Test
    public void serviceTest01(){
        PageInfo<Team> teamPageInfo = teamService.queryByPage(1, 5, null);
        System.out.println(teamPageInfo);
    }

    @Test
    public void serviceTest02(){
        List<Game> games = gameService.queryAll();
        games.forEach(System.out::println);
    }
}
