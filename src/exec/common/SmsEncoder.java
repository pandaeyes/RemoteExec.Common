package exec.common;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class SmsEncoder extends ProtocolEncoderAdapter {

	private final Charset charset; 
	
	public SmsEncoder(Charset charset) { 
		this.charset = charset; 
	}
	
	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		ISmsObject sms = (ISmsObject)message;
		CharsetEncoder ce = charset.newEncoder();
		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
		buffer.putInt(sms.getProto());
		sms.encode(buffer, ce);
		buffer.flip();
		out.write(buffer); 
	}
}
