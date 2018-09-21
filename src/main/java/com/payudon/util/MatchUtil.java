
package com.payudon.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.payudon.entity.Gamer;
import com.payudon.entity.Player;

/** 
* @ClassName: QueueUtil 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月20日 下午5:37:17 
*  
*/
public class MatchUtil {
	public static Queue<Player> players = new LinkedList<>();
	public static List<Gamer> gamers = new ArrayList<>();
	public static void add(Player player) {
		players.offer(player);
		match();
	}
	public static void match() {
		if(players.size()<2) {
			return;
		}
		Gamer gamer = new Gamer(players.poll(),players.poll());
		gamers.add(gamer);
	}
	public static Gamer getGamer(Player player) {
		for (Gamer gamer : gamers) {
			Player p1 = gamer.getPlayer1();
			Player p2 = gamer.getPlayer2();
			if(p1.equals(player)||p2.equals(player)) {
				gamer.updateGame(player);
				return gamer;
			}
		}
		return null;
	}
	public static boolean checkExist(Player player) {
		for (Gamer gamer : gamers) {
			Player p1 = gamer.getPlayer1();
			Player p2 = gamer.getPlayer2();
			if(p1.equals(player)||p2.equals(player)) {
				return true;
			}
		}
		return false;
	}
	public static void remove(Gamer gamer) {
		gamers.remove(gamer);
	}
}
