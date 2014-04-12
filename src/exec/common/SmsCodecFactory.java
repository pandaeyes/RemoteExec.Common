package exec.common;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class SmsCodecFactory implements ProtocolCodecFactory {

	private final SmsEncoder encoder; 
	 
	private final SmsDecoder decoder; 
	
	public SmsCodecFactory() {
		this(Charset.defaultCharset());
	}
	
	public SmsCodecFactory(Charset charSet) {
		this.encoder = new SmsEncoder(charSet);
		this.decoder = new SmsDecoder(charSet);
	}
	
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

}
