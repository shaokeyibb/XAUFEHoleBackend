package cn.xctra.xaufeholebackend.model;

public class WebVpnAvailableModel {

    public WebVpnAvailableModel(boolean available) {
        this.available = available;
    }

    private final boolean available;

    public boolean isAvailable() {
        return available;
    }
}
