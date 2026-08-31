// =====================================================
// 1. INTERFACE — defines a contract (what a vehicle can do)
// =====================================================
interface Vehicle {

    // --- Abstract methods (no body, MUST be implemented) ---
    String getBrand();
    int getMaxSpeed();
    String fuelType();

    // --- Default method (has a body, CAN be overridden) ---
    default String startEngine() {
        honk();  // can call private methods
        return getBrand() + " engine started — fuel: " + fuelType();
    }

    // --- Static method (belongs to the interface, NOT overridable) ---
    static String roadRegulation() {
        return "All vehicles must follow traffic laws!";
    }

    // --- Private method (helper used only inside the interface) ---
    private void honk() {
        System.out.println("Beep beep!");
    }
}

// =====================================================
// 2. SECOND INTERFACE — to show multiple interface implementation
// =====================================================
interface Electric {
    int batteryCapacity();  // abstract

    default String chargingInfo() {
        return "Battery: " + batteryCapacity() + " kWh";
    }
}

// =====================================================
// 3. ABSTRACT CLASS — partial implementation of Vehicle
//    Can have constructors, fields, and concrete methods
// =====================================================
abstract class AbstractVehicle implements Vehicle {

    // --- Fields (state) ---
    private final String brand;
    private final int maxSpeed;

    // --- Constructor ---
    public AbstractVehicle(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    // --- Implement some interface methods ---
    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }

    // --- Concrete method (shared by all subclasses) ---
    public String summary() {
        return brand + " | Max Speed: " + maxSpeed + " km/h | Fuel: " + fuelType();
    }

    // --- Abstract method (each subclass must define its own) ---
    abstract String sound();

    // NOTE: fuelType() is still abstract (comes from Vehicle interface)
    //       so subclasses must implement it too.
}

// =====================================================
// 4. CONCRETE CLASS — Gasoline car
// =====================================================
class GasolineCar extends AbstractVehicle {

    public GasolineCar(String brand, int maxSpeed) {
        super(brand, maxSpeed);
    }

    @Override
    public String fuelType() {
        return "Gasoline";
    }

    @Override
    String sound() {
        return "Vroom vroom!";
    }
}

// =====================================================
// 5. CONCRETE CLASS — Electric car (implements TWO interfaces)
// =====================================================
class ElectricCar extends AbstractVehicle implements Electric {

    private final int batteryKWh;

    public ElectricCar(String brand, int maxSpeed, int batteryKWh) {
        super(brand, maxSpeed);
        this.batteryKWh = batteryKWh;
    }

    @Override
    public String fuelType() {
        return "Electricity";
    }

    @Override
    String sound() {
        return "Whirrr...";
    }

    @Override
    public int batteryCapacity() {
        return batteryKWh;
    }

    // Override a default method from the interface
    @Override
    public String startEngine() {
        return getBrand() + " silently powers on ⚡";
    }
}

// =====================================================
// 6. MAIN CLASS — Putting it all together
// =====================================================
public class VehicleDemo {
    public static void main(String[] args) {

        // --- Create objects ---
        GasolineCar bmw = new GasolineCar("BMW M3", 250);
        ElectricCar tesla = new ElectricCar("Tesla Model S", 260, 100);

        // --- Use them through the abstract class reference ---
        AbstractVehicle[] garage = { bmw, tesla };

        System.out.println("========== GARAGE ==========");
        for (AbstractVehicle v : garage) {
            System.out.println(v.summary());          // concrete method
            System.out.println("Sound  : " + v.sound());         // abstract method
            System.out.println("Engine : " + v.startEngine());   // default method (overridden by Tesla)
            System.out.println();
        }

        // --- Interface static method (called on the interface itself) ---
        System.out.println(Vehicle.roadRegulation());

        // --- Electric-specific behavior via the Electric interface ---
        System.out.println();
        System.out.println("=== Electric-specific ===");
        System.out.println(tesla.chargingInfo());     // default method from Electric
    }
}
