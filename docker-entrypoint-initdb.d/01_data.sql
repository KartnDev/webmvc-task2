INSERT INTO users(id, userName, password)
VALUES ('111111', 'admin', 'admin');

INSERT INTO orders(id, userId, orderNumber, amount, currency, returnUrl, failUrl, status)
VALUES ('1234567890', '111111', 3, 721, 5555, 'https://test.domain.io/api/v1/registerCallback/success',
        'https://test.domain.io/api/v1/registerCallback/failed', 'registered');