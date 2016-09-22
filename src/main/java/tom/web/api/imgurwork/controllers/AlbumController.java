package tom.web.api.imgurwork.controllers;

import java.io.ByteArrayInputStream;
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

import tom.web.api.imgurwork.model.ImgurAlbum;
import tom.web.api.imgurwork.model.ImgurImage;
import tom.web.api.imgurwork.services.ControllerUtilsService;
import tom.web.api.imgurwork.services.ImgurServicesProvider;
import tom.web.api.imgurwork.utils.ContentType;

@RestController
@RequestMapping("/album")
public class AlbumController {
  
    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private ImgurServicesProvider imgurServicesProvider;
    
    @Autowired
    private ControllerUtilsService controllerUtilsService;

    @RequestMapping(path="/{id}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> album(@PathVariable(value="id") String id) throws IOException{
	InputStream in;
	String contentType;

	ImgurAlbum imgurAlbum =  imgurServicesProvider.getAlbum(id);
	if (imgurAlbum != null && imgurAlbum.getData() != null){
	    int imageCount = imgurAlbum.getData().getImages_count();
	    switch (imageCount) {
	    case 0:
		contentType = ContentType.IMAGE_PNG;
		in = controllerUtilsService.getInputStreamTotoImage(id);
		break;
	    case 1:
		LOGGER.info("Only one image in album with id {}, the image will be show", id);
		ImgurImage.Data imgurImageData = imgurAlbum.getData().getImages().get(0);
		contentType = imgurImageData.getType();
		in = controllerUtilsService.getInputStreamFromImgurImageData(imgurImageData);
		break;
	    default:
		LOGGER.info("Many many (many ... or {}) images in the album with id {}, links are one the way", imageCount, id);
		contentType = ContentType.TEXT_HTML;
		StringBuilder sb = new StringBuilder();
		for (ImgurImage.Data imageData : imgurAlbum.getData().getImages()) {
		    String imageId = imageData.getId();
		    String title = imageData.getTitle()!=null?imageData.getTitle():"No title";
		    String description = imageData.getDescription()!=null?imageData.getDescription():"No description";
		    
		    sb.append("<a href='/image/").append(imageId).append("'><strong>")
		    	.append(imageId).append(" - </strong>").append(title).append(" : <em>").append(description)
		    .append("</em></a><br>");
		}
		
		in = new ByteArrayInputStream(sb.toString().getBytes());
		break;
		
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
