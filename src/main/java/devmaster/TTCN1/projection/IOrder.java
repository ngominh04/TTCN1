package devmaster.TTCN1.projection;

public interface IOrder {
    Integer getId();
    String getIdOrder();
    Integer getIdCus();
    Integer getStatus();
    String getOrderDate();
    String getNotes();
    double getTotalMoney();
    String getNameReceiver();
    String getAddress();
    String getPhone();
    String getNamepro();
    Integer getQuantity();
    String getNotePayment();
    String getNoteTran();
    double getTotalTran();

}
