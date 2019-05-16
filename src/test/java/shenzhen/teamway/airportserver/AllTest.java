package shenzhen.teamway.airportserver;

import org.junit.Test;
import shenzhen.teamway.model.Device;
import shenzhen.teamway.otherperson.AirPort;
import shenzhen.teamway.utils.OtherUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AllTest {

@Test
public void testSleep(){
    System.out.println("开始");
    System.out.println("结束");
}
@Test
public void testList(){
    List<Long> list = new ArrayList<>(1);
    list.add(System.currentTimeMillis());
    list.add(System.currentTimeMillis());
    list.add(System.currentTimeMillis());
    System.out.println(list.size());
}
    @Test
    public void getAllDeviceValueTest() {
        ConcurrentHashMap<String, Device> map = new ConcurrentHashMap<>();
        final Device d = new Device();
        final Device dd = new Device();
        d.setIp("11");
        d.setWendu(0.0);
        d.setShidu(1.0);
        dd.setIp("22");
        dd.setWendu(9.0);
        dd.setShidu(8.0);
        map.put("11", d);
        map.put("22", dd);
    }

    @Test
    public void isUpdateTest() {
        final Device d1 = new Device();
        final Device d2 = new Device();
        d1.setWendu(13.9);
        d2.setWendu(13.9);
        d1.setShidu(24.5);
        d2.setShidu(24.5);
        final boolean update = OtherUtils.isUpdate(d1, d2);
        System.out.println(update);
    }

    @Test
    public void startServer() {
        new NettyServer().startServer();
    }
}