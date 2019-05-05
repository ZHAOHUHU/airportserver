package shenzhen.teamway.code;

import shenzhen.teamway.utils.OtherUtils;

import java.util.Arrays;

/**
 * @program: airportserver
 * @description:
 * @author: Zhao Hong Ning
 * @create: 2019-04-28 18:22
 **/
public class MyMessage {
    //   private byte[] head = {OtherUtils.hexToByte("00fe"), OtherUtils.hexToByte("00dc")};
    private short head = -292;
    private byte version = 1;
    private byte[] deviceId = new byte[6];
    private int sessionId;
    private byte commond;
    private char bodyLength;
    private byte[] body;
    private byte check = 0;



    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte[] getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(byte[] deviceId) {
        this.deviceId = deviceId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public byte getCommond() {
        return commond;
    }

    public void setCommond(byte commond) {
        this.commond = commond;
    }

    public char getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(char bodyLength) {
        this.bodyLength = bodyLength;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public byte getCheck() {
        return check;
    }

    public void setCheck(byte check) {
        this.check = check;
    }


    @Override
    public String toString() {
        return "MyMessage{" +
                "head=" + head +
                ", version=" + version +
                ", deviceId=" + Arrays.toString(deviceId) +
                ", sessionId=" + sessionId +
                ", commond=" + commond +
                ", bodyLength=" + bodyLength +
                ", body=" + Arrays.toString(body) +
                ", check=" + check +
                '}';
    }
}