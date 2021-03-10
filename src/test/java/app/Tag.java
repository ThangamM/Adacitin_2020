package petStoreWithBDD;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tag {
    @JsonProperty("id")
    int tagId;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @JsonProperty("name")
    String tagName;

    public Tag() {

    }

    public Tag(String tagName, int tagId) {
        this.tagName = tagName;
        this.tagId = tagId;
    }

}
