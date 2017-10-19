package tom.web.api.imgurwork.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tom.web.api.imgurwork.model.ImgurImage;
import tom.web.api.imgurwork.utils.ContentType;

@Service
public class ControllerUtilsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerUtilsService.class);

	public InputStream getInputStreamFromImgurImageData(ImgurImage.Data imgurImageData) throws IOException{
		if (imgurImageData != null){
			if(ContentType.IMAGE_GIF.equalsIgnoreCase(imgurImageData.getType()) && (imgurImageData.getGifv() != null || imgurImageData.getMp4() != null)){
				if(imgurImageData.getMp4() != null){
					LOGGER.info("Success to get imgur image MP4 with id {}", imgurImageData.getId());
					imgurImageData.setType(ContentType.VIDEO_MP4);
					return  getInputStreamFromUri(imgurImageData.getMp4());
				}
				if(imgurImageData.getGifv() != null){
					LOGGER.info("Success to get imgur image MP4 with id {}", imgurImageData.getId());
					imgurImageData.setType(ContentType.VIDEO_MP4);
					return  getInputStreamFromUri(imgurImageData.getGifv());
				}
			}
			if(imgurImageData.getLink() != null){
				LOGGER.info("Success to get imgur image with id {}", imgurImageData.getId());
				return  getInputStreamFromUri(imgurImageData.getLink());
			}
		}
		LOGGER.info("Fail to get imgur image with id {}, arg image is on the way", imgurImageData.getId());
		return new FileInputStream(Paths.get("src/main/resources/public/assets/images/arg.png").toFile());
	}

	public InputStream getInputStreamFromUri(String uri) throws IOException{
		URL url = new URL(uri);
		return  url.openStream();
	}


	public InputStream getInputStreamArgImage(String id) throws FileNotFoundException{
		LOGGER.info("Fail to get imgur image with id {}, arg image is on the way", id);
		return new FileInputStream(Paths.get("src/main/resources/public/assets/images/arg.png").toFile());
	}

	public InputStream getInputStreamTotoImage(String id) throws FileNotFoundException{
		LOGGER.info("No image in album with id {}, toto image is on the way", id);
		return new FileInputStream(Paths.get("src/main/resources/public/assets/images/toto.png").toFile());
	}
}
