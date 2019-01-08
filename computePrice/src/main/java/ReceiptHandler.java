import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReceiptHandler {
  private String stateCode;
  private ArrayList<ReceiptItem> receiptItemArray = new ArrayList();

  public String getStateCode() {
    return stateCode;
  }

  public void setStateCode(String stateCode) {
    this.stateCode = stateCode;
  }

  public ArrayList<ReceiptItem> getReceiptItemArray() {
    return receiptItemArray;
  }

  public void setReceiptItemArray(ArrayList<ReceiptItem> receiptItemArray) {
    this.receiptItemArray = receiptItemArray;
  }

  public void addItem(ReceiptItem receiptItem){
    this.receiptItemArray.add(receiptItem);
  }
  public String generateReceipt(){
    double totalWithoutTaxes = 0;
    StringBuilder sb = new StringBuilder();
    for (ReceiptItem receiptItem:receiptItemArray){
      String itemName = receiptItem.getItemName();
      int quantity = receiptItem.getQuantity();
      double unitPrice = receiptItem.getUnitPrice();
      double singleTotalPrice = quantity*unitPrice;
      totalWithoutTaxes = totalWithoutTaxes + singleTotalPrice;
      sb.append(String.format("%s     %d   %.2f   %.2f\n",itemName,quantity,unitPrice,singleTotalPrice));
    }
    double discout = 0;
    double tax = 0;
    if (totalWithoutTaxes>1000){
      discout = 0.03;
    }if(totalWithoutTaxes>5000){
      discout = 0.05;
    }if(totalWithoutTaxes>7000){
      discout = 0.07;
    }if (totalWithoutTaxes>10000){
      discout = 0.10;
    }if (totalWithoutTaxes>50000){
      discout = 0.15;
    }
    if (stateCode.equals("UT")){
      tax = 0.0685;
    } if (stateCode.equals("NV")){
      tax = 0.08;
    } if (stateCode.equals("TX")){
      tax = 0.0625;
    } if (stateCode.equals("AL")){
      tax = 0.04;
    }if (stateCode.equals("CA")){
      tax = 0.0825;
    }
    double addMoneyWithTax = totalWithoutTaxes*tax;
    double reduceMoneyWithDiscount = totalWithoutTaxes*discout;
    double actualPrice = totalWithoutTaxes+addMoneyWithTax-reduceMoneyWithDiscount;
    sb.append("\n"+"-----------------------------------------------------"+"\n");
    sb.append(String.format("Total without taxes                               %.2f\n",totalWithoutTaxes));
    sb.append(String.format("Discount %.2f%%                                   -%.2f\n",discout*100,reduceMoneyWithDiscount));
    sb.append(String.format("Tax %.2f%%                                        +%.2f\n",tax*100,addMoneyWithTax));
    sb.append("\n"+"-----------------------------------------------------"+"\n");
    sb.append(String.format("Total price                                      %.2f\n",actualPrice));
    return sb.toString();
  }
}