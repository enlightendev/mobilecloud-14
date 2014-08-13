package org.magnum.dataup.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by JL25292 on 8/13/2014.
 */
@Controller
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

}
