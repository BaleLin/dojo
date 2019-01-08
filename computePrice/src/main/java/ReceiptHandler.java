import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReceiptHandler {
  private String stateCode;
  private ArrayList<ReceiptItem> receiptItemArray = new ArrayList();
  private double totalWithoutTaxes;
  private double discout = 0;
  private double tax;
  private double addMoneyWithTax;
  private double reduceMoneyWithDiscount;
  private double actualPrice;
  private StringBuilder sb = new StringBuilder();
  private Map<String,Double> stateCodeMap = new HashMap<String,Double>();
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

    this.formatReceiptItem();
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
    tax = getTaxRate(this.getStateCode());
    addMoneyWithTax = calculateAddMoney();
    reduceMoneyWithDiscount = calculateReduceMoney();
    actualPrice = calculateActualPrice();

    this.formatReceiptsPrice();

   return sb.toString();
  }
  private double calculateAddMoney(){
    return totalWithoutTaxes*tax;
  }
  private double calculateReduceMoney(){
    return totalWithoutTaxes*discout;
  }
  private double calculateActualPrice(){
    return totalWithoutTaxes+addMoneyWithTax-reduceMoneyWithDiscount;
  }
  private double getTaxRate(String stateCode){
    stateCodeMap.put("UT",0.0685);
    stateCodeMap.put("NV",0.08);
    stateCodeMap.put("TX",0.0625);
    stateCodeMap.put("AL",0.04);
    stateCodeMap.put("CA",0.0825);
    return stateCodeMap.get(stateCode);
  }
  private void formatReceiptItem() {
    for (ReceiptItem receiptItem : receiptItemArray) {
      String itemName = receiptItem.getItemName();
      int quantity = receiptItem.getQuantity();
      double unitPrice = receiptItem.getUnitPrice();
      double singleTotalPrice = quantity * unitPrice;
      totalWithoutTaxes = totalWithoutTaxes + singleTotalPrice;
      sb.append(String.format("%s     %d   %.2f   %.2f\n", itemName, quantity, unitPrice, singleTotalPrice));
    }
  }
  private void formatReceiptsPrice(){
    sb.append("\n"+"-----------------------------------------------------"+"\n");
    sb.append(String.format("Total without taxes                               %.2f\n",totalWithoutTaxes));
    sb.append(String.format("Discount %.2f%%                                   -%.2f\n",discout*100,reduceMoneyWithDiscount));
    sb.append(String.format("Tax %.2f%%                                        +%.2f\n",tax*100,addMoneyWithTax));
    sb.append("\n"+"-----------------------------------------------------"+"\n");
    sb.append(String.format("Total price                                      %.2f\n",actualPrice));
  }

}