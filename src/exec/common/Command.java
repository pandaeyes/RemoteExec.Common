package exec.common;

public class Command {
	
	private String key = "";
	private String desc = "";
	private String dir = "";
	private String cmd = "";
	private String groups = "";
	private String username = "";
	private boolean oneself = false; // 是否独占
			
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isOneself() {
		return oneself;
	}
	public void setOneself(boolean oneself) {
		this.oneself = oneself;
	}
}
