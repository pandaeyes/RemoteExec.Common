package exec.proto;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;

import exec.common.AbstractSmsObject;

/**
 * 校验
 */
public class SmsObjectC100 extends AbstractSmsObject {
	
	private String name = "";
	private String signature = "";
	private String version = "";
	
	public SmsObjectC100() {
		setProto(100);
	}
	
	public String getName() { 
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
		buffer.putString(name, 64, ce);
		buffer.putString(signature, 64, ce);
		buffer.putString(version, 64, ce);
	}
	
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
		name = in.getString(64, cd);
		signature = in.getString(64, cd);
		version = in.getString(64, cd);
	}
}
