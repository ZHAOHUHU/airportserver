package shenzhen.teamway.otherperson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shenzhen.teamway.airportserver.NettyServer;
import shenzhen.teamway.airportserver.NettyServerClientHandler;
import shenzhen.teamway.model.Device;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: airport
 * @description:
 * @author: Zhao Hong Ning
 * @create: 2019-05-05 10:03
 **/
public class AirPort {
    private Logger log = LoggerFactory.getLogger(AirPort.class);
    private ConcurrentHashMap<String, Device> map = new ConcurrentHashMap<>();

    public void setListenPort(int port) {
        NettyServer.getInstance().startServerPort(port);
        NettyServerClientHandler.getInstance().initMap(map);
    }

    public void startRun() {
        NettyServer.getInstance().startServer();
    }

    public void setDeviceList(String deviceList) {
        final String[] ss = deviceList.split(";");
        for (String s : ss) {
            final Device d = new Device();
            log.info("设备的IP是:" + s);
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
    }

    public String getAllDeviceValue() {
        final StringBuilder sb = new StringBuilder();
        //IP1,温度值,湿度值\n IP2,温度值,湿度值
        for (Device value : map.values()) {
            sb.append(value.getIp()).append(",").append(value.getWendu()).append(",").append(value.getShidu()).append("\n");
        }
        return sb.toString();
    }

    public String getDeviceValue() {
        final StringBuilder sb = new StringBuilder();
        for (Device value : map.values()) {
            if (value.isUpdate()) {
                sb.append(value.getIp()).append(",").append(value.getWendu()).append(",").append(value.getShidu()).append("\n");
            }
        }
        return sb.toString();
    }

    public String getOfflineDeviceList() {
        final StringBuilder sb = new StringBuilder();
        for (Device value : map.values()) {
            if (!value.isOnline()) {
                sb.append(value.getIp()).append(",").append(value.getWendu()).append(",").append(value.getShidu()).append("\n");
            }
        }
        return sb.toString();
    }


}