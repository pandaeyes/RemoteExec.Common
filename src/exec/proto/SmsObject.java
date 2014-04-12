package exec.proto;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;

import exec.common.ISmsObject;

public class SmsObject implements ISmsObject{

	private int proto = 101;
	private String content = "";
	private String param = "";
	
	public int getProto() {
		return proto;
	}
	public void setProto(int proto) {
		this.proto = proto;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
		buffer.putString(content, 100, ce);
		buffer.putString(param, 100, ce);
	}
	
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
		content = in.getString(100, cd);
		param = in.getString(100, cd);
	}
}
