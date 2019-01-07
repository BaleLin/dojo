import org.junit.Assert;
import org.junit.Test;

public class ReceiptHandlerTest {
@Test
public void should_return_right_receipt_when_given_item_is_pen_quantity_is_1_unit_price_is_2_state_code_is_UT(){
  ReceiptItem receiptItem = new ReceiptItem("pen",1,2.0);
  ReceiptHandler receiptHandler = new ReceiptHandler();
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
}
