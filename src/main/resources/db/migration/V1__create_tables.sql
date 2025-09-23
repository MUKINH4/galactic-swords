CREATE TABLE adventurer
(
    id       VARCHAR(255) NOT NULL,
    email    VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_adventurer PRIMARY KEY (id)
);

CREATE TABLE sword
(
    name            VARCHAR(255)     NOT NULL,
    rarity          SMALLINT,
    damage          DOUBLE PRECISION NOT NULL,
    characteristics TEXT[],
    description     VARCHAR(255),
    owner_id        VARCHAR(255),
    image_url       VARCHAR(255),
    CONSTRAINT pk_sword PRIMARY KEY (name)
);

ALTER TABLE sword
    ADD CONSTRAINT FK_SWORD_ON_OWNER FOREIGN KEY (owner_id) REFERENCES adventurer (id);