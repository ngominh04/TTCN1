package devmaster.TTCN1.sql;

public class SQL {
    public static final String TRANGCHU="select * from product where ISACTIVE = 1 and ISDELETE = 1";

    public static final String TRANGCHUCHITIET="select id, name, description, notes, image, idcategory, price, quatity, created_date, updated_date, isactive, isdelete " +
            "from product where ID = ?";
    public static final String ALL_PRODUCT_IMAGE="select id, name, url from product_images where ID_PRODUCT = ?";
    public static final String  PRICE_DOWN="select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "order by PRICE desc";
    public static final String  PRICE_UP="select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product order by PRICE asc";
    public static final String DUOI15="select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "where PRICE <= 15000000";
    public static final String PRICE_15_25="select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "where PRICE > 15000000 and PRICE <= 25000000";
    public static final String PRICE_25_35="select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "where PRICE > 25000000 and PRICE <= 35000000";
    public static final String PRICE_35="select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "where PRICE > 35000000";
    public static final String ACCER="" +
            "select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "where DESCRIPTION = 'accer'";
    public static final String ASUS="" +
            "select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "where DESCRIPTION = 'asus'";
    public static final String DELL="" +
            "select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "where DESCRIPTION = 'dell'";
    public static final String HP="" +
            "select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "where DESCRIPTION = 'hp'";
    public static final String LENOVO="" +
            "select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "where DESCRIPTION = 'lenovo'";
    public static final String APPLE="" +
            "select id, name, description, notes, image, idcategory,\n" +
            "       price, quatity, created_date, updated_date, isactive, isdelete\n" +
            "from product\n" +
            "where DESCRIPTION = 'apple'";
    public static final String CUSTOMER ="select *from customer where USERNAME =?";
    public static final String CUSTOMER_BY_ID ="select *from customer where ID =?";
    public static final String PHUKIEN = "select *from product where IDCATEGORY !=1;";
    public static final String CART="select * from cart where id_customer =?";
    public static final String COUNTCART="select count(id_customer) count from cart where id_customer =?";
    public static final String RECEIVER="select *from receiver where isdelete = 1 and idcus=?;";
    public static final String ALL_TRANSPORT="select * from transport_method where ISACTIVE =1 and ISDELETE = 1;";
    public static final String ALL_PAYMENT="select * from payment_method where ISACTIVE =1 and ISDELETE = 1;";
    public static final String ALL_CUSTOMER="select * from customer";
    public static final String ORDER="select *from `order` o where o.STATUS = ?";
    public static final String ORDER_STATUS_IDCUS=" " +
            "select distinct o.ID id,o.ID_ORDERS idOrder,o.STATUS status,o.ORDERS_DATE orderDate,o.NOTES notes,o.ID_CUSTOMER idCus,\n" +
            "       o.TOTAL_MONEY totalMoney, o.NAME_RECIVER nameReceiver,\n" +
            "       o.ADDRESS address,o.PHONE phone,\n" +
            "       op.NOTES notePayment,ot.NOTES noteTran,ot.TOTAL totalTran\n" +
            "       from `order` o\n" +
            "    inner JOIN material.orders_details od on o.ID = od.IDORD\n" +
            "    inner JOIN material.orders_payment op on o.ID = op.IDORD\n" +
            "    inner join material.orders_transport ot on o.ID = ot.IDORD\n" +
            "where o.ID_CUSTOMER = ? and o.STATUS = ?";
    public static final String ORDER_BY_ID="" +
            "select distinct o.ID id,o.ID_ORDERS idOrder,o.STATUS status,o.ORDERS_DATE orderDate,o.NOTES notes,o.ID_CUSTOMER idCus,\n" +
            "       o.TOTAL_MONEY totalMoney, o.NAME_RECIVER nameReceiver,\n" +
            "       o.ADDRESS address,o.PHONE phone,\n" +
            "       op.NOTES notePayment,ot.NOTES noteTran,ot.TOTAL totalTran\n" +
            "from `order` o\n" +
            "         inner JOIN material.orders_details od on o.ID = od.IDORD\n" +
            "         inner JOIN material.orders_payment op on o.ID = op.IDORD\n" +
            "         inner join material.orders_transport ot on o.ID = ot.IDORD\n" +
            "         inner join material.product p on od.IDPRODUCT = p.ID\n" +
            "where o.ID = ?";
    public static final String ORDER_DETAIL_BY_IDORDER="" +
            "select p.NAME name,pi.URL image,od.QTY quantity,od.PRICE price,od.IDPRODUCT idPro from orders_details od\n" +
            "        inner JOIN material.product p on od.IDPRODUCT = p.ID\n" +
            "        inner join material.product_images pi on p.IMAGE = pi.ID\n" +
            "where od.IDORD = ?";
    public static final String EVALUATE_IDORDER_IDCUS="" +
            "select distinct evaluate.VALUE value from evaluate\n" +
            "        inner join material.`order` o on evaluate.ID_ORDER = o.ID\n" +
            "        where ID_ORDER=? and o.ID_CUSTOMER = ?;";

    public static final String EVALUATE_PRO="" +
            "select evaluate.VALUE value,c.NAME nameCus ,o.ORDERS_DATE orderDate from evaluate\n" +
            "         inner join material.`order` o on evaluate.ID_ORDER = o.ID\n" +
            "         inner join material.customer c on o.ID_CUSTOMER = c.ID\n" +
            "         where ID_PRO =? and evaluate.ISDELETE = 1 and c.ISACTIVE =1";
}
