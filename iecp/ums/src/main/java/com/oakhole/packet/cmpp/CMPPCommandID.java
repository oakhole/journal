package com.oakhole.packet.cmpp;

public class CMPPCommandID {

	public CMPPCommandID() {
	}

	public static final int RESPONSE_MASK = 0x80000000;
	public static final int NACK_RESPONSE = 0x80000000;
	public static final int CONNECT = 1;
	public static final int CONNECT_RESPONSE = 0x80000001;
	public static final int TERMINATE = 2;
	public static final int TERMINATE_RESPONSE = 0x80000002;
	public static final int SUBMIT = 4;
	public static final int SUBMIT_RESPONSE = 0x80000004;
	public static final int DELIVER = 5;
	public static final int DELIVER_RESPONSE = 0x80000005;
	public static final int QUERY = 6;
	public static final int QUERY_RESPONSE = 0x80000006;
	public static final int CANCEL = 7;
	public static final int CANCEL_RESPONSE = 0x80000007;
	public static final int ACTIVETEST = 8;
	public static final int ACTIVETEST_RESPONSE = 0x80000008;
	public static final int FORWARD = 9;
	public static final int FORWARD_RESPONSE = 0x80000009;

	public static boolean isResponse(int command_id) {
		switch (command_id) {
		case -2147483648:
		case -2147483647:
		case -2147483646:
		case -2147483644:
		case -2147483643:
		case -2147483642:
		case -2147483641:
		case -2147483640:
		case -2147483639:
			return true;

		case -2147483645:
		default:
			return false;
		}
	}

	public static boolean isRequest(int command_id) {
		switch (command_id) {
		case 1:
		case 2:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return true;

		case 3:
		default:
			return false;
		}
	}

	public static boolean isValid(int command_id) {
		return isRequest(command_id) || isResponse(command_id);
	}

	public static boolean isMessage(int command_id) {
		switch (command_id) {
		case -2147483644:
		case -2147483643:
		case -2147483639:
		case 4:
		case 5:
		case 9:
			return true;
		}
		return false;
	}

	public static boolean isTransmitterOutput(int command_id) {
		switch (command_id) {
		case -2147483646:
		case -2147483640:
		case 2:
		case 4:
		case 6:
		case 7:
		case 8:
		case 9:
			return true;
		}
		return false;
	}

	public static boolean isTransmitterInput(int command_id) {
		switch (command_id) {
		case -2147483646:
		case -2147483644:
		case -2147483642:
		case -2147483641:
		case -2147483640:
		case -2147483639:
		case 2:
		case 8:
			return true;
		}
		return false;
	}

	public static boolean isReceiverOutput(int command_id) {
		switch (command_id) {
		case -2147483646:
		case -2147483643:
		case -2147483640:
		case -2147483639:
		case 2:
		case 8:
			return true;
		}
		return false;
	}

	public static boolean isReceiverInput(int command_id) {
		switch (command_id) {
		case -2147483646:
		case -2147483640:
		case 2:
		case 5:
		case 8:
		case 9:
			return true;
		}
		return false;
	}

	public static boolean isTransceiverInput(int command_id) {
		return isTransmitterInput(command_id) || isReceiverInput(command_id);
	}

	public static boolean isTransceiverOutput(int command_id) {
		return isTransmitterOutput(command_id) || isReceiverOutput(command_id);
	}

	public static String toString(int command_id) {
		switch (command_id) {
		case -2147483648:
			return "nack_response";

		case 1: // '\001'
			return "connect";

		case -2147483647:
			return "connect_response";

		case 2: // '\002'
			return "terminate";

		case -2147483646:
			return "terminate_response";

		case 4: // '\004'
			return "submit";

		case -2147483644:
			return "submit_response";

		case 5: // '\005'
			return "deliver";

		case -2147483643:
			return "deliver_response";

		case 6: // '\006'
			return "query";

		case -2147483642:
			return "query_response";

		case 7: // '\007'
			return "cancel";

		case -2147483641:
			return "cancel_response";

		case 8: // '\b'
			return "activetest";

		case -2147483640:
			return "activetest_response";

		case 9: // '\t'
			return "forward";

		case -2147483639:
			return "forward_response";
		}
		return "reserved";
	}

	public static int toCommand(String command) {
		if (command.equals("nack_response"))
			return 0x80000000;
		if (command.equals("connect"))
			return 1;
		if (command.equals("connect_response"))
			return 0x80000001;
		if (command.equals("terminate"))
			return 2;
		if (command.equals("terminate_response"))
			return 0x80000002;
		if (command.equals("submit"))
			return 4;
		if (command.equals("submit_response"))
			return 0x80000004;
		if (command.equals("deliver"))
			return 5;
		if (command.equals("deliver_response"))
			return 0x80000005;
		if (command.equals("query"))
			return 6;
		if (command.equals("query_response"))
			return 0x80000006;
		if (command.equals("cancel"))
			return 7;
		if (command.equals("cancel_response"))
			return 0x80000007;
		if (command.equals("activetest"))
			return 8;
		if (command.equals("activetest_response"))
			return 0x80000008;
		if (command.equals("forward"))
			return 9;
		return !command.equals("forward_response") ? 0 : 0x80000009;
	}

}
