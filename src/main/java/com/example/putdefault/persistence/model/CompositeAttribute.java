package com.example.putdefault.persistence.model;

import java.util.Objects;

public final class CompositeAttribute {

    private String innerValue;

    public CompositeAttribute() {
    }

    public String getInnerValue() {
        return innerValue;
    }

    public void setInnerValue(String innerValue) {
        this.innerValue = innerValue;
    }

    @Override
    public String toString() {
        return "CompositeAttribute{" +
                "innerValue='" + innerValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CompositeAttribute that = (CompositeAttribute) o;
        return Objects.equals(getInnerValue(), that.getInnerValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInnerValue());
    }

    public static final class Builder {

        private String innerValue;

        private Builder() {
        }

        public static Builder aCompositeAttribute() {
            return new Builder();
        }

        public Builder withInnerValue(String innerValue) {
            this.innerValue = innerValue;
            return this;
        }

        public CompositeAttribute build() {
            CompositeAttribute compositeAttribute = new CompositeAttribute();
            compositeAttribute.setInnerValue(innerValue);
            return compositeAttribute;
        }
    }
}

