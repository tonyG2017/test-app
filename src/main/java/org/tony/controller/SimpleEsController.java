package org.tony.controller;

import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tony.config.TestAppConfiguration;
import org.tony.model.Greeting;
import org.tony.service.SimpleElasticService;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class SimpleEsController {

    private final SimpleElasticService simpleElasticService;
    @Autowired
    public SimpleEsController(SimpleElasticService simpleElasticService){
        this.simpleElasticService = simpleElasticService;
    }


    @RequestMapping(value = "/es/indices/{index}", method = GET )  //If we ignore method, all of the methods, i.e., GET/POST/DELETE show in the swagger ui.
    public boolean doesIndexExist(@PathVariable String index) {
        return simpleElasticService.indexExist(index);
    }

    @RequestMapping(value = "/es/indices", method = GET )  //If we ignore method, all of the methods, i.e., GET/POST/DELETE show in the swagger ui.
    public String[] catIndices() {
        return simpleElasticService.catIndices();
    }
    @RequestMapping(value = "/es/indices/{index}/matchAll", method = GET )  //If we ignore method, all of the methods, i.e., GET/POST/DELETE show in the swagger ui.
    public SearchHit[] searchhMatchAll(@PathVariable String index) {
        SearchHit[] result =null;
        try{
            result = simpleElasticService.simpleSearchMatchAll(index);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    @RequestMapping(value = "/es/indices/{index}/termSearch", method = GET )  //If we ignore method, all of the methods, i.e., GET/POST/DELETE show in the swagger ui.
    public SearchHit[] searchTerm(@PathVariable String index, @RequestParam String field, @RequestParam String value) {
        SearchHit[] result =null;
        try{
            result = simpleElasticService.simpleSearchTerm(index, field,value);
            for(SearchHit hit: result)
                System.out.println(hit);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    @RequestMapping(value = "/es/indices/{index}/matchSearch", method = GET )  //If we ignore method, all of the methods, i.e., GET/POST/DELETE show in the swagger ui.
    public SearchHit[] searchMatch(@PathVariable String index, @RequestParam String field, @RequestParam String value) {
        SearchHit[] result =null;
        try{
            result = simpleElasticService.simpleSearchMatch(index, field,value);
            for(SearchHit hit: result)
                System.out.println(hit);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }


}