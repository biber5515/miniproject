package controller;

import java.util.*;
import Model.PlayerDAO;
import Model.PlayerDTO;
import View.View;

public class PlayerController {
	// �� - �並 �Դٰ����ؾ���
	// �����ִ� �޼��带 ����
	// ���⼭ ����Ǵ� �޼���� �𵨿� ������ ��
	
	View view = new View();
	PlayerDAO playerDao;
	
	Scanner sc = new Scanner(System.in);
	Random rand = new Random();

	public PlayerController(PlayerDAO playerDao) {
		this.playerDao = playerDao;
	}

	// ����� ���������� �Ϸ�Ǹ� true�� ���Ϲ�������.
	public boolean playerEnroll(String playerName, int playerAbility, String userId) {
		Random rand = new Random();
		playerAbility = rand.nextInt(100) + 1; // �ɷ�ġ ����
		return playerDao.enrollPlayer(playerName, playerAbility, userId);
	}

	// ��� Ÿ�ڸ���Ʈ�� �����ɴϴ�.
	public ArrayList<PlayerDTO> getAllHitterList(String userId, PlayerDAO playerDao) {
		return playerDao.selectAllHitter(userId);
	}

	// ������ Ÿ�ڸ���Ʈ�� �����ɴϴ�.
	public PlayerDTO getOneHitterList(String playerName, PlayerDAO playerDAO) {
		return playerDao.selectOneHitter(playerName);
	}

	// ���� ����Ʈ�� �̾ƿɴϴ�.
	public PlayerDTO getPitcherList(String userId) {
		// ��� ���� ����Ʈ�� ���� �� ��, �������� �̾ƾ���
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
		System.out.println("�ɷ�ġ >> " + pAbility);
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
