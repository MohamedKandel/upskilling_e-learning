package com.elearning.graduation.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class EnrollmentId implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID accountId;
    private UUID crsId;

    public EnrollmentId() {}

    public EnrollmentId(UUID accountId, UUID crsId) {
        this.accountId = accountId;
        this.crsId = crsId;
    }

    public UUID getAccountId() { return accountId; }
    public void setAccountId(UUID accountId) { this.accountId = accountId; }

    public UUID getCrsId() { return crsId; }
    public void setCrsId(UUID crsId) { this.crsId = crsId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrollmentId)) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(accountId, that.accountId) &&
               Objects.equals(crsId, that.crsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, crsId);
    }
}