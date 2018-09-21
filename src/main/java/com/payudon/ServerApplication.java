package com.payudon;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.payudon.config.NettyServer;

import io.netty.channel.ChannelFuture;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner{
	@Value("${netty.port}")
    private int port;

    @Value("${netty.url}")
    private String url;

    @Autowired
    private NettyServer socketServer;
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		InetSocketAddress address = new InetSocketAddress(url, port);
        ChannelFuture future = socketServer.run(address);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                socketServer.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
	}
}
