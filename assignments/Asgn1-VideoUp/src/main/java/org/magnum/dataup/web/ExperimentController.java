package org.magnum.dataup.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * A controller for showing how to use retrofit to define its api and create a client that abstracts much of the
 * http semantics.
 */
@Controller
@RequestMapping("/test")
public class ExperimentController {

    /**
     *
     * @param pvar1
     * @param name
     * @return
     */
    @RequestMapping(value="/pathvar/{pvar1}", method=RequestMethod.GET)
    public @ResponseBody String testPathVar(
                                            @PathVariable String pvar1,
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "male", required = false) Boolean male){

        return "breakpoint here";
    }


    /**
     *
     * @param user
     * @return
     */
    @RequestMapping(value="/{user}/repos", method=RequestMethod.GET)
    public @ResponseBody List<String> listRepos(@PathVariable String user){

        String[] repos = new String[]{user + "rep1",user + "rep2"};

        return Arrays.asList(repos);
    }

    @RequestMapping(value= "/{id}/data", method=RequestMethod.POST)
    public @ResponseBody String uploadFile(@PathVariable long id,
                                           @RequestParam("file") MultipartFile file) throws IOException {


        return "breakpoint here";
    }
}
