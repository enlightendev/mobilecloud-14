package org.magnum.dataup.web;

import org.magnum.dataup.VideoSvcApi;
import org.magnum.dataup.model.VideoStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JL25292 on 8/13/2014.
 */
@Controller
@RequestMapping("/test")
public class ExperimentController {

    /**
     * testing method to test pathvar and request params
     * @param pvar1
     * @param name
     * @return
     */
    @RequestMapping(value="/pathvar/{pvar1}", method=RequestMethod.GET)
    public @ResponseBody String testPathVar(
                                            @PathVariable String pvar1,
                                            @RequestParam(value = "name", required = false) String name){

        if(name == null)
            name = "nothing";

        return "You entered " + pvar1 + " as a path var and " + name + " as a request param.";
    }


    /**
     * @GET("/test/{user}/repos")
     * List<String> listRepos(@Path("user") String user);
     *
     * @param user
     * @return
     */
    @RequestMapping(value="/{user}/repos", method=RequestMethod.GET)
    public @ResponseBody
    List<String> testClient(@PathVariable String user){

        String[] repos = new String[]{user + "rep1",user + "rep2"};

        return Arrays.asList(repos);
    }

    @RequestMapping(value= "/{id}/data", method=RequestMethod.POST)
    public @ResponseBody String upload(@PathVariable long id,
                                       @RequestParam("file") MultipartFile file) throws IOException {

        return "nice";
    }
}
