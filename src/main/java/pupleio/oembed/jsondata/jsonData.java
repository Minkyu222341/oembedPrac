package pupleio.oembed.jsondata;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class jsonData {

    public jsonData() {

    }
    public JSONObject json (String data) throws JSONException {
        System.out.println("data = " + data);
        int html = data.indexOf(",\"html\"");
        int lastDot = data.lastIndexOf("\"");
        data=data.substring(0,html)+"}";
        JSONObject jObject = youtubeGetJsonObject(data);
        return jObject;
    }

    private JSONObject youtubeGetJsonObject(String data) throws JSONException {
        JSONObject jObject = new JSONObject(data);
        String title = jObject.getString("title");
        String author_name = jObject.getString("author_name");
        String author_url =  jObject.getString("author_url");
        String type = jObject.getString("type");
        String height = jObject.getString("height");
        String width = jObject.getString("width");
        String version = jObject.getString("version");
        String provider_name = jObject.getString("provider_name");
        String provider_url = jObject.getString("provider_url");
        String humbnail_height = jObject.getString("thumbnail_height");
        String thumbnail_width = jObject.getString("thumbnail_width");
        String thumbnail_url = jObject.getString("thumbnail_url");
        return jObject;
    }
}
