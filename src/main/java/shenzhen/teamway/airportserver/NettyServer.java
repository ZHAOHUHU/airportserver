package shenzhen.teamway.airportserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shenzhen.teamway.code.MessageDecode;
import shenzhen.teamway.otherperson.AirPort;

/**
 * @program: airport
 * @description:
 * @author: Zhao Hong Ning
 * @create: 2019-04-28 17:15
 **/
public class NettyServer {
    private Logger log = LoggerFactory.getLogger(NettyServer.class);
    private static NettyServer nettyServer;
    private int serverPort;

    public static synchronized NettyServer getInstance() {
        if (nettyServer == null) {
            nettyServer = new NettyServer();
        }
        return nettyServer;
    }

    private boolean bind(int port) throws InterruptedException {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        //通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        //保持长连接状态
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        bootstrap.childHandler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {
            @Override
            protected void initChannel(io.netty.channel.socket.SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast();
                ChannelPipeline p = socketChannel.pipeline();
                p.addLast(new MessageDecode());
                p.addLast( NettyServerClientHandler.getInstance());
            }
        });
        ChannelFuture f = bootstrap.bind(port).sync();
        if (f.isSuccess()) {
            log.info("服务启动，侦听端口: " + port);
        }
        return true;
    }

    public void startServerPort(int port) {
        serverPort = port;
    }

    public void startServer() {
        try {
            bind(serverPort);
        } catch (InterruptedException e) {
            log.error("启动网网络服务错误，程序退出。");
            return;
        }
    }

    public static void main(String[] args) {
        final AirPort port = new AirPort();
     port.setListenPort(8888);
     port.startRun();
    }

}