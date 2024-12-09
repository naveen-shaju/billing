CREATE TABLE billing_header (
    id SERIAL PRIMARY KEY,
    billing_id VARCHAR(255),
    total_invoice_amount DECIMAL(10, 2),
    invoice_date DATE
);
CREATE TABLE payment_information (
    id SERIAL PRIMARY KEY,
    payment_method VARCHAR(50),
    card_number_type VARCHAR(255),
    form_of_payment_id VARCHAR(255),
    billing_header_id INT,
    CONSTRAINT fk_billing_header
        FOREIGN KEY (billing_header_id)
        REFERENCES billing_header(id)
        ON DELETE CASCADE
);
