CREATE TABLE workflow (
    id INT PRIMARY KEY IDENTITY,
    name VARCHAR(255)
);

CREATE TABLE state (
    id INT PRIMARY KEY IDENTITY,
    workflow_id INT FOREIGN KEY REFERENCES workflow(id),
    name VARCHAR(255),
    is_initial BIT,
    is_final BIT
);

CREATE TABLE transition (
    id INT PRIMARY KEY IDENTITY,
    workflow_id INT FOREIGN KEY REFERENCES workflow(id),
    source_state_id INT FOREIGN KEY REFERENCES state(id),
    target_state_id INT FOREIGN KEY REFERENCES state(id),
    event VARCHAR(255)
);

CREATE TABLE contract (
    id INT PRIMARY KEY IDENTITY,
    state_id INT FOREIGN KEY REFERENCES state(id),
    data NVARCHAR(MAX)
);
