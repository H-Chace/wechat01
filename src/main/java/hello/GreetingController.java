//this is branch dev

package hello;

import java.io.Console;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;
import java.security.MessageDigest;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final String token="weixin";

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="signature") String signature , @RequestParam(value="timestamp") String timestamp , @RequestParam(value="nonce") String nonce , @RequestParam(value="echostr") String echostr) {
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        
        String tempArray[]={token,timestamp,nonce};
        Arrays.sort(tempArray);
        int len=tempArray.length;
        System.out.println(len);


        // MessageDigest sha1=MessageDigest.getInstance(SHA-1);
        // byte[] strbyte=signature.

                
        return echostr;
    }
}
