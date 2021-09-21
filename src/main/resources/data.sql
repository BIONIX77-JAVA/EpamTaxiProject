CREATE TABLE IF NOT EXISTS USERS
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(150) NOT NULL,
    password   VARCHAR,
    first_name VARCHAR,
    last_name  VARCHAR,
    status     VARCHAR   DEFAULT 'UNCONFIRMED',
    locked     BOOLEAN,
    created    TIMESTAMP DEFAULT now(),
    UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS USER_ROLES
(
    user_id   BIGINT       NOT NULL,
    role_name VARCHAR(150) NOT NULL,
    UNIQUE (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES USERS (id)
);

CREATE TABLE IF NOT EXISTS VEHICLES
(
    id                  BIGSERIAL    NOT NULL PRIMARY KEY,
    brand               VARCHAR(150) NOT NULL,
    model               VARCHAR(150) NOT NULL,
    registration_number VARCHAR(20)  NOT NULL,
    color               VARCHAR(150) NOT NULL,
    vehicle_type        VARCHAR(150) NOT NULL,
    category_type       VARCHAR(150) NOT NULL,
    status              VARCHAR(150) NOT NULL,
    created             TIMESTAMP DEFAULT now(),
    UNIQUE (registration_number)
);

CREATE TABLE IF NOT EXISTS BOOKING_REQUESTS
(
    id                  BIGSERIAL NOT NULL PRIMARY KEY,
    user_id             BIGINT    NOT NULL,
    start_address       VARCHAR   NOT NULL,
    destination_address VARCHAR   NOT NULL,
    category_type       VARCHAR   NOT NULL,
    vehicle_type        VARCHAR,
    seats               INT       NOT NULL,
    promo_code          VARCHAR(20),
    created             TIMESTAMP DEFAULT now(),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS BOOKINGS
(
    id                 BIGINT        NOT NULL PRIMARY KEY,
    user_id            BIGINT        NOT NULL,
    booking_request_id BIGINT        NOT NULL,
    total_cost         DECIMAL(5, 2) NOT NULL,
    total_price        DECIMAL(5, 2) NOT NULL,
    discount           DECIMAL(5, 2) DEFAULT 0,
    discount_type      VARCHAR,
    promo_code         VARCHAR,
    status             VARCHAR       NOT NULL,
    modified           TIMESTAMP     DEFAULT now(),
    created            TIMESTAMP     DEFAULT now(),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (booking_request_id) REFERENCES booking_requests (id) ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS BOOKINGS_VEHICLES
(
    booking_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES BOOKINGS (id) ON DELETE NO ACTION,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles (id) ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS PROMO_CODES
(
    value      VARCHAR(10)   NOT NULL PRIMARY KEY,
    percent    DECIMAL(5, 2) NOT NULL,
    valid_from DATE DEFAULT now(),
    valid_to   DATE DEFAULT now() + interval '10 day'
);


CREATE TABLE IF NOT EXISTS VEHICLE_BILLING_ORDERS
(
    id         BIGSERIAL NOT NULL PRIMARY KEY,
    vehicle_id BIGINT    NOT NULL,
    booking_id BIGINT    NOT NULL,
    price      DECIMAL(5, 2),
    status    VARCHAR(50) NOT NULL,
    created    TIMESTAMP DEFAULT now(),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles (id) ON DELETE NO ACTION,
    FOREIGN KEY (booking_id) REFERENCES bookings (id) ON DELETE NO ACTION
);