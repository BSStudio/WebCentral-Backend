package hu.bme.sch.bss.webcentral.core.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.DomainAuditModel;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@JsonSerialize
@JsonDeserialize(builder = Status.Builder.class)
@Entity
@Table(name = "status")
public final class Status extends DomainAuditModel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    //TODO read more about it
    @JsonIgnore
    @OneToMany(targetEntity = User.class, cascade = ALL, fetch = EAGER, orphanRemoval = true, mappedBy = "status")
    private Set<User> users;

    public Status() {
        // No-arg constructor for hibernate
    }

    public Status(final Builder builder) {
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<User> getUsers() {
        return users;
    }

    //Generated code begins here


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Status status = (Status) o;
        return Objects.equals(id, status.id)
            && Objects.equals(name, status.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Status{"
            + "id=" + id
            + ", name='" + name + '\''
            + '}';
    }

    // Generated code ends here

    @SuppressWarnings("hiddenfield")
    public static final class Builder {
        private String name;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Status build() {
            return new Status(this);
        }
    }
}
