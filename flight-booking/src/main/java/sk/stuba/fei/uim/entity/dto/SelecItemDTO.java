package sk.stuba.fei.uim.entity.dto;

public class SelecItemDTO {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SelecItemDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
