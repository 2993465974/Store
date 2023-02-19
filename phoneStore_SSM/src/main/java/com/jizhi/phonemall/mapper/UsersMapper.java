package com.jizhi.phonemall.mapper;

import com.jizhi.phonemall.entity.Users;
import com.jizhi.phonemall.entity.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UsersMapper {
    long countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    /**
     * 存在返回Users对象，不存在返回null
     * @param username
     * @param password
     * @return
     */
    @Select("select * from users where username = #{username} and password = #{password}")
    Users findUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
    @Select("select * from users where username = #{username}")
    Users findUsername(String username);

    /**
     * 查询所有用户(分页)
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Select("select * from users order by uid desc limit #{startIndex},#{pageSize}")
    List<Users> findAll(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
    @Select("select * from users where username like concat('%',#{username},'%') order by uid desc")
    List<Users> findUserByLikeUsername(String username);
}