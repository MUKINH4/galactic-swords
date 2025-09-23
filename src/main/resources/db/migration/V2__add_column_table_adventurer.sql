ALTER TABLE adventurer
    ADD gold DOUBLE PRECISION;

ALTER TABLE adventurer
    ADD nome VARCHAR(255);

ALTER TABLE adventurer
    ADD role VARCHAR(255);

ALTER TABLE adventurer
    ALTER COLUMN gold SET NOT NULL;