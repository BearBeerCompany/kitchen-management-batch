CREATE TABLE
    IF NOT EXISTS orders_ack (
        id INTEGER PRIMARY KEY,
        order_number INTEGER,
        table_number VARCHAR(50),
        insert_date VARCHAR(50),
        insert_time VARCHAR(50),
        client_name VARCHAR(255),
        take_away BOOLEAN,
        order_notes VARCHAR(500),
        quantity INTEGER,
        menu_item_id INTEGER,
        menu_item_name VARCHAR(255),
        menu_item_notes VARCHAR(500),
        category_id INTEGER,
        ack BOOLEAN
    );
