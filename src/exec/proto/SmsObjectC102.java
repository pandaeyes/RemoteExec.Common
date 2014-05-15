package exec.proto;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import exec.common.AbstractSmsObject;

/**
 * 批量执行
 */
public class SmsObjectC102 extends AbstractSmsObject {
	
	private List<String> cmdList = new ArrayList<String>();

	public List<String> getCmdList() {
		return cmdList;
	}

	public void setCmdList(List<String> cmdList) {
		this.cmdList = cmdList;
	}
	
	public SmsObjectC102() {
		setProto(102);
	}
	
	@Override
	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
		for (String cmd : cmdList) {
			putString(cmd, buffer, ce);
		}
	}

	@Override
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
		while (in.hasRemaining()) {
			cmdList.add(getString(in, cd));
		}
	}
}
