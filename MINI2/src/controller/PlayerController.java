package controller;

import java.util.*;
import Model.PlayerDAO;
import Model.PlayerDTO;
import View.View;

public class PlayerController {
	// 모델 - 뷰를 왔다갔다해야함
	// 여기있는 메서드를 실행
	// 여기서 실행되는 메서드는 모델에 영향을 줌
	
	View view = new View();
	PlayerDAO playerDao;
	
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();

	public PlayerController(PlayerDAO playerDao) {
		this.playerDao = playerDao;
	}

	// 등록이 정상적으로 완료되면 true를 리턴받을것임.
	public boolean playerEnroll(String playerName, int playerAbility, String userId) {
		Random rand = new Random();
		playerAbility = rand.nextInt(100) + 1; // 능력치 랜덤
		return playerDao.enrollPlayer(playerName, playerAbility, userId);
	}

	// 모든 타자리스트를 가져옵니다.
	public ArrayList<PlayerDTO> getAllHitterList(String userId, PlayerDAO playerDao) {
		return playerDao.selectAllHitter(userId);
	}

	// 선택한 타자리스트를 가져옵니다.
	public PlayerDTO getOneHitterList(String playerName, PlayerDAO playerDAO) {
		return playerDao.selectOneHitter(playerName);
	}

	// 투수 리스트를 뽑아옵니다.
	public PlayerDTO getPitcherList(String userId) {
		// 모든 투수 리스트를 가져 온 후, 랜덤으로 뽑아야함
		Random rand = new Random();
		PlayerDTO pickedPlayer;
		ArrayList<PlayerDTO> pitcherList = playerDao.PitcherSelect(userId);
		int randomNumber = rand.nextInt(pitcherList.size());
		pickedPlayer = pitcherList.get(randomNumber);
		return pickedPlayer;
	}
	
	public void enrollPlayers (String id, PlayerDAO playerDao) {
		for (int i = 0; i < 3; i++) {
			String playerName = view.enrollPlayer();
			int pAbility = rand.nextInt(100) + 1;
			boolean isOverlap = playerDao.enrollPlayer(playerName, pAbility, id);
			if (!isOverlap) {
				i = i-1;
				continue;
			}
			view.showAbility(pAbility);
		}
	}
	
	public void enrollBonusPlayer(PlayerDAO playerDao, String id) {
		String playerName = view.enrollPlayer();
		int pAbility = rand.nextInt(100) + 1;
		System.out.println("능력치 >> " + pAbility);
		playerDao.enrollPlayer(playerName, pAbility, id);
	}
	
	public void showHitterList(String id, PlayerDAO playerDao) {
		view.showHitterList();
		ArrayList<PlayerDTO> selectH = playerDao.selectAllHitter(id);
		for (PlayerDTO al : selectH) {
			view.showHitters(al);
		}
	}
	
	public PlayerDTO inputHitters (String id, PlayerDAO playerDao) {
		String setname = view.inputHitterName();
		PlayerDTO dto = playerDao.selectOneHitter(setname);
		return dto;
	}
	
	public void handleStrike(PlayerDTO player, int pitcherAbil, int strike, int score) {
		view.strikeResult(player, pitcherAbil);
		view.showStrike(strike, score);
	}
	
	public void handleSafety(PlayerDTO player, int pitcherAbil, int strike, int score) {
		view.safetyResult(player, pitcherAbil);
		view.showStrike(strike, score);
	}
	
	public void handleHomerun(PlayerDTO player, int pitcherAbil, int strike, int score) {
		view.homerunResult(player, pitcherAbil);
		view.showStrike(strike, score);
	}
}
