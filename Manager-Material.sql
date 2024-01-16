create schema `material`;
use `material`; 

    SET FOREIGN_KEY_CHECKS=OFF; -- to disable them
    SET FOREIGN_KEY_CHECKS=on; -- to re-enable them
    
    
create table category
(
    ID           int auto_increment
        primary key,
    IDPARENT     int                          null,
    NAME         varchar(500) charset utf8mb3 null,
    NOTES        text                         null,
    ICON         varchar(250) charset utf8mb3 null,
    CREATED_DATE timestamp                    null,
    UPDATED_DATE timestamp                    null,
    CREATED_BY   varchar(50) charset utf8mb3  null,
    UPDATED_BY   varchar(50) charset utf8mb3  null,
    ISACTIVE     tinyint                      null
);


INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (1, 'laptop_hp_240_1', 'laptop_hp_240_1.jpg', 1);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (2, 'laptop_hp_240_2', 'laptop_hp_240_2.jpg', 1);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (3, 'laptop_hp_240_3', 'laptop_hp_240_3.jpg', 1);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (4, 'laptop_lenovo_ideapad_1', 'laptop_lenovo_ideapad_1.jpg', 2);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (5, 'laptop_lenovo_ideapad_2', 'laptop_lenovo_ideapad_2.jpg', 2);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (6, 'laptop_lenovo_ideapad_3', 'laptop_lenovo_ideapad_3.jpg', 2);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (7, 'laptop_hp_15s_1', 'laptop_hp_15s_1.jpg', 3);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (8, 'laptop_hp_15s_2', 'laptop_hp_15s_2.jpg', 3);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (9, 'laptop_hp_15s_3', 'laptop_hp_15s_3.jpg', 3);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (10, 'laptop_palivion_14_1', 'laptop_palivion_14_1.jpg', 4);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (11, 'laptop_palivion_14_2', 'laptop_palivion_14_2.jpg', 4);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (12, 'laptop_palivion_14_3', 'laptop_palivion_14_3.jpg', 4);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (13, 'laptop_asus_vivobook x4_1', 'laptop_asus_vivobook x4_1.jpg', 5);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (14, 'laptop_asus_vivobook x4_2', 'laptop_asus_vivobook x4_2.jpg', 5);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (15, 'laptop_asus_vivobook x4_3', 'laptop_asus_vivobook x4_3.jpg', 5);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (16, 'laptop_dell_inspiron_1', 'laptop_dell_inspiron_1.jpg', 6);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (17, 'laptop_dell_inspiron_2', 'laptop_dell_inspiron_2.jpg', 6);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (18, 'laptop_dell_inspiron_3', 'laptop_dell_inspiron_3.jpg', 6);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (19, 'laptop_acer_aspire_1', 'laptop_acer_aspire_1.jpg', 7);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (20, 'laptop_acer_aspire_2', 'laptop_acer_aspire_2.jpg', 7);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (21, 'laptop_acer_aspire_3', 'laptop_acer_aspire_3.jpg', 7);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (22, 'laptop_macboock_air_m2_1', 'laptop_macboock_air_m2_1.jpg', 8);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (23, 'laptop_macboock_air_m2_2', 'laptop_macboock_air_m2_2.jpg', 8);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (24, 'laptop_macboock_air_m2_3', 'laptop_macboock_air_m2_3.jpg', 8);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (25, 'laptop_dell_vostro_15_1', 'laptop_dell_vostro_15_1.jpg', 9);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (26, 'laptop_dell_vostro_15_2', 'laptop_dell_vostro_15_2.jpg', 9);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (27, 'laptop_dell_vostro_15_3', 'laptop_dell_vostro_15_3.jpg', 9);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (28, 'latop_dell_inspiron_14_5430_1', 'latop_dell_inspiron_14_5430_1.jpg', 10);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (29, 'latop_dell_inspiron_14_5430_2', 'latop_dell_inspiron_14 5430_2.jpg', 10);
INSERT INTO `material`.product_images (ID, NAME, URL, ID_PRODUCT) VALUES (30, 'latop_dell_inspiron_14_5430_3', 'latop_dell_inspiron_14_5430_3.jpg', 10);

create table customer
(
    ID           int auto_increment
        primary key,
    NAME         varchar(250) charset utf8mb3 null,
    USERNAME     varchar(50) charset utf8mb3  null,
    PASSWORD     varchar(50) charset utf8mb3  null,
    ADDRESS      varchar(500) charset utf8mb3 null,
    EMAIL        varchar(150) charset utf8mb3 null,
    PHONE        varchar(50) charset utf8mb3  null,
    CREATED_DATE timestamp                    null,
    ISACTIVE     tinyint                      null
);

create table orders
(
    ID           int auto_increment
        primary key,
    IDORDERS     char(10)                     null,
    ORDERS_DATE  timestamp                    null,
    IDCUSTOMER   int                          null,
    TOTAL_MONEY  double                       null,
    NOTES        text                         null,
    NAME_RECIVER varchar(250) charset utf8mb3 null,
    ADDRESS      varchar(500) charset utf8mb3 null,
    PHONE        varchar(50) charset utf8mb3  null,
    constraint ORDERS_IDORDERS_uindex
        unique (IDORDERS),
    constraint orders_customer_ID_fk
        foreign key (IDCUSTOMER) references customer (ID)
);

create table payment_method
(
    ID           int auto_increment
        primary key,
    NAME         varchar(250) charset utf8mb3 null,
    NOTES        text                         null,
    CREATED_DATE timestamp                    null,
    UPDATED_DATE timestamp                    null,
    ISACTIVE     tinyint                      null
);

create table orders_payment
(
    ID        int auto_increment
        primary key,
    IDORD     int                         null,
    IDPAYMENT int                         null,
    TOTAL     int                         null,
    NOTES     int                         null,
    STATUS    varchar(50) charset utf8mb3 null,
    constraint orders_payment_orders_ID_fk
        foreign key (IDORD) references orders (ID),
    constraint orders_payment_payment_method_ID_fk
        foreign key (IDPAYMENT) references payment_method (ID)
);

-- sản phẩm  
create table product
(
    ID           int auto_increment
        primary key,
    NAME         varchar(500) charset utf8mb3 null,
    DESCRIPTION  text                         null,
    NOTES        text                         null,
    IMAGE        varchar(550) charset utf8mb3 null,
    IDCATEGORY   int                          null,
    PRICE        double                       null,
    QUATITY      int                          null,
    CREATED_DATE timestamp                    null,
    UPDATED_DATE timestamp                    null,
    CREATED_BY   varchar(50) charset utf8mb3  null,
    UPDATED_BY   varchar(50) charset utf8mb3  null,
    ISACTIVE     tinyint                      null,
    constraint product_category_ID_fk
        foreign key (IDCATEGORY) references category (ID)
);

create table orders_details
(
    ID        int auto_increment
        primary key,
    IDORD     int    null,
    IDPRODUCT int    null,
    PRICE     double null,
    QTY       int    null,
    constraint orders_details_orders_ID_fk
        foreign key (IDORD) references orders (ID),
    constraint orders_details_product_ID_fk
        foreign key (IDPRODUCT) references product (ID)
);

create table product_images
(
    ID         int auto_increment
        primary key,
    NAME       varchar(250) charset utf8mb3 null,
    URL        varchar(250) charset utf8mb3 null,
    ID_PRODUCT int                          null,
    constraint product_images_product_ID_fk
        foreign key (ID_PRODUCT) references product (ID)
);

create table transport_method
(
    ID           int auto_increment
        primary key,
    NAME         varchar(250) charset utf8mb3 null,
    NOTES        text                         null,
    CREATED_DATE timestamp                    null,
    UPDATED_DATE timestamp                    null,
    ISACTIVE     tinyint                      null
);

create table orders_transport
(
    ID          int auto_increment
        primary key,
    IDORD       int null,
    IDTRANSPORT int null,
    TOTAL       int null,
    NOTES       int null,
    constraint orders_transport_orders_ID_fk
        foreign key (IDORD) references orders (ID),
    constraint orders_transport_transport_method_ID_fk
        foreign key (IDTRANSPORT) references transport_method (ID)
);
