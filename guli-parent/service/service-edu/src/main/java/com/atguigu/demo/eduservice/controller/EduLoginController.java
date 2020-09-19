package com.atguigu.demo.eduservice.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public R login()
    {
        return R.ok().data("token","admin");
    }


    @GetMapping("info")
    public  R info()
    {
        return  R.ok().data("roles","admin").data("name","SlowSail").data("avatar","https://image.baidu.com/search/detail?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&cl=2&cm=1&sc=0&lm=-1&ie=gb18030&pn=2&rn=1&di=109230&ln=30&word=%BA%A3%D4%F4%CD%F5%CD%BC%C6%AC&os=475932767,178428480&cs=226876538,4043701022&objurl=http%3A%2F%2Ff.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3D64fdb384ce5c1038242bc6c68721bf25%2F060828381f30e92435342faf44086e061c95f798.jpg&bdtype=0&simid=3098220617,3850592810&pi=0&adpicid=0&timingneed=0&spn=0&is=0,0&fr=ala&ala=1&alatpl=adress&pos=1&oriquery=%E6%B5%B7%E8%B4%BC%E7%8E%8B%E5%9B%BE%E7%89%87&hs=2&xthttps=000000");
    }
}
