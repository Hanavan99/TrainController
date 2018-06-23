package controller.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import controller.core.Accessory;
import controller.core.Engine;
import controller.core.Route;
import controller.core.Switch;
import controller.core.Train;

public class ResourceManager {

	public static final String ENGINE_FILE = "engines.ser";
	public static final String TRAIN_FILE = "trains.ser";
	public static final String SWITCH_FILE = "switches.ser";
	public static final String ACCESSORY_FILE = "accessories.ser";
	public static final String ROUTE_FILE = "routes.ser";

	private File directory;
	private ArrayList<Engine> engines = new ArrayList<Engine>();
	private ArrayList<Train> trains = new ArrayList<Train>();
	private ArrayList<Switch> switches = new ArrayList<Switch>();
	private ArrayList<Accessory> accessories = new ArrayList<Accessory>();
	private ArrayList<Route> routes = new ArrayList<Route>();

	public ResourceManager(File directory) {
		this.directory = directory;
	}

	public void loadResources() {
		engines = load(directory, ENGINE_FILE);
		trains = load(directory, TRAIN_FILE);
		switches = load(directory, SWITCH_FILE);
		accessories = load(directory, ACCESSORY_FILE);
		routes = load(directory, ROUTE_FILE);
	}

	public void saveResources() {
		save(directory, ENGINE_FILE, engines);
		save(directory, TRAIN_FILE, trains);
		save(directory, SWITCH_FILE, switches);
		save(directory, ACCESSORY_FILE, accessories);
		save(directory, ROUTE_FILE, routes);
	}

	public Engine[] getEngines() {
		return engines.toArray(new Engine[0]);
	}

	public Train[] getTrains() {
		return trains.toArray(new Train[0]);
	}

	public Switch[] getSwitches() {
		return switches.toArray(new Switch[0]);
	}

	public Accessory[] getAccessories() {
		return accessories.toArray(new Accessory[0]);
	}

	public Route[] getRoutes() {
		return routes.toArray(new Route[0]);
	}

	public void updateEngine(Engine engine) {
		int index = engines.indexOf(engine);
		if (index != -1) {
			engines.set(engines.indexOf(engine), engine);
		} else {
			engines.add(engine);
		}
	}

	public void updateTrain(Train train) {
		int index = trains.indexOf(train);
		if (index != -1) {
			trains.set(index, train);
		} else {
			trains.add(train);
		}
	}

	public void updateSwitch(Switch sw) {
		System.out.println(sw);
		System.out.println(switches);
		int index = switches.indexOf(sw);
		if (index != -1) {
			switches.set(index, sw);
		} else {
			switches.add(sw);
		}
	}

	public void updateAccessory(Accessory acc) {
		int index = accessories.indexOf(acc);
		if (index != -1) {
			accessories.set(index, acc);
		} else {
			accessories.add(acc);
		}
	}

	public void updateRoute(Route route) {
		int index = routes.indexOf(route);
		if (index != -1) {
			routes.set(index, route);
		} else {
			routes.add(route);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> ArrayList<T> load(File directory, String name) {
		ArrayList<T> result = new ArrayList<T>();
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(directory, name)));
			result = (ArrayList<T>) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	private <T> void save(File directory, String name, ArrayList<T> list) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(directory, name)));
			out.writeObject(list);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
