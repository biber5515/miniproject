package Model;

public class PlayerDTO {
	
	private String playerName;
	private int playerAbility;
	private String ID;
	
	public PlayerDTO() {
		
	}
	
	public PlayerDTO(String playerName, int playerAbility) {
		this.playerName = playerName;
		this.playerAbility = playerAbility;
	}
	
	public PlayerDTO (String playerName, int playerAbility , String ID) {
		this.playerName = playerName;
		this.playerAbility = playerAbility;
		this.ID=ID;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getPlayerAbility() {
		return playerAbility;
	}
	public void setPlayerAbility(int playerAbility) {
		this.playerAbility = playerAbility;
	}
	
	

}
