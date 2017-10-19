package tom.web.api.imgurwork.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tom.web.api.imgurwork.model.ImgurGallery;
import tom.web.api.imgurwork.services.ControllerUtilsService;
import tom.web.api.imgurwork.services.ImgurServicesProvider;
import tom.web.api.imgurwork.utils.ContentType;

@RestController
@RequestMapping({"/gallery", "g"})
public class GalleryController {
  
    private static final Logger LOGGER = LoggerFactory.getLogger(GalleryController.class);

    @Autowired
    private ImgurServicesProvider imgurServicesProvider;
    
    @Autowired
    private ControllerUtilsService controllerUtilsService;

    @Autowired
    private AlbumController albumController;
    
    @Autowired
    private ImageController imageController;
    
    @RequestMapping(path="/{id}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> gallery(@PathVariable(value="id") String id) throws IOException{
	InputStream in;
	String contentType;

	ImgurGallery imgurGallery =  imgurServicesProvider.getGallery(id);
	if (imgurGallery != null && imgurGallery.getData() != null){
		if (imgurGallery.getData().isIs_album()){
			LOGGER.info("Gallery is an album with id {}", id);
			return albumController.album(imgurGallery.getData().getId());
		}
		else{
			LOGGER.info("Gallery is an image with id {}", id);
			return imageController.image(imgurGallery.getData().getId());
		}
	}
	else{
	    contentType = ContentType.IMAGE_PNG;
	    in = controllerUtilsService.getInputStreamArgImage(id);
	}

	return ResponseEntity
		.ok()
		.contentType(MediaType.parseMediaTypes(contentType).get(0))
		.body(new InputStreamResource(in));
    }
    
}
