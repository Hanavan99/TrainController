package controller.core;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -1261758762657544307L;

	private String username;
	private boolean isAdmin;
	private int maxEngineSpeed;
	private boolean canEditSwitches;
	private boolean canUseSwitches;
	private boolean canEditAccessories;
	private boolean canUseAccessories;
	private boolean canEditRoutes;
	private boolean canUseRoutes;
	private boolean canEditEngines;
	private boolean canUseEngines;
	private boolean canEditTrains;
	private boolean canUseTrains;

	public User() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isCanEditSwitches() {
		return canEditSwitches;
	}

	public void setCanEditSwitches(boolean canEditSwitches) {
		this.canEditSwitches = canEditSwitches;
	}

	public boolean isCanEditAccessories() {
		return canEditAccessories;
	}

	public void setCanEditAccessories(boolean canEditAccessories) {
		this.canEditAccessories = canEditAccessories;
	}

	public boolean isCanEditRoutes() {
		return canEditRoutes;
	}

	public void setCanEditRoutes(boolean canEditRoutes) {
		this.canEditRoutes = canEditRoutes;
	}

	public boolean isCanEditEngines() {
		return canEditEngines;
	}

	public void setCanEditEngines(boolean canEditEngines) {
		this.canEditEngines = canEditEngines;
	}

	public int getMaxEngineSpeed() {
		return maxEngineSpeed;
	}

	public void setMaxEngineSpeed(int maxEngineSpeed) {
		this.maxEngineSpeed = maxEngineSpeed;
	}

	public boolean isCanUseSwitches() {
		return canUseSwitches;
	}

	public void setCanUseSwitches(boolean canUseSwitches) {
		this.canUseSwitches = canUseSwitches;
	}

	public boolean isCanUseAccessories() {
		return canUseAccessories;
	}

	public void setCanUseAccessories(boolean canUseAccessories) {
		this.canUseAccessories = canUseAccessories;
	}

	public boolean isCanUseRoutes() {
		return canUseRoutes;
	}

	public void setCanUseRoutes(boolean canUseRoutes) {
		this.canUseRoutes = canUseRoutes;
	}

	public boolean isCanUseEngines() {
		return canUseEngines;
	}

	public void setCanUseEngines(boolean canUseEngines) {
		this.canUseEngines = canUseEngines;
	}

	public boolean isCanEditTrains() {
		return canEditTrains;
	}

	public void setCanEditTrains(boolean canEditTrains) {
		this.canEditTrains = canEditTrains;
	}

	public boolean isCanUseTrains() {
		return canUseTrains;
	}

	public void setCanUseTrains(boolean canUseTrains) {
		this.canUseTrains = canUseTrains;
	}

}
