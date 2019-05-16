package shenzhen.teamway.otherperson;

import io.netty.util.internal.ConcurrentSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shenzhen.teamway.airportserver.NettyServer;
import shenzhen.teamway.airportserver.NettyServerClientHandler;
import shenzhen.teamway.model.Device;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: airport
 * @description:
 * @author: Zhao Hong Ning
 * @create: 2019-05-05 10:03
 **/
public class AirPort {
    private Logger log = LoggerFactory.getLogger(AirPort.class);
    private ConcurrentHashMap<String, Device> map = new ConcurrentHashMap<>();
    private long currentTime = System.currentTimeMillis();
    private final ExecutorService single = Executors.newSingleThreadExecutor();
    private static final long time = 1000 * 60 * 3;

    public void setListenPort(int port) {
        NettyServer.getInstance().startServerPort(port);
        NettyServerClientHandler.getInstance().initMap(map);
    }

    public void startRun() {
        Runnable runnable = () -> NettyServer.getInstance().startServer();
        single.execute(runnable);
        single.shutdown();
    }

    public void setDeviceList(String deviceList) {
        log.info("setDeviceList方法被调用");
        final String[] ss = deviceList.split(";");
        for (String s : ss) {
            final Device d = new Device();
            if (map.containsKey(s)) {
                continue;
            }
            d.setIp(s);
            d.setOnline(false);
            d.setShidu(0.0);
            d.setWendu(0.0);
            d.setUpdate(false);
            d.setTime(System.currentTimeMillis());
            map.put(s, d);
        }
        log.info("setDeviceList方法调用结束");
    }

    public String getAllDeviceValue() {
        log.info("getAllDeviceValue方法被调用");
        final StringBuilder sb = new StringBuilder();
        for (Device value : map.values()) {
            sb.append(value.getIp()).append(",").append(value.getWendu()).append(",").append(value.getShidu()).append("\n");
        }
        log.info("getAllDeviceValue方法调用结束");
        log.info("getAllDeviceValue方法返回值："+sb.toString());
        return sb.toString();
    }

    public String getDeviceValue() {
        log.info("getDeviceValue方法被调用");
        final StringBuilder sb = new StringBuilder();
        for (Device value : map.values()) {
            if (value.getTime() >= currentTime) {
                sb.append(value.getIp()).append(",").append(value.getWendu()).append(",").append(value.getShidu()).append("\n");
            }
        }
        currentTime = System.currentTimeMillis();
        log.info("getDeviceValue方法调用结束");
        log.info("getDeviceValue方法的返回值："+sb.toString());
        return sb.toString();
    }

    public String getOfflineDeviceList() {
        log.info("getOfflineDeviceList方法被调用");
        final StringBuilder sb = new StringBuilder();
        final long l = System.currentTimeMillis();
        for (Device value : map.values()) {
            if (l - value.getTime() > time || !value.isOnline()) {
                sb.append(value.getIp()).append(",");
            }
        }
        log.info("getOfflineDeviceList方法调用结束");
        log.info("调用getOfflineDeviceList方法的返回值是："+sb.toString());
        return sb.toString();
    }
}