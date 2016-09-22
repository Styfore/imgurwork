package tom.web.api.imgurwork.model;

import java.util.List;

public class ImgurAlbum {

    public class Data {
	private String id;
	private String title;
	private int images_count;
	
	private List<ImgurImage.Data> images;

	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	}

	public String getTitle() {
	    return title;
	}

	public void setTitle(String title) {
	    this.title = title;
	}

	public int getImages_count() {
	    return images_count;
	}

	public void setImages_count(int images_count) {
	    this.images_count = images_count;
	}

	public List<ImgurImage.Data> getImages() {
	    return images;
	}

	public void setImages(List<ImgurImage.Data> images) {
	    this.images = images;
	}
	
    }

    private Data data;

    private boolean success;
    private int status;

    public boolean isSuccess() {
	return success;
    }

    public void setSuccess(boolean success) {
	this.success = success;
    }

    public void setStatus(int status) {
	this.status = status;
    }

    public int getStatus() {
	return status;
    }

    public void setData(Data data) {
	this.data = data;
    }

    public Data getData() {
	return data;
    }

    @Override
    public String toString() {
	return "Image [data=" + data + ", success=" + success + ", status=" + status + "]";
    }
}
