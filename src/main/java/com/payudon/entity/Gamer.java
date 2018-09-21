
package com.payudon.entity;

import lombok.Data;

/** 
* @ClassName: Gamer 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月20日 下午5:51:17 
*  
*/
@Data
public class Gamer {
	private Player player1;
	private Player player2;
	private int count=0;//记录局数
	private int sum = 0;
	public Player getOpponent(Player player) {
		if(player.equals(player1)) {
			return player2;
		}else if(player.equals(player2)) {
			return player1;
		}
		return null;
	}
	public Gamer(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}
	public synchronized void updateGame(Player player) {
		if(player1.equals(player)) {//更新信息
			if(!player.isPlay()) {
				player2.setPlay(true);
			}
			this.player1 = player;
		}else {
			if(!player.isPlay()) {
				player1.setPlay(true);
			}
			this.player2 = player;
		}
		if(isBegin()) {
			init();
		}
	}
	public void init() {//初始化
		if(count%2==0) {
			player1.setFirst(true);
			player2.setFirst(false);
			player1.setPlay(true);
			player2.setPlay(false);
		}else {
			player1.setFirst(false);
			player2.setFirst(true);
			player1.setPlay(false);
			player2.setPlay(true);
		}
	}
	public boolean isBegin() {
		sum++;
		if(sum>1) {
			return false;
		}
		return player1.getPoint()==null&&player2.getPoint()==null;
	}
	
}
