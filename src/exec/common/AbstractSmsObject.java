package exec.common;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public abstract class AbstractSmsObject implements ISmsObject{
	
	public int proto = 0;
	protected IoSession session = null;
	
	@Override
	public int getProto() {
		return proto;
	}

	@Override
	public void setProto(int proto) {
		this.proto = proto;
	}

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	@Override
	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
	}

	@Override
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
	}
	
	public void putString(String val, int size, IoBuffer buffer, CharsetEncoder ce) throws Exception {
		buffer.putString(val, size, ce);
	}
	
	public void putString(String val, IoBuffer buffer, CharsetEncoder ce) throws Exception {
		buffer.putString(val, 63, ce);
	}
	
	public String getString(int size, IoBuffer in, CharsetDecoder cd) throws Exception {
		return in.getString(size, cd);
	}
	
	public String getString(IoBuffer in, CharsetDecoder cd) throws Exception {
		int size = 63;
		return in.getString(size, cd);
	}
	
	public void putInt(int val, IoBuffer buffer) {
		buffer.putInt(val);
	}
	
	public int getInt(IoBuffer in) {
		return in.getInt();
	}

}
