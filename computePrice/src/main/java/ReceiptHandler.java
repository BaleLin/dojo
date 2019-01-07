public class ReceiptHandler {
  private String stateCode;

  public String getStateCode() {
    return stateCode;
  }

  public void setStateCode(String stateCode) {
    this.stateCode = stateCode;
  }
  public String generateReceipt(ReceiptItem receiptItem){
    StringBuilder sb = new StringBuilder();
    String itemName = receiptItem.getItemName();
    int quantity = receiptItem.getQuantity();
    double unitPrice = receiptItem.getUnitPrice();
    double totalWithoutTaxes = quantity*unitPrice;
    double discout = 0;
    double tax = 0;
    if (totalWithoutTaxes>1000){
      discout = 0.03;
    }
    if (stateCode.equals("UT")){
      tax = 0.0685;
    }
    double addMoneyWithTax = totalWithoutTaxes*tax;
    double reduceMoneyWithDiscount = totalWithoutTaxes*discout;
    double actualPrice = totalWithoutTaxes+addMoneyWithTax-reduceMoneyWithDiscount;
    sb.append(String.format("%s     %d   %.2f   %.2f\n",itemName,quantity,unitPrice,totalWithoutTaxes));
    sb.append("\n"+"-----------------------------------------------------"+"\n");
    sb.append(String.format("Total without taxes                               %.2f\n",totalWithoutTaxes));
    sb.append(String.format("Discount %.2f%%                                   -%.2f\n",discout*100,reduceMoneyWithDiscount));
    sb.append(String.format("Tax %.2f%%                                        +%.2f\n",tax*100,addMoneyWithTax));
    sb.append("\n"+"-----------------------------------------------------"+"\n");
    sb.append(String.format("Total price                                      %.2f\n",actualPrice));
    return sb.toString();
  }
}