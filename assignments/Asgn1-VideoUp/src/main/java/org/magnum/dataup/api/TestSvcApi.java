package org.magnum.dataup.api;


import org.magnum.dataup.model.VideoStatus;
import retrofit.http.*;
import retrofit.mime.TypedFile;

import java.util.List;

public interface TestSvcApi {

    @GET("/test/{user}/repos")
    List<String> listRepos(@Path("user") String user);

    @Multipart
    @POST("/test/{id}/data")
    public String upload(@Path("id") long id, @Part("file") TypedFile file);

}
