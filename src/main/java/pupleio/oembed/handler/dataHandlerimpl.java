package pupleio.oembed.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class dataHandlerimpl implements  dataHandler{
    private static final Logger log = LoggerFactory.getLogger(dataHandlerimpl.class);


    @Override
    public String getData(String url) throws IOException {
        log.info("dataHandlerimpl.getData");
        String data = null;
        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setDoOutput(true);
        try{
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while(br.ready()) {
                sb.append(br.readLine());
            }
            data = convertString(String.valueOf(sb));

        }catch(Exception e) {
            e.printStackTrace();
        }
//daata 유효성 검사 해야함
        return data;
    }
    //유니코드 변환
    @Override
    public  String convertString(String val) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < val.length(); i++) {
            if ('\\' == val.charAt(i) && 'u' == val.charAt(i + 1)) {
                Character r = (char) Integer.parseInt(val.substring(i + 2, i + 6), 16);
                sb.append(r);
                i += 5;
            } else {
                sb.append(val.charAt(i));
            }
        }
// 결과 리턴
        return sb.toString();
    }

}