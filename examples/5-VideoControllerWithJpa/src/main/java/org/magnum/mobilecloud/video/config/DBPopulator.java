package org.magnum.mobilecloud.video.config;

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 *
 */
@Component(value = "dbPopulator")
public class DBPopulator {


    @Autowired
    private VideoRepository videos;

    public void addVideos(){

        videos.save(randomVideo());
        videos.save(randomVideo());
        videos.save(randomVideo());
        videos.save(randomVideo());

    }


    /**
     * Construct and return a Video object with a
     * rnadom name, url, and duration.
     *
     * @return
     */
    private Video randomVideo() {
        // Information about the video
        // Construct a random identifier using Java's UUID class
        String id = UUID.randomUUID().toString();
        String title = "Video-"+id;
        String url = "http://coursera.org/some/video-"+id;
        long duration = 60 * (int)Math.rint(Math.random() * 60) * 1000; // random time up to 1hr
        return new Video(title, url, duration);
    }
}
