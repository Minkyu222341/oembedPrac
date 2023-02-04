package pupleio.oembed.service;

import org.json.JSONException;
import org.json.simple.JSONObject;

import java.io.IOException;

public interface OembedService {
    org.json.JSONObject youtubeHandler(String url) throws IOException, JSONException;
    JSONObject instagramHandler(String url) throws IOException;
    JSONObject twitterHandler(String url) throws IOException;
    JSONObject vimeoHandler(String url) throws IOException;
}