package com.example.personal_trainer.ui.register;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class RegisteredResult {
    @Nullable
    private RegisteredInUserView success;
    @Nullable
    private Integer error;

    RegisteredResult(@Nullable Integer error) {
        this.error = error;
    }

    RegisteredResult(@Nullable RegisteredInUserView success) {
        this.success = success;
    }

    @Nullable
    RegisteredInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}