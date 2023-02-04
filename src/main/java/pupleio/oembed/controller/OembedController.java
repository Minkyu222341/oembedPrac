package pupleio.oembed.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pupleio.oembed.service.OembedServiceimpl;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
public class OembedController {
    private static final Logger log = LoggerFactory.getLogger(OembedController.class);
    private final OembedServiceimpl oembedserviceimpl;

    @Autowired
    public OembedController(OembedServiceimpl oembedserviceimpl) {
        this.oembedserviceimpl = oembedserviceimpl;
    }

    @RequestMapping("/oembed")
    public String controller(){
        return "oembed";
    }

    @GetMapping("/search")
    @ResponseBody
    public JSONObject search(HttpServletRequest request, Model model) throws IOException {
        log.info("search controller 작동");
        String url = request.getParameter("url");
        return oembedserviceimpl.urlConnector(url);
    }
}