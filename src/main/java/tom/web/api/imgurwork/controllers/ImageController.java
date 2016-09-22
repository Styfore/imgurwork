package tom.web.api.imgurwork.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

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

import tom.web.api.imgurwork.model.ImgurImage;
import tom.web.api.imgurwork.services.ImgurServicesProvider;

@RestController
@RequestMapping("/image")
public class ImageController {
  
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImgurServicesProvider imgurServicesProvider;


    @RequestMapping(path="/{id}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> image(@PathVariable(value="id") String id) throws IOException{
	ImgurImage imgurImage = imgurServicesProvider.getImage(id);

	InputStream in;
	String contentType;
	if (imgurImage != null && imgurImage.getData() != null && imgurImage.getData().getLink() != null){
	    URL url = new URL(imgurImage.getData().getLink());
	    contentType = imgurImage.getData().getType();
	    in = url.openStream();
	    LOGGER.info("Success to get imgur image with id {}", id);
	}
	else{
	    contentType = "image/png";
	    in = new FileInputStream(Paths.get("src/main/resources/public/assets/images/arg.png").toFile());
	    LOGGER.info("Fail to get imgur image with id {}, arg image is on the way", id);
	}

	return ResponseEntity
		.ok()
		.contentType(MediaType.parseMediaTypes(contentType).get(0))
		.body(new InputStreamResource(in));
    }


}
