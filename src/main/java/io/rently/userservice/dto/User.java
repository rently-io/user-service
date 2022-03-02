package io.rently.userservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.lang.NonNull;

@JsonDeserialize(builder = User.Builder.class)
public class User {
    @JsonProperty
    @NonNull
    private final String id; // required

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String username;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String fullName;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String email;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String phone;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public static class Builder {
        @JsonProperty
        private final String id;
        @JsonProperty
        private String username;
        @JsonProperty
        private String fullName;
        @JsonProperty
        private String email;
        @JsonProperty
        private String phone;

        @JsonCreator
        public Builder(String id) {
            this.id = id;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
