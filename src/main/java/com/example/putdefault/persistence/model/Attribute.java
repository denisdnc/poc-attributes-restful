package com.example.putdefault.persistence.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@JsonInclude(Include.NON_NULL)
@Document
public final class Attribute {

    @Id
    private String id;
    private String requiredValue;
    private String oldValue;
    private String newValue;
    private CompositeAttribute compositeAttribute;

    public Attribute() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequiredValue() {
        return requiredValue;
    }

    public void setRequiredValue(String requiredValue) {
        this.requiredValue = requiredValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public CompositeAttribute getCompositeAttribute() {
        return compositeAttribute;
    }

    public void setCompositeAttribute(CompositeAttribute compositeAttribute) {
        this.compositeAttribute = compositeAttribute;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id='" + id + '\'' +
                ", requiredValue='" + requiredValue + '\'' +
                ", oldValue='" + oldValue + '\'' +
                ", newValue='" + newValue + '\'' +
                ", compositeAttribute=" + compositeAttribute +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Attribute that = (Attribute) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getRequiredValue(), that.getRequiredValue()) &&
                Objects.equals(getOldValue(), that.getOldValue()) &&
                Objects.equals(getNewValue(), that.getNewValue()) &&
                Objects.equals(getCompositeAttribute(), that.getCompositeAttribute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRequiredValue(), getOldValue(), getNewValue(), getCompositeAttribute());
    }

    public static final class Builder {

        private String id;
        private String requiredValue;
        private String oldValue;
        private String newValue;
        private CompositeAttribute compositeAttribute;

        private Builder() {
        }

        public static Builder anAttributes() {
            return new Builder();
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withRequiredValue(String alwaysValue) {
            this.requiredValue = alwaysValue;
            return this;
        }

        public Builder withOldValue(String notAlwaysValue) {
            this.oldValue = notAlwaysValue;
            return this;
        }

        public Builder withNewValue(String newValue) {
            this.newValue = newValue;
            return this;
        }

        public Builder withCompositeAttribute(CompositeAttribute compositeAttribute) {
            this.compositeAttribute = compositeAttribute;
            return this;
        }

        public Attribute build() {
            Attribute attribute = new Attribute();
            attribute.setId(id);
            attribute.setRequiredValue(requiredValue);
            attribute.setOldValue(oldValue);
            attribute.setNewValue(newValue);
            attribute.setCompositeAttribute(compositeAttribute);
            return attribute;
        }
    }
}
