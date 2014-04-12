package exec.common;

import exec.proto.SmsObject;

public class SmsObjectFactory {
	
	public static ISmsObject newSmsObject(int proto) {
		ISmsObject sms = null;
		switch (proto) {
			case 101:
				sms = new SmsObject();
				break;
			default:
				throw new RuntimeException("unknow proto");
		}
		sms.setProto(proto);
		return sms;
	}
}
