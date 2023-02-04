package pupleio.oembed.handler;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class jsonHadnlerimpl implements jsonHandler {
    private static final Logger log = LoggerFactory.getLogger(jsonHadnlerimpl.class);

    public jsonHadnlerimpl() {
    }

    @Override
    public JSONObject youtubeJson(String data){
        log.info("jsonHadnlerimpl.youtubeJson");
        JSONObject jsonobj = new JSONObject();
        String [] key = new String[]{"title","author_name","author_url","type","height","width","version","provider_name","provider_url","thumbnail_height","thumbnail_width","thumbnail_url","html"};
        data=data.replace("{","");
        jsonobj.put("url","youtube");
        for(int i=0; i<key.length; i++){
            if(i== key.length-1){
                int start = data.indexOf(key[i]);
                int end= data.indexOf("}");
                String value = data.substring(start+key[i].length()+3, end-1);
                jsonobj.put(key[i], value);
            }
            else {

                int start = data.indexOf(key[i]);
                int end = data.indexOf(key[i + 1]);
                String value = data.substring(start+key[i].length()+2, end);
                value = value.replace(",","");
                value= value.replace("\"","");
                jsonobj.put(key[i], value);
            }

        }

        return jsonobj;



    }



    @Override
    public JSONObject vimeoJson(String data){
        log.info("jsonHadnlerimpl.vimeoJson");
        JSONObject jsonobj = new JSONObject();
        String [] key = new String[]{"type","version","provider_name","provider_url","title","author_name","author_url","is_plus","account_type","html","width","height","duration","description","thumbnail_url","thumbnail_width","thumbnail_height","thumbnail_url_with_play_button","upload_date","video_id","uri"};
        data=data.replace("{","");
        jsonobj.put("url","vimeo");
        for(int i=0; i<key.length; i++){
            if(i==11){
                int start = data.indexOf(key[i]);
                int end= data.indexOf("frameborder");
                String value = data.substring(start+key[i].length()+3, end);
                value=value.replace("\\", "");
                value= value.replace("\"","");
                jsonobj.put(key[i], value);
                continue;

            }
            if(i== key.length-1){
                int start = data.indexOf(key[i]);
                int end= data.indexOf("}");
                String value = data.substring(start+key[i].length()+3, end);
                value = getString(value);
                jsonobj.put(key[i], value);
            }
            else if(key[i]=="html"){
                int start = data.indexOf(key[i]);
                int end = data.indexOf("<\\/iframe>");
                String value = data.substring(start+key[i].length()+3, end-1)+"><\\/iframe>";
                value=value.replace("\\", "");
                jsonobj.put(key[i], value);
            }
            else {
                int start = data.indexOf(key[i]);
                int end = data.indexOf(key[i + 1]);
                String value = data.substring(start+key[i].length()+2, end-1);
                value = getString(value);
                jsonobj.put(key[i], value);
            }

        }
        return jsonobj;

    }

    private String getString(String value) {
        value = value.replace("\\", "");
        value = value.replace(",","");
        value = value.replace("\"","");
        return value;
    }


}