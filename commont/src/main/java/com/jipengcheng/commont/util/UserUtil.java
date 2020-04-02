package com.jipengcheng.commont.util;

public class UserUtil {
    public static boolean checkPhone(String phone){
        if (phone.isEmpty()&&phone.trim().length()==11){
            String reg="^1[3-9]\\d{9}$";
            boolean matches = phone.trim().matches(reg);
            return matches;
        }else {
            return false;
        }

    }
    public static boolean checkPassword(String password){
        int length = password.trim().length();
        if (length>8&&length<16){
            String reg="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
            boolean matches = password.trim().matches(reg);
            return matches;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        /*boolean b = UserUtil.checkPhone("1761 009537");
        System.out.println(b);*/
        boolean b = UserUtil.checkPassword("111111111aa!");
        System.out.println(b);
    }
}
