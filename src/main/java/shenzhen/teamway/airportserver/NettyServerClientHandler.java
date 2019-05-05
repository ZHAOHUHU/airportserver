package shenzhen.teamway.airportserver;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shenzhen.teamway.model.Device;
import shenzhen.teamway.model.wenshiduDevice;
import shenzhen.teamway.utils.OtherUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: airportserver
 * @description:
 * @author: Zhao Hong Ning
 * @create: 2019-04-28 17:29
 **/
@ChannelHandler.Sharable
public class NettyServerClientHandler extends SimpleChannelInboundHandler<wenshiduDevice> {
    private static final long time = 1000 * 60 * 3;
    private ConcurrentHashMap<String, Device> map;
    private Logger log = LoggerFactory.getLogger(NettyServerClientHandler.class);
    private static NettyServerClientHandler handler;

    public static synchronized NettyServerClientHandler getInstance() {
        if (handler == null) {
            handler = new NettyServerClientHandler();
        }
        return handler;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接建立成功");
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, wenshiduDevice m) throws Exception {
        log.info(m.toString());
        updateDeviceState(m);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
        log.error("连接关闭");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("连接捕捉到异常：" + cause.toString());

    }

    public void initMap(ConcurrentHashMap<String, Device> map) {
        this.map = map;
    }

    private void updateDeviceState(wenshiduDevice m) {
        if (map.containsKey(m.getIp())) {
            final Device device = map.get(m.getIp());
            if (System.currentTimeMillis() - device.getTime() < time) {
                final Device temp = new Device();
                temp.setWendu(m.getWendu());
                temp.setShidu(m.getShidu());
                if (OtherUtils.isUpdate(device, temp)) {
                    device.setShidu(m.getShidu());
                    device.setWendu(m.getWendu());
                    device.setTime(System.currentTimeMillis());
                    device.setOnline(true);
                    device.setUpdate(true);
                } else {
                    //值无变化
                    device.setUpdate(false);
                }
            } else {
                device.setShidu(m.getShidu());
                device.setWendu(m.getWendu());
                device.setTime(System.currentTimeMillis());
                //设备超过了三分钟
                device.setOnline(false);
            }
        } else {
            final Device d = new Device();
            d.setWendu(m.getWendu());
            d.setShidu(m.getShidu());
            d.setIp(m.getIp());
            d.setTime(System.currentTimeMillis());
            d.setOnline(true);
            map.put(m.getIp(), d);
        }
    }
}