package pupleio.oembed.service;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pupleio.oembed.handler.dataHandlerimpl;
import pupleio.oembed.handler.jsonHadnlerimpl;
import pupleio.oembed.jsondata.jsonData;

import java.io.IOException;

@Service
public class OembedServiceimpl implements OembedService {
    private static final Logger log = LoggerFactory.getLogger(OembedServiceimpl.class);

    private final dataHandlerimpl datahanlderimpl;
    private final jsonHadnlerimpl jsonhandler;
    private final jsonData jsondata;

    @Autowired
    public OembedServiceimpl(dataHandlerimpl datahanlderimpl, jsonHadnlerimpl jsonhandler, jsonData jsondata) {
        this.datahanlderimpl=datahanlderimpl;
        this.jsonhandler=jsonhandler;
        this.jsondata = jsondata;
    }


    public org.json.JSONObject urlConnector(String url) throws IOException {
        log.info("OembedServiceimpl.urlConnector");
        String youtube = "www.youtube.com";
        String instagram = "www.instagram.com";
        String twitter = "twitter.com";
        String vimeo = "vimeo.com";

        try {
            System.out.println("test");
            if (url.contains(youtube)) return youtubeHandler(url);
        }catch (NullPointerException | JSONException e){
            log.info(String.valueOf(e));}
        log.info("null");
        return null;
    }

    @Override
    public org.json.JSONObject youtubeHandler(String url) throws IOException, JSONException {
        log.info("OembedServiceimpl.youtubeHandler");
        String top="https://www.youtube.com/oembed?url=https%3A//youtube.com/watch%3Fv%3D";
        String mid = url.split("watch\\?v=")[1];
        String botm="&format=json";
        String result=top+mid+botm;

        String jsonData = getJsonObject(result);
        return jsondata.json(jsonData);
    }

    @Override
    public JSONObject instagramHandler(String url) throws IOException {
        String result= null;
        String jsonData = getJsonObject(result);
        return null;
    }

    @Override
    public JSONObject twitterHandler(String url) throws IOException {
        String  result = null;
        String jsonData = getJsonObject(result);
        return null;
    }

    @Override
    public JSONObject vimeoHandler(String url) throws IOException {
        log.info("OembedServiceimpl.vimeoHandler");
        //https://vimeo.com/api/oembed.json?url=https://vimeo.com/20097015
        String top="https://vimeo.com/api/oembed.json?url=";
        String mid = url;
        String result=top+mid;
        System.out.println(result);

        String jsonData = getJsonObject(result);

        try {
            return jsonhandler.vimeoJson(jsonData);
        } catch(StringIndexOutOfBoundsException e){
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("error","error");
            jsonobj.put("msg","vimeo oembed ????????? ?????? ?????? ????????? ????????? ??????????????? ????????? ?????? ??? ????????????.\n" +
                    "???????????? ????????? ????????? ????????? ?????? ?????? ???????????????.");
            log.info("StringIndexOutOfBoundsException");
            return jsonobj;
        }
    }
    private String getJsonObject(String result) throws IOException {
        String data = datahanlderimpl.getData(result);
        return data;
    }

    private JSONObject getError() {
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("error","error");
        jsonobj.put("msg","???????????????. ?????? ????????? ?????? ????????? url ?????????. ????????? ???????????? vimeo??? ???????????????. ?????? ?????? ?????? ?????? ??? ??? ????????? ???????????????.");
        return jsonobj;
    }

}
