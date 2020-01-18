package eu.cwsfe.monitoring.history;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class History {

    private int id;
    private String code;
    private String description;

    public History(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        History history = (History) o;

        return new EqualsBuilder()
                .append(id, history.id)
                .append(code, history.code)
                .append(description, history.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(code)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("code", code)
                .append("description", description)
                .toString();
    }
}
