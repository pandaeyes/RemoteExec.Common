package exec.proto;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;

import exec.common.AbstractSmsObject;

public class SmsObjectS100 extends AbstractSmsObject {
	
	private int succ = 0; // 0 失败 1成功
	private String msg = "";

	public SmsObjectS100() {
		setProto(100);
	}

	public int getSucc() {
		return succ;
	}

	public void setSucc(int succ) {
		this.succ = succ;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
		putInt(succ, buffer);
		putString(msg, buffer, ce);
	}

	@Override
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
		succ = getInt(in);
		msg = getString(in, cd);
	}
}
