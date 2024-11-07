package iisi.example.gia.controller;

import iisi.example.gia.model.vo.TestVO;
import iisi.example.gia.util.SecurityUtil;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping(
            value = "/test",
            method = {RequestMethod.GET, RequestMethod.POST})
    public User test(TestVO testVO) {
        User loginUser = SecurityUtil.getLoginUser();
        return loginUser;
    }
}
