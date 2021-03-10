package petStoreWithBDD;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Inventory {
    @JsonProperty("sold")
    int soldCount;
    @JsonProperty("pending")
    int pendingCount;
    @JsonProperty("active")
    int activeCount;

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public int getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    public Inventory() {

    }

    public Inventory(int soldCount, int pendingCount, int activeCount) {
        this.soldCount = soldCount;
        this.pendingCount = pendingCount;
        this.activeCount = activeCount;
    }

}
