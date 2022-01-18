package controller;

import java.util.*;
import Model.PlayerDAO;
import Model.PlayerDTO;
import View.View;

public class PlayerController {
	// 모델 - 뷰를 왔다갔다해야함
	// 여기있는 메서드를 실행
	// 여기서 실행되는 메서드는 모델에 영향을 줌
	private PlayerDAO playerDao;
	private View view;

	public PlayerController(PlayerDAO playerDao, View view) {
		this.playerDao = playerDao;
		this.view = view;
	}

	// 등록이 정상적으로 완료되면 true를 리턴받을것임.
	public boolean playerEnroll(String playerName, int playerAbility, String userId) {
		Random rand = new Random();
		playerAbility = rand.nextInt(100) + 1; // 능력치 랜덤
		return playerDao.enrollPlayer(playerName, playerAbility, userId);
	}

	// 모든 타자리스트를 가져옵니다.
	public ArrayList<PlayerDTO> getAllHitterList(String userId) {
		return playerDao.selectAllHitter(userId);
	}

	// 선택한 타자리스트를 가져옵니다.
	public PlayerDTO getOneHitterList(String playerName) {
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
}
