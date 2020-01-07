package net.cc.demo.controller;

import net.cc.demo.dao.CcUserMapper;
import net.cc.demo.entity.CcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @classname CcController
 * @date 2020-01-07 00:35
 * @description TODO
 */
@RestController
public class CcController {

    @Autowired
    private CcUserMapper ccUserMapper;

    @GetMapping("/test")
    public void test() {

        List<CcUser> list = ccUserMapper.selectList(null);

        list.forEach(System.out::println);
    }
}