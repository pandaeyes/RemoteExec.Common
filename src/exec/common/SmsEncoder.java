package exec.common;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import exec.proto.SmsObjectS102;

public class SmsEncoder extends ProtocolEncoderAdapter {

	private final Charset charset; 
	
	public SmsEncoder(Charset charset) { 
		this.charset = charset; 
	}
	
	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		ISmsObject sms = (ISmsObject)message;
		CharsetEncoder ce = charset.newEncoder();
		IoBuffer buffer = IoBuffer.allocate(16).setAutoExpand(true);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.putInt(sms.getProto());
		buffer.putString(sms.getClass().getName(), 64, ce);
		sms.encode(buffer, ce);
		buffer.flip();
		
		int packetLength = buffer.limit();
		IoBuffer buf = IoBuffer.allocate(packetLength + 4).setAutoExpand(true);
		buf.order(ByteOrder.LITTLE_ENDIAN);
		buf.putInt(packetLength);
		buf.put(buffer);
		buf.flip();
		out.write(buf); 
	}
}
