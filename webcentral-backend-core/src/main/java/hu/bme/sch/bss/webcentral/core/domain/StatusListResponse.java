package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.model.Status;

import java.util.List;

@JsonSerialize
public final class StatusListResponse {
    private final Status[] statuses;

    public Status[] getStatuses() {
        return statuses;
    }

    private StatusListResponse(final Builder builder) {
        this.statuses = builder.statuses;
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {
        private Status[] statuses;

        public Builder withStatuses(final Status[] statuses) {
            this.statuses = statuses;
            return this;
        }

        public Builder withStatuses(final List<Status> statuses) {
            this.statuses = new Status[statuses.size()];
            this.statuses = statuses.toArray(this.statuses);
            return this;
        }

        public StatusListResponse build() {
            return new StatusListResponse(this);
        }
    }
}
