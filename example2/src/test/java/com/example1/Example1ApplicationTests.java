package com.example1;

import com.example1.po.UserBean;
import com.example1.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Example1ApplicationTests {
	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {
		List<UserBean> users = userService.getAll();
 	}

}
