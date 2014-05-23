package exec.common;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SmsDecoder  extends CumulativeProtocolDecoder {
	
	private final static Logger log = LoggerFactory.getLogger(SmsDecoder.class);

	private final Charset charset;
	
	public SmsDecoder(Charset charset) { 
		this.charset = charset; 
	} 
	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		CharsetDecoder cd = charset.newDecoder();
		int position = in.position();
		int remaining = in.remaining();
		
		in.order(ByteOrder.LITTLE_ENDIAN);
		int packetLength = in.getInt();
		if ((remaining - 4) < packetLength) {
			in.position(position);
			return false; //接收新数据，以拼凑成完整数据
		}
		byte[] dst = new byte[packetLength];
	    in.get(dst);
	    IoBuffer buf = IoBuffer.wrap(dst);
	    buf.order(ByteOrder.LITTLE_ENDIAN);
	    int proto = buf.getInt();
		String className = buf.getString(64, cd);
		try {
			if (className != null && className.trim().length() > 0) {
				ISmsObject sms = (ISmsObject)Class.forName(className).newInstance();
				sms.setProto(proto);
				sms.setSession(session);
				sms.decode(buf, cd);
				out.write(sms);
			} else {
				log.error("解包出错，协议类名为空[" + proto + "]:" + className);
			}
		} catch(Exception e) {
			log.error("实例化协议类出错[" + proto + "]:" + className);
			e.printStackTrace();
		}
		if (in.remaining() > 0) {
			return true; //如果读取内容后还粘了包，就让父类再给 一次，进行下一次解析
		}
		return false;
	}
}
