package ibcs.ia;

public class ServiceEnabledModel {
    private boolean isServiceEnabled;

    public ServiceEnabledModel(boolean isServiceEnabled) {
        this.isServiceEnabled = isServiceEnabled;
    }

    public boolean isServiceEnabled() {
        return isServiceEnabled;
    }

    public void setServiceEnabled(boolean serviceEnabled) {
        isServiceEnabled = serviceEnabled;
    }
}
