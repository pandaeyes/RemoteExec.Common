package exec.common;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;


public class SmsDecoder  extends CumulativeProtocolDecoder {

	private final Charset charset;
	
	public SmsDecoder(Charset charset) { 
		this.charset = charset; 
	} 
	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		CharsetDecoder cd = charset.newDecoder();
		int proto = in.getInt();
		ISmsObject sms = SmsObjectFactory.newSmsObject(proto);
		sms.decode(in, cd);
		out.write(sms);
		return false;
	}
}
