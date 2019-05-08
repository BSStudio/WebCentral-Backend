package hu.bme.sch.bss.webcentral.core.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.core.DomainAuditModel;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@JsonSerialize
@JsonDeserialize(builder = Position.Builder.class)
@Entity
@Table(name = "position")
public final class Position extends DomainAuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    public Position() {
        // No-arg constructor for hibernate
    }

    public Position(final Builder builder) {
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
        Position position = (Position) o;
        return Objects.equals(id, position.id)
            && Objects.equals(name, position.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Position{"
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

        public Position build() {
            return new Position(this);
        }
    }
}
