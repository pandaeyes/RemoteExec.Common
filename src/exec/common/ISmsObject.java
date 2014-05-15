package exec.common;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public interface ISmsObject {
	
	public int getProto();
	
	public void setProto(int proto);
	
	public IoSession getSession();
	
	public void setSession(IoSession session);
	
	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception;
	
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception;
}
