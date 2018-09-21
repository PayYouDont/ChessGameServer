/**   
* @Title: ServerHandler.java 
* @Package com.payudon.config 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年9月20日 下午4:33:55 
*/
package com.payudon.config;

import com.payudon.entity.Gamer;
import com.payudon.entity.Player;
import com.payudon.util.JsonUtil;
import com.payudon.util.MatchUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/** 
* @ClassName: ServerHandler 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月20日 下午4:33:55 
*  
*/
public class ServerHandler extends SimpleChannelInboundHandler<Object>{

	/** 
	 * <p>Title: messageReceived</p> 
	 * <p>Description: </p> 
	 * @param ctx
	 * @param msg
	 * @throws Exception 
	 * @see io.netty.channel.SimpleChannelInboundHandler#messageReceived(io.netty.channel.ChannelHandlerContext, java.lang.Object) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月20日 下午4:41:32
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
		Gamer gamer = getGamer(msg);
        ctx.channel().writeAndFlush(JsonUtil.toJson(gamer));
        ctx.close();
	}
	public Gamer getGamer(Object msg) throws InterruptedException {
		Player player = JsonUtil.parseObject(msg.toString(), Player.class);
		if(!MatchUtil.checkExist(player)) {
			MatchUtil.add(player);
		}
		Gamer gamer = MatchUtil.getGamer(player);
		while(gamer==null) {
			Thread.sleep(1000);
			gamer = MatchUtil.getGamer(player);
		}
		if(gamer.isBegin()) {
			return gamer;
		}
		if(player.isWin()) {
			MatchUtil.remove(gamer);
		}
		while(!player.isPlay()) {
			Thread.sleep(1000);
		}
		return gamer;
	}
	@Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("新连接加入"+ctx);
    }
}
