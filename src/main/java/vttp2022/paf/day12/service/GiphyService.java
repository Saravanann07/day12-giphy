package vttp2022.paf.day12.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {
    //1
    private static final String URL = "https://api.giphy.com/v1/gifs/search?";
    
    // export GIPHY_API_KEY="VUFJ7F2jHv9KujOK3WL29pGTkFNwNOPb"
    @Value("${giphy.api.key}")
    private String giphyKey;

    public List<String> getGiphs(String q){
        return getGiphs(q, "pg", 10);

    }

    public List<String> getGiphs(String q, String rating){
        return getGiphs(q, rating, 10);

    }

    public List<String> getGiphs(String q, Integer limit){
        return getGiphs(q, "pg", limit);

    }

    public List<String> getGiphs(String q, String rating, Integer limit){
        List<String> list = new LinkedList<>();
        //2
        String giphySearch = UriComponentsBuilder.fromUriString(URL)
            .queryParam("key", giphyKey)
            .queryParam("q", q)
            .queryParam("limit", limit)
            .queryParam("rating", rating)
            .toUriString();
        //3
        RequestEntity<Void> req = RequestEntity
            .get(giphySearch)
            .accept(MediaType.APPLICATION_JSON)
            .build();
        //4
        RestTemplate template = new RestTemplate();
        //5
        ResponseEntity<String> resp = template.exchange(req, String.class);

        //6
        InputStream is = new ByteArrayInputStream(resp.getBody().getBytes());
        JsonReader reader = Json.createReader(is);
        JsonObject obj = reader.readObject();
        JsonArray array = obj.getJsonArray("data");
        //7
        for(int i =0; i< array.size(); i++){
            String url = array.getJsonObject(i)
                            .getJsonObject("images")
                            .getJsonObject("fixed_width")
                            .getString("url");
           list.add(url);
        }
    
        return list;
    }
        
   
}
