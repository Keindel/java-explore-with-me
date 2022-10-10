DROP TABLE IF EXISTS location_areas,
    compile_events_coupling,
    participation_requests,
    events,
    compilations,
    users,
    locations,
    categories;

CREATE TABLE IF NOT EXISTS categories
(
    id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(32) UNIQUE                              NOT NULL
);

CREATE TABLE IF NOT EXISTS locations
(
    id  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    lat DECIMAL(9, 6)                                   NOT NULL,
    lon DECIMAL(9, 6)                                   NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    name  VARCHAR(128)                                    NOT NULL,
    email VARCHAR(128) UNIQUE                             NOT NULL
);

CREATE TABLE IF NOT EXISTS compilations
(
    id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    pinned BOOLEAN                                         NOT NULL,
    title  VARCHAR(512)                                    NOT NULL
);

CREATE TABLE IF NOT EXISTS events
(
    id                 BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    annotation         VARCHAR(512)                                    NOT NULL,
    category_id        INT REFERENCES categories (id) ON DELETE NO ACTION,
    created_on         TIMESTAMP                                       NOT NULL,
    description        VARCHAR(1024)                                   NOT NULL,
    event_date         TIMESTAMP                                       NOT NULL,
    initiator_id       BIGINT REFERENCES users (id) ON DELETE NO ACTION,
    location_id        BIGINT REFERENCES locations (id) ON DELETE NO ACTION,
    paid               BOOLEAN                                         NOT NULL,
    participant_limit  INT,
    published_on       TIMESTAMP,
    request_moderation BOOLEAN                                         NOT NULL,
    state              VARCHAR(64)                                     NOT NULL,
    title              VARCHAR(128)                                    NOT NULL
);

CREATE TABLE IF NOT EXISTS participation_requests
(
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    created      TIMESTAMP                                       NOT NULL,
    event_id     BIGINT REFERENCES events (id) ON DELETE CASCADE NOT NULL,
    requester_id BIGINT REFERENCES users (id) ON DELETE CASCADE  NOT NULL,
    status       VARCHAR(32)                                     NOT NULL,

    CONSTRAINT forbid_duplicate_requests UNIQUE (event_id, requester_id)
);


CREATE TABLE IF NOT EXISTS compile_events_coupling
(
    event_id       BIGINT REFERENCES events (id) ON DELETE CASCADE       NOT NULL,
    compilation_id BIGINT REFERENCES compilations (id) ON DELETE CASCADE NOT NULL,

    PRIMARY KEY (event_id, compilation_id)
);

CREATE TABLE IF NOT EXISTS location_areas
(
    id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    lat    DECIMAL(9, 6)                                   NOT NULL,
    lon    DECIMAL(9, 6)                                   NOT NULL,
    name   VARCHAR(128),
    radius INT
);