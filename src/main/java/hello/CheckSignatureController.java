//this is branch dev

package hello;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;
import java.security.*;
import java.util.StringJoiner;



import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckSignatureController {

    private final static String token="weixin";
    private final static String KEY_SHA1 = "SHA1";  

    @RequestMapping("/checksignature")
    public String checkSignature(@RequestParam(value="signature") String signature , @RequestParam(value="timestamp") String timestamp , @RequestParam(value="nonce") String nonce , @RequestParam(value="echostr") String echostr) {
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        
        String tempArray[]={token,timestamp,nonce};
        Arrays.sort(tempArray);
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<tempArray.length;i++){
            sb.append(tempArray[i]);
        };
        String tempStr=sb.toString();
        System.out.println(tempStr); 


        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            'a', 'b', 'c', 'd', 'e', 'f'};

        String signatureSha1=null;
            
        try {
            MessageDigest mdTemp = MessageDigest.getInstance(KEY_SHA1);
            mdTemp.update(tempStr.getBytes("UTF-8"));
         
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            signatureSha1=new String(buf);
            System.out.println(signatureSha1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }    
        
        if(signature.equals(signatureSha1)){
            System.out.println("passed");
            return echostr;
        }else{
            System.out.println("failed");
            return signatureSha1;
        }

        
    }
}
