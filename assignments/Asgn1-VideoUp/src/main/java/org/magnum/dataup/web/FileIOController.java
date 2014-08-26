package org.magnum.dataup.web;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Controller to demonstrate the various file uploading and streaming methods
 */
@Controller
@RequestMapping("/fileio")
public class FileIOController {

    /**
     * <form method="POST" enctype="multipart/form-data" action="/upload/fromform">
     *    File to upload: <input type="file" name="file"><br />
     *    Name: <input type="text" name="name"><br /> <br />
     *    <input type="submit" value="Upload"> Press here to upload the file!
     * </form>
     *
     * @param name
     * @param data
     * @return
     * @throws IOException
     */
    @RequestMapping(value= "/upload", method= RequestMethod.POST)
    public @ResponseBody String uploadBasic(@RequestParam("name") String name,
                                            @RequestParam("file") MultipartFile data) throws IOException {

        if (!data.isEmpty()) {
            try {
                byte[] bytes = data.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(name + "-uploaded"))
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

    @RequestMapping(value="/stream", method=RequestMethod.GET)
    public void getMedia(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam("name") String name) throws IOException
    {
        try {
            // get your file as InputStream
            InputStream is = new BufferedInputStream(
                    new FileInputStream(new File(name + "-uploaded"))
            );
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
