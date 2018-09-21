
package com.payudon.entity;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/** 
* @ClassName: Player1 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月7日 上午9:36:50 
*  
*/
@Data
public class Player implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer score;
	private boolean isPlay;
	private boolean isFirst;
	private List<Point> chess = new ArrayList<>();
	private Point point;
	private boolean isWin;
	private String nick;
	private String ip;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (nick == null) {
			if (other.nick != null)
				return false;
		} else if (!nick.equals(other.nick))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		return result;
	}
	
	
}
