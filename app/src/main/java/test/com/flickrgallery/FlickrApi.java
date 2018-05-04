package test.com.flickrgallery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.com.flickrgallery.Model.Response;

public interface FlickrApi {

    String SERVICES_REST = "/services/rest";
    String METHOD = "method";
    String API_KEY = "api_key";
    String FORMAT = "format";
    String NO_JSON_CALLBACK = "nojsoncallback";
    String EXTRAS = "extras";
    String TEXT = "text";

    @GET(SERVICES_REST)
    Call<Response> getData(@Query(METHOD) String method,
                           @Query(API_KEY) String key,
                           @Query(FORMAT) String formatResponse,
                           @Query(NO_JSON_CALLBACK) int callback,
                           @Query(EXTRAS) String imageType);

    @GET(SERVICES_REST)
    Call<Response> searchData(@Query(METHOD) String method,
                              @Query(API_KEY) String key,
                              @Query(FORMAT) String formatResponse,
                              @Query(NO_JSON_CALLBACK) int callback,
                              @Query(EXTRAS) String imageType,
                              @Query(TEXT) String query);
}
