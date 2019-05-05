package shenzhen.teamway.model;

import shenzhen.teamway.code.MyMessage;

import java.io.PipedReader;
import java.io.PrintStream;

/**
 * @program: airportserver
 * @description:
 * @author: Zhao Hong Ning
 * @create: 2019-04-30 15:35
 **/
public class wenshiduDevice extends MyMessage {
    private String ip;
    private double wendu;
    private double shidu;

    @Override
    public String toString() {
        return "wenshiduDevice{" +
                "ip='" + ip + '\'' +
                ", wendu=" + wendu +
                ", shidu=" + shidu +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getWendu() {
        return wendu;
    }

    public void setWendu(double wendu) {
        this.wendu = wendu;
    }

    public double getShidu() {
        return shidu;
    }

    public void setShidu(double shidu) {
        this.shidu = shidu;
    }
}