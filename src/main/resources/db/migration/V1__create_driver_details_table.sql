CREATE TABLE driver_details_table
(
    id                        SERIAL PRIMARY KEY,
    visiting_state            VARCHAR(50),
    vehicle_number            VARCHAR(10),
    seat_capacity             VARCHAR(50),
    whatsapp_number           VARCHAR(10),
    entry_border              VARCHAR(50),
    tax_mode                  VARCHAR(50),
    tax_from_date             VARCHAR(20),
    tax_to_date               VARCHAR(20),
    total_payment             VARCHAR(20),
    is_tax_paid_to_government VARCHAR(10)
);
