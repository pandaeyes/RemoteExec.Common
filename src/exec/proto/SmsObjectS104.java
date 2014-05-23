package exec.proto;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;

import exec.common.AbstractSmsObject;

/**
 * 运行是否结果
 */
public class SmsObjectS104 extends AbstractSmsObject {

	private int result = 0; // 0 失败 1成功
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
		putInt(result, buffer);
	}
	
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
		result = in.getInt();
	}
}
