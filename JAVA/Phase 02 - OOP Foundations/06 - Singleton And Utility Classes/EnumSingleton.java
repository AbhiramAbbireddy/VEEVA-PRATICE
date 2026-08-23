// Best practice Singleton (Effective Java Item 3)
public enum EnumSingleton {
    INSTANCE;

    private int configurationTimeout = 5000;

    public int getConfigurationTimeout() {
        return configurationTimeout;
    }

    public void setConfigurationTimeout(int timeout) {
        this.configurationTimeout = timeout;
    }

    public void performOperation() {
        System.out.println("Enum Singleton performing operation safely.");
    }
}
