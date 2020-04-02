package com.jipengcheng.provider.service.impl;


import com.jipengcheng.commont.Config.RandomConfig;
import com.jipengcheng.commont.Config.RedisKeyConfig;
import com.jipengcheng.commont.util.JwtUtil;
import com.jipengcheng.commont.util.MD5Util;
import com.jipengcheng.commont.util.RandomUtil;
import com.jipengcheng.commont.util.UserUtil;
import com.jipengcheng.commont.vo.R;
import com.jipengcheng.commont.vo.ResponseEntity;
import com.jipengcheng.entity.Entity.Register;
import com.jipengcheng.provider.dao.RegisterDao;
import com.jipengcheng.provider.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author 17611009537
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private RestTemplate restTemplate;
   @Autowired
   private StringRedisTemplate redisTemplate;

    @Override
    public R  sendmsg(String phone) {
            boolean b = UserUtil.checkPhone(phone);
            if (b){
                Boolean aBoolean = redisTemplate.hasKey(RedisKeyConfig.RANDOM_KEY + phone);
                if (!aBoolean){
                    String random = RandomUtil.getRandom(RandomConfig.RANDOM_NUM);
                    redisTemplate.opsForValue().set(RedisKeyConfig.RANDOM_KEY+phone,random,RedisKeyConfig.RANDOM_TIME,TimeUnit.MINUTES);
                    ResponseEntity forObject = restTemplate.
                            getForObject("https://way.jd.com/chuangxin/VerCodesms?mobile="+phone+"&content=【北方厨师】你的验证码是："
                                    + random +
                                    "，3分钟内有效！&appkey=314c2074c7d94ced19d57bf28b9e0a5a", ResponseEntity.class);
                    if ("1000".equals(forObject.getCode())){
                        return R.ok();
                    }else {
                        return R.fail();
                    }
                }else {
                    return R.fail("对不起，操作频繁");
                }

            }else {
                return R.fail("手机输入错误，重新输入");
            }
    }

    @Override
    public R registerPerson(Register register) {
        /*手机号注册*/
        if (register.getUsername()==null){
            boolean b = UserUtil.checkPhone(register.getPhone());
            if (b){
                Register byPhone = registerDao.findByPhone(RedisKeyConfig.RANDOM_KEY+register.getPhone());
                if (byPhone==null){
                    if (redisTemplate.hasKey(register.getPhone())){
                        String s = redisTemplate.opsForValue().get(register.getPhone());
                        if (register.getVerificationCode().equals(s)){
                            try {
                                int i = registerDao.insertPerson(register);
                                if (i<0){
                                    return R.fail("注册失败");
                                }else {
                                    redisTemplate.delete(RedisKeyConfig.RANDOM_KEY + register.getPhone());
                                    return R.ok();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                return R.fail("注册信息有误");
                            }
                        }else {
                            return R.fail("验证码已失效");
                        }
                    }
                }else {
                    return R.fail("该手机号已经注册过");
                }
            }
/*信息注册*/
        }else {
            if (register.getUsername().length()<50){
                if (UserUtil.checkPassword(register.getPassword())){
                    String s = MD5Util.MD5Encode(register.getUsername(), "utf-8");
                    register.setPassword(s);
                    if (UserUtil.checkPhone(register.getPhone())){
                        if (registerDao.findByPhone(register.getPhone())==null){
                            try {
                                int i = registerDao.insertPerson(register);
                                if (i<0){
                                    return R.fail("注册失败");
                                }else {
                                    return R.ok("注册成功，请登录");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                return R.fail("请重新检查后再注册");
                            }
                        }else {
                            return R.fail("手机号已存在，请重新输入手机号");
                        }

                    }else {
                        return R.fail("请正确输入手机号");
                    }

                }else {
                    R.fail("密码输入不符合要求，请重新输入");
                }
            }
        }
        return null;
    }

    @Override
    public R loginPerson(Register register) {
        String phone = register.getPhone();
        if (register.getUsername()==null){
            if (UserUtil.checkPhone(phone)){
                if (!redisTemplate.hasKey(RedisKeyConfig.LOGIN_KEY)){
                    Boolean aBoolean = redisTemplate.hasKey(RedisKeyConfig.RANDOM_KEY + phone);
                    if (aBoolean){
                        if (register.getVerificationCode().
                                equals( redisTemplate.opsForValue().get(RedisKeyConfig.RANDOM_KEY + phone))){
                            String token = JwtUtil.createToken(register.getPhone());
                            redisTemplate.opsForValue().set(RedisKeyConfig.LOGIN_KEY+register.getPhone(),token,RedisKeyConfig.LOGIN_TIME,TimeUnit.MINUTES);
                            return R.ok(token);
                        }else {
                           if (Integer.parseInt(redisTemplate.opsForValue().get(RedisKeyConfig.LOGIN_NUM+phone))>5){
                               return R.fail("您今天失败次数已达上限，24小时候再试");
                           }else {
                               redisTemplate.opsForValue().increment(RedisKeyConfig.LOGIN_NUM_TIME+phone,1);
                               int i = Integer.parseInt(redisTemplate.opsForValue().get(RedisKeyConfig.LOGIN_NUM_TIME + phone));
                               if (i>=5){
                                   redisTemplate.expire(RedisKeyConfig.LOGIN_NUM_TIME + phone,RedisKeyConfig.LOGIN_NUM_OUT,TimeUnit.HOURS);
                                   return R.fail("您今天失败次数已达上限，24小时候再试");
                               }
                           }
                            return R.fail("登录失败，请重新登录");
                        }
                    }else {
                        R.fail("验证码过期，请重新获取");
                    }
                }else {
                    String token = JwtUtil.createToken(register.getPhone());

                }

            }else {
                R.fail("手机号输入有误，请重新输入");
            }
        }else {

        }
        return null;
    }


}
