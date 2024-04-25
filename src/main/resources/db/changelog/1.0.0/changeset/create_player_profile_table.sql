CREATE TABLE player_profile
(
    account_id       BIGINT PRIMARY KEY,
    personaname      VARCHAR(255) NOT NULL,
    name             VARCHAR(255),
    plus             BOOLEAN      NOT NULL,
    cheese           INT          NOT NULL,
    steamid          VARCHAR(255) NOT NULL,
    avatar           VARCHAR(500) NOT NULL,
    avatarmedium     VARCHAR(500) NOT NULL,
    avatarfull       VARCHAR(500) NOT NULL,
    profileurl       VARCHAR(500) NOT NULL,
    last_login       TIMESTAMP WITH TIME ZONE,
    loccountrycode   CHAR(2),
    status           VARCHAR(255),
    fh_unavailable   BOOLEAN,
    is_contributor   BOOLEAN      NOT NULL,
    is_subscriber    BOOLEAN      NOT NULL,
    rank_tier        INT,
    leaderboard_rank INT
)