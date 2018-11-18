package me.zuichu.emoji;

public class Emotion {
    private String unicode;
    private String surrogates;
    private String name;
    private String type;

    private Emotion(Builder builder) {
        setUnicode(builder.unicode);
        setSurrogates(builder.surrogates);
        setName(builder.name);
        setType(builder.type);
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSurrogates() {
        return surrogates;
    }

    public void setSurrogates(String surrogates) {
        this.surrogates = surrogates;
    }

    public static final class Builder {
        private String unicode;
        private String surrogates;
        private String name;
        private String type;

        public Builder() {
        }

        public Builder unicode(String val) {
            unicode = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder surrogates(String val) {
            surrogates = val;
            return this;
        }

        public Emotion build() {
            return new Emotion(this);
        }
    }
}
