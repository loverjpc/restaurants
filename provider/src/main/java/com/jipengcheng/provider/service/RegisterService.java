package com.jipengcheng.provider.service;

import com.jipengcheng.entity.Entity.Register;
import com.jipengcheng.commont.vo.R;

public interface RegisterService {
    /*消息发送*/
    public R sendmsg(String phone);
    /*注册*/
    public R registerPerson(Register register);
    /*登录*/
    public R loginPerson(Register register);
}
