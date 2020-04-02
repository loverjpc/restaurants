import com.jipengcheng.commont.util.EncryptUtil;
import com.jipengcheng.commont.util.IdGenerator;
import com.jipengcheng.commont.util.JwtUtil;
import com.jipengcheng.commont.util.MD5Util;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.stream.IntStream;

public class test {
    @Test
    public void getNum(){
        Random random = new Random();
        String ss="";
        for (int i=0;i<6;i++){
            int i1 = random.nextInt(10);
            ss+=String.valueOf(i1);
        }
        System.out.println(ss);
        System.out.println(ss.length());

    }
    @Test
    public void getPassword() throws NoSuchAlgorithmException {
       /* IdGenerator idGenerator = new IdGenerator();
        long l1 = idGenerator.nextId();
        long l2 = idGenerator.nextId();
        long l3 = idGenerator.nextId();
        long l4 = idGenerator.nextId();
        long l5 = idGenerator.nextId();
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println(l4);
        System.out.println(l5);*/
        /*MessageDigest md = MessageDigest.getInstance("MD5");
        String str="7889900";
        md.update(str.getBytes());
        byte[]byteDigest = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < byteDigest.length; offset++) {
            i = byteDigest[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        System.out.println(buf)*/;
        //32位加密

        // 16位的加密
        //return buf.toString().substring(8, 24);
        String s = MD5Util.MD5Encode("123456", "utf-8");
        String s2 = MD5Util.MD5Encode("123456", "utf-8");
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s.equals(s2));

    }
    @Test
    public void loginTest(){
        String token = JwtUtil.createToken("11111");
        String token2 = JwtUtil.createToken("11111");
        String token3= JwtUtil.createToken("11111");
        String token4 = JwtUtil.createToken("11111");
        System.out.println(token.equals(token2));
        System.out.println(token.equals(token3));
        System.out.println(token2.equals(token3));
    }
}
