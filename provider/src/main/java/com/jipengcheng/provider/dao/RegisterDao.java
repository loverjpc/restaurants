package com.jipengcheng.provider.dao;

import com.jipengcheng.entity.Entity.Register;
import org.apache.ibatis.annotations.Param;

public interface RegisterDao {
    public int insertPerson(Register register);
    public Register findByPhone(@Param("phone") String phone);
}
