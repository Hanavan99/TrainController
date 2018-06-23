package controller.core.enums;

public enum CommandSet {

	TMCC(0xFE), LEGACY_ENGINE(0xF8), LEGACY_TRAIN(0xF9), LEGACY_MULTI_WORD(0xFB);

	private int code;

	private CommandSet(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
