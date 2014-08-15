package org.magnum.dataup;

import org.junit.Test;
import org.magnum.dataup.web.api.ExperimentControllerApi;
import retrofit.RestAdapter;
import retrofit.mime.TypedFile;

import java.io.File;
import java.util.*;

public class ExperimentControllerTest {

	private static final String SERVER = "http://localhost:8080";

    private File testVideoData = new File("src/test/resources/test.mp4");

	private ExperimentControllerApi tstService = new RestAdapter.Builder()
			.setEndpoint(SERVER).build()
			.create(ExperimentControllerApi.class);

	@Test
	public void testListRepos() {
        List<String> repos = tstService.listRepos("juan");

        System.out.println(repos);
    }

    @Test
    public void testMultiPart(){

        TypedFile file = new TypedFile("video/mp4", testVideoData);
        String result = tstService.uploadFile(12345l, file);

        System.out.println(result);
    }

}