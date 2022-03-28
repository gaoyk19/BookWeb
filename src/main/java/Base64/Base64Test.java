package Base64;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Test {
    public static void main(String[] args){
        String str="这是编码内容";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] buffer=str.getBytes(StandardCharsets.UTF_8);
        String enstr = encoder.encodeToString(buffer);
        System.out.println(enstr);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(enstr);
        String dstr=new String(decode);
        System.out.println(dstr);


    }



}
