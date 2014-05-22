package exec.proto;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;

import exec.common.AbstractSmsObject;

/**
 * 单条执行,可以带参数
 */
public class SmsObjectC104 extends AbstractSmsObject {
	
	private String cmdkey = "";
	private String param = "";
	
	public String getCmdkey() {
		return cmdkey;
	}

	public void setCmdkey(String cmdkey) {
		this.cmdkey = cmdkey;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public SmsObjectC104() {
		setProto(104);
	}

	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
		putString(cmdkey, buffer, ce);
		putString(param, buffer, ce);
	}
	
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
		cmdkey = getString(in, cd);
		param = getString(in, cd);
	}
}
