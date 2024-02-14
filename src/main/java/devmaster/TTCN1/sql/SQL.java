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
}
