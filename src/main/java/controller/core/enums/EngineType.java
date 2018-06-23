package controller.core.enums;

public enum EngineType {

	DEFAULT("None", new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }), DIESEL("Diesel", new String[] { "Reset", "Vol Up", "Engineer", "RPM Up", "Vol Down", "Emergency", "RPM Down", "Operator", "Smoke On", "Smoke Off" });

	private String displayName;
	private String[] numpadText;

	private EngineType(String displayName, String[] numpadText) {
		this.displayName = displayName;
		this.numpadText = numpadText;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String[] getNumpadText() {
		return numpadText;
	}

}
