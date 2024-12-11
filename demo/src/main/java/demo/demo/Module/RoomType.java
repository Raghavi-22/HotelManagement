package demo.demo.Module;

import java.util.UUID;

public class RoomType {
    private UUID typeID;
    private String typeName;
    private String description;

    // Getters and Setters
    public UUID getTypeID() {
        return typeID;
    }

    public void setTypeID(UUID typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
