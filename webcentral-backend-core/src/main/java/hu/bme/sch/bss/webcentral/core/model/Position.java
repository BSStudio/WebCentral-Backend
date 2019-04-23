package hu.bme.sch.bss.webcentral.core.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.DomainAuditModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@JsonSerialize
@JsonDeserialize(builder = Position.Builder.class)
@Entity
@Table(name = "position")
public final class Position extends DomainAuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position(final Builder builder) {
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

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
