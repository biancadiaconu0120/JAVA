package com.modul2.learning.entities;

public enum ReservationStatus {
    PENDING {
        @Override
        public Boolean isNextStatePossible(ReservationStatus nextState) {
            return nextState == IN_PROGRESS || nextState == CANCELED;
        }
    },
    IN_PROGRESS {
        @Override
        public Boolean isNextStatePossible(ReservationStatus nextState) {
            return nextState == DELAYED || nextState == FINISHED;
        }
    },
    DELAYED {
        @Override
        public Boolean isNextStatePossible(ReservationStatus nextState) {
            return nextState == FINISHED;
        }
    },
    FINISHED {
        @Override
        public Boolean isNextStatePossible(ReservationStatus nextState) {
            return false;
        }
    }, CANCELED {
        @Override
        public Boolean isNextStatePossible(ReservationStatus nextState) {
            return false;
        }
    };

    public abstract Boolean isNextStatePossible(ReservationStatus nextState);

}

