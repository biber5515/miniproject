package Model;

public class USER_VO {
	private String ID;
	private int PASSWORD;
	private String NAME;
	private int SCORE;
	
	//������
	public USER_VO() {
		
	}
	//�α����� ���� ������
	public USER_VO(String ID, int PASSWORD) {
		this.ID = ID;
		this.PASSWORD = PASSWORD;
		
	}
	//��ũ Ȯ���� ���� ������
	public USER_VO(String ID, String NAME, int SCORE) {
		this.ID = ID;
		this.NAME = NAME;
		this.SCORE=SCORE;
		
	}
	
	public USER_VO(String ID, int PASSWORD, String NAME, int SCORE) {
		super();
		this.ID = ID;
		this.PASSWORD = PASSWORD;
		this.NAME = NAME;
		this.SCORE = SCORE;
	}

	public String getID() {
		return ID;
	}

	public int getPASSWORD() {
		return PASSWORD;
	}

	public String getNAME() {
		return NAME;
	}

	public int getSCORE() {
		return SCORE;
	}
	
	
	
	
	

}
