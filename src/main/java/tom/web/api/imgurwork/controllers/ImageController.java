package tom.web.api.imgurwork.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tom.web.api.imgurwork.model.ImgurImage;
import tom.web.api.imgurwork.services.ControllerUtilsService;
import tom.web.api.imgurwork.services.ImgurServicesProvider;
import tom.web.api.imgurwork.utils.ContentType;

@RestController
@RequestMapping("/image")
public class ImageController {
  
    @Autowired
    private ImgurServicesProvider imgurServicesProvider;

    @Autowired
    private ControllerUtilsService controllerUtilsService;

    @RequestMapping(path="/{id}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> image(@PathVariable(value="id") String id) throws IOException{
	ImgurImage imgurImage = imgurServicesProvider.getImage(id);

	InputStream in;
	String contentType;
	if (imgurImage != null && imgurImage.getData() != null && imgurImage.getData().getLink() != null){
	    contentType = imgurImage.getData().getType();
	    in = controllerUtilsService.getInputStreamFromImgurImageData(imgurImage.getData());
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
