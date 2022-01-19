package Model;

public class Music_VO {
	private String title;
	private String path;
	public Music_VO(String title, String path) {
		super();
		this.title = title;
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	

}
