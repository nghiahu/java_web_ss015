create database session15;
use session15;

create table Review(
    id int primary key auto_increment,
    idProduct int not null,
    idUser int not null,
    rating int not null check ( rating > 0 and rating < 6 ),
    comment varchar(255)
);

create table Product(
    id int primary key auto_increment,
    name varchar(50) not null ,
    description varchar(255),
    price int
);

create table Cart(
    idCart int primary key auto_increment,
    idUser int not null ,
    idProduct int not null,
    quantity int
);

CREATE TABLE orders (
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    idUser INT NOT NULL,
    recipientName VARCHAR(255) NOT NULL,
    address VARCHAR(500) NOT NULL,
    phoneNumber VARCHAR(20) NOT NULL,
    orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    orderId INT NOT NULL,
    productId INT NOT NULL,
    quantity INT NOT NULL,
    currentPrice DOUBLE NOT NULL,
    FOREIGN KEY (orderId) REFERENCES orders(orderId) ON DELETE CASCADE,
    FOREIGN KEY (productId) REFERENCES Product(id)
);


DELIMITER //
create procedure saveReview(
    idProduct_in int,
    idUser_in int,
    rating_in int,
    comment_in varchar(255)
)
begin
    insert into Review (idProduct, idUser, rating, comment)
        values (idProduct_in, idUser_in, rating_in, comment_in);
end //
DELIMITER ;

DELIMITER //
create procedure getReviewByProduct(idProduct_in int)
begin
    select * from Review where idProduct = idProduct_in;
end //
DELIMITER ;

DELIMITER //
create procedure addToCart(
    idUser_in int,
    idProduct_in int
)
begin
    declare existing_quantity int;

    select quantity into existing_quantity
    from cart
    where idUser = idUser_in and idProduct = idProduct_in;

    if existing_quantity is not null then
        update cart
        set quantity = quantity + 1
        where idUser = idUser_in and idProduct = idProduct_in;
    else
        INSERT INTO cart(idUser, idProduct, quantity)
        VALUES(idUser_in, idProduct_in, 1);
    end if;
end //
DELIMITER ;

DELIMITER //
create procedure getAllProduct()
begin
    select * from Product;
end //
DELIMITER ;

DELIMITER //
create procedure getCartByUser(idUser_in int)
begin
    select * from Cart where idUser = idUser_in;
end //
DELIMITER ;

DELIMITER //
create procedure getTotalCartByUser(idUser_in int)
begin
    select sum(c.quantity * p.price) as total_amount
    from Cart c
             join Product p on c.idProduct = p.id
    where c.idUser = idUser_in;
end //
DELIMITER ;

DELIMITER //
create procedure clearCart(idUser_in int)
begin
    delete from cart where idUser = idUser_in;
end //
DELIMITER ;

DELIMITER //

CREATE PROCEDURE addOrder(
    IN p_idUser INT,
    IN p_recipientName VARCHAR(255),
    IN p_address VARCHAR(500),
    IN p_phoneNumber VARCHAR(20)
)
BEGIN
    DECLARE newOrderId INT;
    INSERT INTO orders(idUser, recipientName, address, phoneNumber)
    VALUES(p_idUser, p_recipientName, p_address, p_phoneNumber);

    SET newOrderId = LAST_INSERT_ID();

    INSERT INTO order_details(orderId, productId, quantity, currentPrice)
    SELECT newOrderId, c.idProduct, c.quantity, p.price
    FROM cart c
             JOIN Product p ON c.idProduct = p.id
    WHERE c.idUser = p_idUser;

    DELETE FROM cart WHERE idUser = p_idUser;
END //

DELIMITER ;

DELIMITER //
CREATE PROCEDURE getOrdersByUser(
    IN p_idUser INT
)
BEGIN
    SELECT *
    FROM orders
    WHERE idUser = p_idUser
    ORDER BY orderDate DESC;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE getOrderDetailsByOrderId(
    IN p_orderId INT
)
BEGIN
    SELECT
        od.id AS orderDetailId,
        od.orderId,
        od.productId,
        p.name AS productName,
        p.description,
        od.quantity,
        od.currentPrice,
        (od.quantity * od.currentPrice) AS totalPrice
    FROM order_details od
             JOIN Product p ON od.productId = p.id
    WHERE od.orderId = p_orderId;
END //
DELIMITER ;

