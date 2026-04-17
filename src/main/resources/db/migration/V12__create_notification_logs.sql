CREATE TABLE notification_logs (
    id       UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    title    VARCHAR(100) NOT NULL,
    body     VARCHAR(255) NOT NULL,
    sent_at  TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    sent_by  VARCHAR(255) NOT NULL
);
