package client;

import com.zhaidaosi.game.jgframework.Boot;
import com.zhaidaosi.game.jgframework.common.encrpt.BaseRsa;

import java.io.IOException;
import java.util.Properties;


public class TestRsa {

    public static void main(String[] args) throws IOException {
        Properties pps = new Properties();
        pps.load(Boot.class.getClassLoader().getResourceAsStream("jgframework.properties"));
        BaseRsa.init(pps.getProperty("rsa.publicKey"), pps.getProperty("rsa.privateKey"));
        System.out.println(BaseRsa.encrypt("测试"));
        System.out.println(BaseRsa.decrypt("oGFYmt4TQ7VJ/INqLzHjZoRndf3Hdptuuqju2/EkiyOdsyJFBrZw9gYlpytb5Tf2cjnTGvBQZfrpZW6xgBMCMNeGXirYsv4Dg006yNutwlG+tvwRpb9QO+PL0kfNO39BHpKWsoMzTs08uRcdjDvFEoTVgo6j9Fiws3nl6tbXOLE="));
    }

}
