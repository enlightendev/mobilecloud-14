package org.magnum.dataup.web.rest;

import org.magnum.dataup.VideoSvcApi;
import org.magnum.dataup.model.Video;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * http://spring.io/guides/gs/uploading-files/
 * Created by JL25292 on 8/13/2014.
 */
@Controller
public class VideoServiceController {

    // An in-memory list that the servlet uses to store the
    // videos that are sent to it by clients
    private List<Video> videos = new CopyOnWriteArrayList<Video>();

    final AtomicLong counter = new AtomicLong();

    @RequestMapping(value= VideoSvcApi.VIDEO_SVC_PATH, method=RequestMethod.POST)
    public @ResponseBody Video addVideo(@RequestBody Video v){
        v.setId(counter.incrementAndGet());
        v.setDataUrl(getDataUrl(v.getId()));
        videos.add(v);
        return v;
    }

    @RequestMapping(value=VideoSvcApi.VIDEO_SVC_PATH, method=RequestMethod.GET)
    public @ResponseBody List<Video> getVideoList(){
        return videos;
    }

    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("/" + name + "-uploaded"))
                );

                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }


    private String getDataUrl(long videoId){
        String url = getUrlBaseForLocalServer() + "/video/" + videoId + "/data";
        return url;
    }

    private String getUrlBaseForLocalServer() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String base =
                "http://"+request.getServerName()
                        + ((request.getServerPort() != 80) ? ":"+request.getServerPort() : "");
        return base;
    }

}
