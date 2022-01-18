package controller;

import java.util.*;
import Model.PlayerDAO;
import Model.PlayerDTO;
import View.View;

public class PlayerController {
	// �� - �並 �Դٰ����ؾ���
	// �����ִ� �޼��带 ����
	// ���⼭ ����Ǵ� �޼���� �𵨿� ������ ��
	private PlayerDAO playerDao;
	private View view;

	public PlayerController(PlayerDAO playerDao, View view) {
		this.playerDao = playerDao;
		this.view = view;
	}

	// ����� ���������� �Ϸ�Ǹ� true�� ���Ϲ�������.
	public boolean playerEnroll(String playerName, int playerAbility, String userId) {
		Random rand = new Random();
		playerAbility = rand.nextInt(100) + 1; // �ɷ�ġ ����
		return playerDao.enrollPlayer(playerName, playerAbility, userId);
	}

	// ��� Ÿ�ڸ���Ʈ�� �����ɴϴ�.
	public ArrayList<PlayerDTO> getAllHitterList(String userId) {
		return playerDao.selectAllHitter(userId);
	}

	// ������ Ÿ�ڸ���Ʈ�� �����ɴϴ�.
	public PlayerDTO getOneHitterList(String playerName) {
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
}
