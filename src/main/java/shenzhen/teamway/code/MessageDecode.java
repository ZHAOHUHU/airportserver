package shenzhen.teamway.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shenzhen.teamway.airportserver.NettyServer;
import shenzhen.teamway.model.wenshiduDevice;
import shenzhen.teamway.utils.OtherUtils;

import java.util.List;

/**
 * @program: airportserver
 * @description:
 * @author: Zhao Hong Ning
 * @create: 2019-04-28 17:23
 **/
public class MessageDecode extends ByteToMessageDecoder {
    final byte[] c = new byte[4];
    final byte[] deviceId = new byte[6];
    private Logger log = LoggerFactory.getLogger(NettyServer.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final wenshiduDevice d = new wenshiduDevice();
        final String ip = ctx.channel().remoteAddress().toString();
        if (in.readableBytes() < 2) {
            return;
        }
        final short i = in.readShort();
        if (i != -292) {
            return;
        }
        in.markReaderIndex();
        in.readByte();
        in.readBytes(deviceId);
        in.readInt();//注意大小端
        in.readByte();
        short totalLength = in.readShort();
        for (int j = 0; j < 4; j++) {
            final short temp = in.readShort();
            if (j == 1) {
                d.setWendu(temp / 10);
            } else if (j == 3) {
                d.setShidu(temp / 10);
            }
        }
        in.readBytes(totalLength - 8);
        in.readByte();
        d.setDeviceId(deviceId);
        d.setIp(OtherUtils.trimIp(ip));
        out.add(d);
    }
}