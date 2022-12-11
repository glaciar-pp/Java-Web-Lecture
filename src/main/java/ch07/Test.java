package ch07;

import java.util.List;

public class Test {

public static void main(String[] args) {
		PlayerDAO dao = new PlayerDAO();

		List<Player> list = dao.getPlayers();
		for (Player p : list)
			System.out.println(p);
	}
}