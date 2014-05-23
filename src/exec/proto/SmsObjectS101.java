package exec.proto;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;

import exec.common.AbstractSmsObject;
import exec.common.Command;


/**
 * 可用命令列表
 */
public class SmsObjectS101 extends AbstractSmsObject {

	private List<Command> cmdList = new ArrayList<Command>();
	
	public List<Command> getCmdList() {
		return cmdList;
	}

	public void setCmdList(List<Command> cmdList) {
		this.cmdList = cmdList;
	}

	public SmsObjectS101() {
		setProto(101);
	}
	
	public void encode(IoBuffer buffer, CharsetEncoder ce) throws Exception {
		for (Command cmd : cmdList) {
			putString(cmd.getKey(), buffer, ce);
			putString(cmd.getDesc(), buffer, ce);
			putString(cmd.getDir(), buffer, ce);
			putString(cmd.getCmd(), 124, buffer, ce);
		}
	}
	
	public void decode(IoBuffer in, CharsetDecoder cd) throws Exception {
		String key = null;
		String desc = null;
		String dir = null;
		String cmd = null;
		while (in.hasRemaining()) {
			Command command = new Command();
			key = getString(in, cd);
			desc = getString(in, cd);
			dir = getString(in, cd);
			cmd = getString(124, in, cd);
			command.setKey(key);
			command.setDesc(desc);
			command.setDir(dir);
			command.setCmd(cmd);
			cmdList.add(command);
		}
	}
}
