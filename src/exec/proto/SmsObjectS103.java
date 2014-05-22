package exec.proto;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;

import exec.common.AbstractSmsObject;

/**
 * 提示信息
 */
public class SmsObjectS103 extends AbstractSmsObject {

	private String line = "";

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	public SmsObjectS103() {
		setProto(103);
	}
	
	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
		putString(line, buffer, ce);
	}
	
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
		line = getString(in, cd);
	}
}
