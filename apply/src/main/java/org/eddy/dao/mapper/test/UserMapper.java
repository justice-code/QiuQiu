package org.eddy.dao.mapper.test;

import org.apache.ibatis.annotations.Mapper;
import org.eddy.model.User;

import java.util.List;

/**
 * Created by eddy on 2016/12/10.
 */
@Mapper
public interface UserMapper {

    List<User> selectById(Integer id);

    int insert(String name);
}
