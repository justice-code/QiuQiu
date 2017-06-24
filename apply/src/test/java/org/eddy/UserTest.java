package org.eddy;

import org.eddy.dao.mapper.test.UserMapper;
import org.eddy.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by eddy on 2017/6/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationStart.class})
@ActiveProfiles(value="dev")
public class UserTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @Transactional
    public void test() {
        List<User> users = userMapper.selectById(2);
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(new Integer(2), users.get(0).getId());
    }

    @Test
    @Transactional
    public void test2() {
        List<User> users = userMapper.selectById(1);
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(new Integer(1), users.get(0).getId());
        System.out.println(2);
    }

    @Test
    @Transactional
    public void test3() {
        userMapper.insert("today");
    }

    @Test
    @Transactional
    public void test4() {
        User user = new User(1, "test2");

        userMapper.update(user);
    }

    @Test
    @Transactional
    public void test5() {
        userMapper.delete(1);
    }

    @Test
    @Transactional
    public void test6() {
        userMapper.selectUsers(1, 3);
    }
}
