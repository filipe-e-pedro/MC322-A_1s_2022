package connectricity;

public class Player extends Entity{

	int wireInventorySize = 8;
	int wireNumber = 0;

	Wire wireInventory[] = new Wire[wireInventorySize];

	public Player(int xIndex, int yIndex, Map map){
		super(xIndex, yIndex, map);
		for (int i = 0; i < wireInventorySize; i++) {
			wireInventory[i] = null;
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

	public void setPosition(int xIndex, int yIndex) {
		this.xIndex = xIndex;
		this.yIndex = yIndex;
	}

}