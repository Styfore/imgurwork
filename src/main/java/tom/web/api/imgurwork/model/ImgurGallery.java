package tom.web.api.imgurwork.model;

public class ImgurGallery {

	public class Data {
		private String id;
		private boolean is_album;


		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public boolean isIs_album() {
			return is_album;
		}

		public void setIs_album(boolean is_album) {
			this.is_album = is_album;
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
		return "Gallery [data=" + data + ", success=" + success + ", status=" + status + "]";
	}
}
