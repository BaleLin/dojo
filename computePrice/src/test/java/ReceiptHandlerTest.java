import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReceiptHandlerTest {

  private ReceiptHandler receiptHandler;

  @Before
  public void setUp(){
     receiptHandler = new ReceiptHandler();
  }

  @Test
  public void should_return_right_receipt_when_given_item_is_pen_quantity_is_1_unit_price_is_2_state_code_is_UT() {
    ReceiptItem receiptItem = new ReceiptItem("pen", 1, 2.0);

    receiptHandler.setStateCode("UT");
    String result = receiptHandler.generateReceipt(receiptItem);

    String expectResult = "pen     1   2.00   2.00\n"
        + "\n"
        + "-----------------------------------------------------\n"
        + "Total without taxes                               2.00\n"
        + "Discount 0.00%                                   -0.00\n"
        + "Tax 6.85%                                        +0.14\n"
        + "\n"
        + "-----------------------------------------------------\n"
        + "Total price                                      2.14\n";
    Assert.assertEquals(expectResult, result);
  }
  
  @Test
  public void should_return_right_receipt_when_given_item_is_pen_quantity_is_1_unit_price_is_3000_state_code_is_UT(){
    ReceiptItem receiptItem = new ReceiptItem("pen", 1, 3000);

    receiptHandler.setStateCode("UT");
    String result = receiptHandler.generateReceipt(receiptItem);

    String expectResult = "pen     1   3000.00   3000.00\n"
        + "\n"
        + "-----------------------------------------------------\n"
        + "Total without taxes                               3000.00\n"
        + "Discount 3.00%                                   -90.00\n"
        + "Tax 6.85%                                        +205.50\n"
        + "\n"
        + "-----------------------------------------------------\n"
        + "Total price                                      3115.50\n";
    Assert.assertEquals(expectResult, result);
  }

  @Test
  public void should_return_right_receipt_when_given_item_is_pen_quantity_is_1_unit_price_is_6000_state_code_is_UT(){
    ReceiptItem receiptItem = new ReceiptItem("pen", 1, 6000);

    receiptHandler.setStateCode("UT");
    String result = receiptHandler.generateReceipt(receiptItem);

    String expectResult = "pen     1   6000.00   6000.00\n"
        + "\n"
        + "-----------------------------------------------------\n"
        + "Total without taxes                               6000.00\n"
        + "Discount 5.00%                                   -300.00\n"
        + "Tax 6.85%                                        +411.00\n"
        + "\n"
        + "-----------------------------------------------------\n"
        + "Total price                                      6111.00\n";
    Assert.assertEquals(expectResult, result);
  }
}
