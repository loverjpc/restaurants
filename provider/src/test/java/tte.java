import com.jipengcheng.provider.ProviderStart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderStart.class)
public class tte {
    @Autowired
    private RestTemplate restTemplate;
    @Test
    public void get(){
        String forObject = restTemplate.
                getForObject("https://way.jd.com/chuangxin/VerCodesms?" +
                        "mobile=17611009537&content=【创信】你的验证码是：5873，3分钟内有效！" +
                        "&appkey=314c2074c7d94ced19d57bf28b9e0a5a", String.class);
        System.out.println(forObject);
    }
}
