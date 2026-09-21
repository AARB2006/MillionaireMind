package com.millionairemind.model;

import java.math.BigDecimal;

/**
 * Shared game state for the first UI-flow pass.
 */
public final class GameSession {

    private BigDecimal winnings = BigDecimal.ZERO;

    public BigDecimal getWinnings() {
        return winnings;
    }

    public void setWinnings(BigDecimal winnings) {

        if (winnings == null || winnings.signum() < 0) {
            throw new IllegalArgumentException(
                    "Winnings cannot be null or negative."
            );
        }

        this.winnings = winnings;
    }

    public void reset() {
        winnings = BigDecimal.ZERO;
    }
}
