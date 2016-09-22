package tom.web.api.imgurwork.model;

public class ImgurImage {

    public class Data {
	private String id;
	private String link;
	private String title;
	private String type;
	private long size;
	
	public String getType() {
	    return type;
	}
	
	public void setType(String type) {
	    this.type = type;
	}
	
	public String getId() {
	    return id;
	}
	public String getLink() {
	    return link;
	}

	public void setId(String id) {
	    this.id = id;
	}

	@Override
	public String toString() {
	    return "Data [id=" + id + ", link=" + link + ", title=" + title + "]";
	}
	public void setLink(String link) {
	    this.link = link;
	}
	
	public void setTitle(String title) {
	    this.title = title;
	}
	
	public String getTitle() {
	    return title;
	}
	
	public long getSize() {
	    return size;
	}
	public void setSize(long size) {
	    this.size = size;
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
