package shenzhen.teamway.utils;

import shenzhen.teamway.model.Device;

/**
 * @program: airportserver
 * @description:
 * @author: Zhao Hong Ning
 * @create: 2019-04-28 19:18
 **/
public class OtherUtils {

    public static String trimIp(String ip) {
        final String substring = ip.substring(1);
        final String[] split = substring.split(":");
        return split[0];
    }

    /**
     * @Author: Zhao Hong Ning
     * @Description:d1是之前保存 d2是这次上报
     * @Date: 2019/5/5
     * @param: d1
     * @param: d2
     * @return: boolean
     */
    public static boolean isUpdate(Device d1, Device d2) {
        boolean wendu = false;
        boolean shidu = false;
        if (d2.getWendu() < d1.getWendu() / (1 + 0.02) || d2.getWendu() > d1.getWendu() / (1 - 0.02)) {
            wendu = true;
        }
        if (d2.getShidu() < d1.getShidu() / (1 + 0.02) || d2.getShidu() > d1.getShidu() / (1 - 0.02)) {
            shidu = true;
        }
        if (wendu || shidu) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        final String s = OtherUtils.trimIp("/192.168.12.74:2000");
        System.out.println(s);
    }
}