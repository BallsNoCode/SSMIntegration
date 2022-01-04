package com.kkb.controller;

import com.kkb.pojo.Admins;
import com.kkb.service.AdminService;
import com.kkb.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author APPDE
 */
@Controller
@RequestMapping("admin")
@ResponseBody
public class LoginController {

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResultVO<Admins> login(String loginName,String password){
        List<Admins> admins = adminService.checkLogin(loginName, password);
        if (admins.size() == 0) {
            return new ResultVO<>(500,"服务器异常");
        }
        Admins admin = admins.get(0);
        return new ResultVO<>(admin);
    }

    @RequestMapping(value = "loginOut",method = RequestMethod.GET)
    public void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/pages/login.html");
    }

}
