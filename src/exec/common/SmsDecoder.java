package exec.common;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
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
		in.order(ByteOrder.LITTLE_ENDIAN);
		CharsetDecoder cd = charset.newDecoder();
		int packetLength = in.getInt();
		if (packetLength > 4) {
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
		}
		return true;
	}
}
