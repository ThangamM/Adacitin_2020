package petStoreWithBDD;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "id", "status"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    String name;
    int id;
    String status;
    Category category;
    Tag[] tags;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public Pet() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pet(String name, int id, String status) {
        this.name = name;
        this.id = id;
        this.status = status;
    }

    public Pet petWithCategory(Category category) {
        this.category = category;
        return this;
    }

    public Pet petWithTag(Tag[] tags) {
        this.tags = tags;
        return this;
    }

}
