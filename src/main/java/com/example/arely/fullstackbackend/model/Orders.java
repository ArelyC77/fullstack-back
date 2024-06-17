package com.example.arely.fullstackbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity//makes user table

public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    private String dateRequestReceived;//was username
    private String department;//was name
    private Integer shoppingCartNo;//was email
    private Integer poNo; //main table
    private String vendorName; //main table
    private String requestorName; //main table
    private String description; //main table
    private Double amount; //main table
    private Integer fundNo;//main table
    //-----------------------------------------------------------
    private String datePOCreated;
    private Integer vendorNo;
    private String shipTo;
    private Integer objectNo;
    private Integer locationNo;
    private Integer programNo;
    private Integer functionNo;
    private String sapOrCreditCard;
    private String dateApproved;
    private String dateGottardiApproved;
    private String processorName;
    private String statusGoodReceipts;
    private String invoiceStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateRequestReceived() {
        return dateRequestReceived;
    }

    public void setDateRequestReceived(String dateRequestReceived) {
        this.dateRequestReceived = dateRequestReceived;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getShoppingCartNo() {
        return shoppingCartNo;
    }

    public void setShoppingCartNo(Integer shoppingCartNo) {
        this.shoppingCartNo = shoppingCartNo;
    }

    public Integer getPoNo() {
        return poNo;
    }

    public void setPoNo(Integer poNo) {
        this.poNo = poNo;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getRequestorName() {
        return requestorName;
    }

    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getFundNo() {
        return fundNo;
    }

    public void setFundNo(Integer fundNo) {
        this.fundNo = fundNo;
    }

    public String getDatePOCreated() {
        return datePOCreated;
    }

    public void setDatePOCreated(String datePOCreated) {
        this.datePOCreated = datePOCreated;
    }

    public Integer getVendorNo() {
        return vendorNo;
    }

    public void setVendorNo(Integer vendorNo) {
        this.vendorNo = vendorNo;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public Integer getObjectNo() {
        return objectNo;
    }

    public void setObjectNo(Integer objectNo) {
        this.objectNo = objectNo;
    }

    public Integer getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(Integer locationNo) {
        this.locationNo = locationNo;
    }

    public Integer getProgramNo() {
        return programNo;
    }

    public void setProgramNo(Integer programNo) {
        this.programNo = programNo;
    }

    public Integer getFunctionNo() {
        return functionNo;
    }

    public void setFunctionNo(Integer functionNo) {
        this.functionNo = functionNo;
    }

    public String getSapOrCreditCard() {
        return sapOrCreditCard;
    }

    public void setSapOrCreditCard(String sapOrCreditCard) {
        this.sapOrCreditCard = sapOrCreditCard;
    }

    public String getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(String dateApproved) {
        this.dateApproved = dateApproved;
    }

    public String getDateGottardiApproved() {
        return dateGottardiApproved;
    }

    public void setDateGottardiApproved(String dateGottardiApproved) {
        this.dateGottardiApproved = dateGottardiApproved;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    public String getStatusGoodReceipts() {
        return statusGoodReceipts;
    }

    public void setStatusGoodReceipts(String statusGoodReceipts) {
        this.statusGoodReceipts = statusGoodReceipts;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

}
