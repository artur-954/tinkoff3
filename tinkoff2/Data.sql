CREATE TABLE link (
  id SERIAL PRIMARY KEY,
  url TEXT NOT NULL,
  description TEXT,
  created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE chat (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL
);

CREATE TABLE chat_link (
  id SERIAL PRIMARY KEY,
  chat_id INTEGER REFERENCES chat(id),
  link_id INTEGER REFERENCES link(id),
  created_at TIMESTAMP DEFAULT NOW()
);