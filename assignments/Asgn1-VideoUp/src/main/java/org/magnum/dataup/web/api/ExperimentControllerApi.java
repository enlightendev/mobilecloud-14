package org.magnum.dataup.web.api;


import retrofit.http.*;
import retrofit.mime.TypedFile;

import java.util.List;

public interface ExperimentControllerApi {

    @GET("/test/pathvar/{pvar}")
    String testPathVar(@Path("pvar1") String pvar,
                       @Query("name") String name,
                       @Query("male") Boolean male);

    @GET("/test/{user}/repos")
    List<String> listRepos(@Path("user") String user);

    @Multipart
    @POST("/test/{id}/data")
    public String uploadFile(@Path("id") long id, @Part("file") TypedFile file);

}
