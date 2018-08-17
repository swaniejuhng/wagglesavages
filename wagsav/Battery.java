public class Battery {
    private String waggle_id;
    private float battery_status;
    private float voltage;
    private char charging;
    private float temperature;
    private float humidity;
    private char heater;
    private char fan;
    private String notification;
    private int number;
    private String updated_time;

    public String getWaggle_id() {
        return waggle_id;
    }

    public void setWaggle_id(String waggle_id) {
        this.waggle_id = waggle_id;
    }

    public float getBattery_status() {
        return battery_status;
    }

    public void setBattery_status(float battery_status) {
        this.battery_status = battery_status;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public char getCharging() {
        return charging;
    }

    public void setCharging(char charging) {
        this.charging = charging;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public char getHeater() {
        return heater;
    }

    public void setHeater(char heater) {
        this.heater = heater;
    }

    public char getFan() {
        return fan;
    }

    public void setFan(char fan) {
        this.fan = fan;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }
}
