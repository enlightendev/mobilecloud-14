package org.magnum.dataup;

import io.magnum.autograder.junit.Rubric;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.magnum.dataup.api.TestSvcApi;
import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.magnum.dataup.model.VideoStatus.VideoState;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.*;

public class RetrofitTest {

	private static final String SERVER = "http://localhost:8080";

    private File testVideoData = new File("C:\\dev\\java\\edu\\mobilecloud-14\\assignments\\Asgn1-VideoUp\\src\\test\\resources\\test.mp4");

	private TestSvcApi tstService = new RestAdapter.Builder()
			.setEndpoint(SERVER).build()
			.create(TestSvcApi.class);

	@Test
	public void testListRepos() {
        List<String> repos = tstService.listRepos("juan");

        System.out.println(repos);
    }

    @Test
    public void testMultiPart(){

        TypedFile file = new TypedFile("video/mp4", testVideoData);
        String result = tstService.upload(12345l,file);

        System.out.println(result);
    }

}