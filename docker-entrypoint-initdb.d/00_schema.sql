CREATE TABLE users
(
    id        TEXT PRIMARY KEY,
    userName  TEXT NOT NULL UNIQUE,
    password  TEXT NOT NULL,
    isDeleted BOOLEAN DEFAULT FALSE,
    deletedAt DATE    DEFAULT NULL
);

CREATE TABLE orders
(
    id          TEXT PRIMARY KEY,
    userId      TEXT    NOT NULL,
    orderNumber TEXT    NOT NULL,
    amount      INTEGER NOT NULL DEFAULT 0 CHECK (amount >= 0),
    currency    INTEGER          DEFAULT 810 CHECK (currency >= 0),
    returnUrl   TEXT    NOT NULL,
    failUrl     TEXT,
    status      TEXT,
    isDeleted   BOOLEAN          DEFAULT FALSE,
    deletedAt   DATE             DEFAULT NULL,
    CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES users (id)
);
