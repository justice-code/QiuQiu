package org.eddy.dao.mapper.test;

import org.apache.ibatis.annotations.Mapper;
import org.eddy.model.User;
import org.eddy.sql.config.KeyParam;

import java.util.List;

/**
 * Created by eddy on 2016/12/10.
 */
@Mapper
public interface UserMapper {

    @KeyParam("id")
    List<User> selectById(Integer id);

    @KeyParam("id")
    int insert(String name);
}
