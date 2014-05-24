package exec.proto;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;

import exec.common.AbstractSmsObject;

/**
 * 重载结果
 */
public class SmsObjectS105 extends AbstractSmsObject {

	private int succ = 0; // 0:失败 1:成功
	
	public int getSucc() {
		return succ;
	}

	public void setSucc(int succ) {
		this.succ = succ;
	}

	public SmsObjectS105() {
		setProto(105);
	}
	
	@Override
	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
		putInt(succ, buffer);
	}

	@Override
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
		succ = getInt(in);
	}
}
