package tom.web.api.imgurwork.services;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import tom.web.api.imgurwork.model.ImgurAlbum;
import tom.web.api.imgurwork.model.ImgurGallery;
import tom.web.api.imgurwork.model.ImgurImage;

public interface ImgurService {
    
    @GET("image/{id}")
    Call<ImgurImage> createImage(@Header("Authorization") String authorization, @Path("id") String user);
    
    @GET("album/{id}")
    Call<ImgurAlbum> createAlbum(@Header("Authorization") String authorization, @Path("id") String user);
    
    @GET("gallery/{id}")
    Call<ImgurGallery> createGallery(@Header("Authorization") String authorization, @Path("id") String user);
    
}
