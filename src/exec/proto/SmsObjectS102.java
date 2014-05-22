package exec.proto;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;

import exec.common.AbstractSmsObject;

/**
 * 执行结果
 */
public class SmsObjectS102 extends AbstractSmsObject {

	private String line = "";

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	public SmsObjectS102() {
		setProto(102);
	}
	
	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
		putString(line, 1024, buffer, ce);
	}
	
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
		line = getString(1024, in, cd);
	}
}
