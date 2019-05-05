package shenzhen.teamway.model;

/**
 * @program: airportserver
 * @description:
 * @author: Zhao Hong Ning
 * @create: 2019-05-05 09:46
 **/
public class Device {
    private double wendu;
    private double shidu;
    private long time;
    private boolean online;
    private boolean update;
    private String ip;

    @Override
    public String toString() {
        return "Device{" +
                "wendu=" + wendu +
                ", shidu=" + shidu +
                ", time=" + time +
                ", online=" + online +
                ", update=" + update +
                ", ip='" + ip + '\'' +
                '}';
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}