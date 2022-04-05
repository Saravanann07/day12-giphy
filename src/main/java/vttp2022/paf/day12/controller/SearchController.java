package vttp2022.paf.day12.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.paf.day12.service.GiphyService;

@Controller
@RequestMapping(path="")
public class SearchController {

    @Autowired
    private GiphyService giphySvc;

    @GetMapping(path="/search")
    public String getSearch (@RequestParam (required = true) String q,
    @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
    @RequestParam(name = "rating", required = false, defaultValue = "pg") String rating, 
    Model model){

        List<String> list = giphySvc.getGiphs(q, rating, limit);
        model.addAttribute("list", list);
        return "results";
    }
}
