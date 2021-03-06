package tom.web.api.imgurwork.services;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tom.web.api.imgurwork.model.ImgurAlbum;
import tom.web.api.imgurwork.model.ImgurGallery;
import tom.web.api.imgurwork.model.ImgurImage;

public class ImgurServicesProvider implements InitializingBean{
    private static final Logger LOGGER = LoggerFactory.getLogger(ImgurServicesProvider.class);
    
    private String clientId;
    private String baseUrl;
    private String header;
    
    private Retrofit retrofit;
    private ImgurService imgurService;
    
    public ImgurImage getImage(String id){
	 ImgurImage imgurImage = null;
	try {
	    LOGGER.info("Try to get imgur image with id {}", id);
	    Response<ImgurImage> response = imgurService.createImage(header, id).execute();
	    if (response != null && response.isSuccessful() && response.body().isSuccess()){
		imgurImage = response.body();
	    }
	} catch (IOException e) {
	    LOGGER.error(e.getMessage(), e);
	}
	
	return imgurImage;
    }
    
    public ImgurAlbum getAlbum(String id){
	ImgurAlbum imgurAlbum = null;
	try {
	    LOGGER.info("Try to get imgur album with id {}", id);
	    Response<ImgurAlbum> response = imgurService.createAlbum(header, id).execute();
	    if (response != null && response.isSuccessful() && response.body().isSuccess()){
		imgurAlbum = response.body();
	    }
	} catch (IOException e) {
	    LOGGER.error(e.getMessage(), e);
	}
	
	return imgurAlbum;
   }
    
    public ImgurGallery getGallery(String id){
    	ImgurGallery imgurGallery = null;
    	try {
    	    LOGGER.info("Try to get imgur gallery with id {}", id);
    	    Response<ImgurGallery> response = imgurService.createGallery(header, id).execute();
    	    if (response != null && response.isSuccessful() && response.body().isSuccess()){
    	    	imgurGallery = response.body();
    	    }
    	} catch (IOException e) {
    	    LOGGER.error(e.getMessage(), e);
    	}
    	
    	return imgurGallery;
       }
    
    public void setBaseUrl(String baseUrl) {
	this.baseUrl = baseUrl;
    }
    
    public void setClientId(String clientId) {
	this.clientId = clientId;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
	header = String.format("Client-ID %s", clientId);
	
	retrofit = new Retrofit.Builder()
		.baseUrl(baseUrl)
		.addConverterFactory(GsonConverterFactory.create())
		.build();
	
	imgurService = retrofit.create(ImgurService.class);
    }
    
}
