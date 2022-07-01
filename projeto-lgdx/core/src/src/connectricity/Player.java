package src.connectricity;

public class Player extends Entity{

	int wireInventorySize = 60;
	int wireNumber = 0;

	int resistorInventorySize = 60;
	int resistorNumber = 0;

	Wire wireInventory[] = new Wire[wireInventorySize];
	Resistor resistorInventory[] = new Resistor[resistorInventorySize];

	public Player(int xIndex, int yIndex, Map map, int initialWireNumber, int initialResistorNumber){
		super(xIndex, yIndex, map);
		wireNumber = initialWireNumber;
		resistorNumber = initialResistorNumber;
		for (int i = 0; i < wireInventorySize; i++) {
			if (i < initialWireNumber) {
				Wire wire = new Wire(0, 0, map);
				wireInventory[i] = wire;
			}
			else {
				wireInventory[i] = null;
			}
		}
		for (int i = 0; i < resistorInventorySize; i++) {
			if (i < initialResistorNumber) {
				Resistor resistor = new Resistor(0, 0, map);
				resistorInventory[i] = resistor;
			}
			else {
				resistorInventory[i] = null;
			}
		}
	}

	public String getName() {
		return "P";
	}

	public int getWireNumber() {
		return wireNumber;
	}

	public boolean hasWire() {
		return wireNumber > 0;
	}

	public boolean hasWireSpace() {
		return wireNumber < wireInventorySize;
	}
	public int getResistorNumber() {
		return resistorNumber;
	}

	public boolean hasResistor() {
		return resistorNumber > 0;
	}

	public boolean hasResistorSpace() {
		return resistorNumber < resistorInventorySize;
	}

	public Wire spendWire() {
		for (int i = 0; i < wireInventorySize; i++) {
			if (wireInventory[i] != null) {
				Wire wire = wireInventory[i];
				wireInventory[i] = null;
				wireNumber --;
				return wire;
			}
		}
		return null;
	}

	public void storeWire(Wire wire) {
		boolean stored = false;
		for (int i = 0; i < wireInventorySize; i++) {
			if (wireInventory[i] == null) {
				wireInventory[i] = wire;
				stored = true;
				wireNumber++;
				break;
			}
		}
		if (!stored) {
			System.out.println("Seu inventario de fios está cheio");
		}
	}

	public Resistor spendResistor() {
		for (int i = 0; i < resistorInventorySize; i++) {
			if (resistorInventory[i] != null) {
				Resistor resistor = resistorInventory[i];
				resistorInventory[i] = null;
				resistorNumber --;
				return resistor;
			}
		}
		return null;
	}

	public void storeResistor(Resistor resistor) {
		boolean stored = false;
		for (int i = 0; i < resistorInventorySize; i++) {
			if (resistorInventory[i] == null) {
				resistorInventory[i] = resistor;
				stored = true;
				resistorNumber++;
				break;
			}
		}
		if (!stored) {
			System.out.println("Seu inventario de resistores está cheio");
		}
	}

	public void setPosition(int xIndex, int yIndex) {
		this.xIndex = xIndex;
		this.yIndex = yIndex;
	}

}