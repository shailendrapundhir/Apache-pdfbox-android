package com.joshsoftware.googlepdf.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shekhar on 04/07/16.
 */
public class QuotationPremiumDetail implements Parcelable {

    private String idv;
    private String ncbValue;

    private boolean isExternalFitted;
    private String fuelType;

    private double biFuelCharge;
    private double externalCngKitCharge;

    private double addOnPremium;
    private double liabilityPremium;

    private double odPremium;
    private double commercialDiscount;
    private double ncbDiscount;
    private double totalOdPremium;

    private double basicPremium;
    private double serviceTaxAmount;
    private double totalPremium;
    private double totalPremiumWithoutPremium;

    private double odRate;
    private double paPremium;
    private double commercialOdpremium;




    public QuotationPremiumDetail() {

    }

    protected QuotationPremiumDetail(Parcel in) {
        idv = in.readString();
        ncbValue = in.readString();
        isExternalFitted = in.readByte() != 0;
        fuelType = in.readString();
        biFuelCharge = in.readDouble();
        externalCngKitCharge = in.readDouble();
        addOnPremium = in.readDouble();
        liabilityPremium = in.readDouble();
        odPremium = in.readDouble();
        commercialDiscount = in.readDouble();
        ncbDiscount = in.readDouble();
        totalOdPremium = in.readDouble();
        basicPremium = in.readDouble();
        serviceTaxAmount = in.readDouble();
        totalPremium = in.readDouble();
        totalPremiumWithoutPremium = in.readDouble();
        odRate = in.readDouble();
        paPremium = in.readDouble();
        commercialOdpremium = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idv);
        dest.writeString(ncbValue);
        dest.writeByte((byte) (isExternalFitted ? 1 : 0));
        dest.writeString(fuelType);
        dest.writeDouble(biFuelCharge);
        dest.writeDouble(externalCngKitCharge);
        dest.writeDouble(addOnPremium);
        dest.writeDouble(liabilityPremium);
        dest.writeDouble(odPremium);
        dest.writeDouble(commercialDiscount);
        dest.writeDouble(ncbDiscount);
        dest.writeDouble(totalOdPremium);
        dest.writeDouble(basicPremium);
        dest.writeDouble(serviceTaxAmount);
        dest.writeDouble(totalPremium);
        dest.writeDouble(totalPremiumWithoutPremium);
        dest.writeDouble(odRate);
        dest.writeDouble(paPremium);
        dest.writeDouble(commercialOdpremium);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuotationPremiumDetail> CREATOR = new Creator<QuotationPremiumDetail>() {
        @Override
        public QuotationPremiumDetail createFromParcel(Parcel in) {
            return new QuotationPremiumDetail(in);
        }

        @Override
        public QuotationPremiumDetail[] newArray(int size) {
            return new QuotationPremiumDetail[size];
        }
    };

    public boolean isExternalFitted() {
        return isExternalFitted;
    }

    public void setExternalFitted(boolean externalFitted) {
        isExternalFitted = externalFitted;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getBiFuelCharge() {
        return biFuelCharge;
    }

    public void setBiFuelCharge(double biFuelCharge) {
        this.biFuelCharge = biFuelCharge;
    }

    public double getExternalCngKitCharge() {
        return externalCngKitCharge;
    }

    public void setExternalCngKitCharge(double externalCngKitCharge) {
        this.externalCngKitCharge = externalCngKitCharge;
    }

    public double getOdRate() {
        return odRate;
    }

    public void setOdRate(double odRate) {
        this.odRate = odRate;
    }

    public double getPaPremium() {
        return paPremium;
    }

    public void setPaPremium(double paPremium) {
        this.paPremium = paPremium;
    }

    public double getCommercialOdpremium() {
        return commercialOdpremium;
    }

    public void setCommercialOdpremium(double commercialOdpremium) {
        this.commercialOdpremium = commercialOdpremium;
    }

    public double getTotalPremiumWithoutPremium() {
        return totalPremiumWithoutPremium;
    }

    public void setTotalPremiumWithoutPremium(double totalPremiumWithoutPremium) {
        this.totalPremiumWithoutPremium = totalPremiumWithoutPremium;
    }

    public String getIdv() {
        return idv;
    }

    public void setIdv(String idv) {
        this.idv = idv;
    }

    public String getNcbValue() {
        return ncbValue;
    }

    public void setNcbValue(String ncbValue) {
        this.ncbValue = ncbValue;
    }

    public double getAddOnPremium() {
        return addOnPremium;
    }

    public void setAddOnPremium(double addOnPremium) {
        this.addOnPremium = addOnPremium;
    }

    public double getLiabilityPremium() {
        return liabilityPremium;
    }

    public void setLiabilityPremium(double liabilityPremium) {
        this.liabilityPremium = liabilityPremium;
    }

    public double getOdPremium() {
        return odPremium;
    }

    public void setOdPremium(double odPremium) {
        this.odPremium = odPremium;
    }

    public double getCommercialDiscount() {
        return commercialDiscount;
    }

    public void setCommercialDiscount(double commercialDiscount) {
        this.commercialDiscount = commercialDiscount;
    }

    public double getNcbDiscount() {
        return ncbDiscount;
    }

    public void setNcbDiscount(double ncbDiscount) {
        this.ncbDiscount = ncbDiscount;
    }

    public double getTotalOdPremium() {
        return totalOdPremium;
    }

    public void setTotalOdPremium(double totalOdPremium) {
        this.totalOdPremium = totalOdPremium;
    }

    public double getBasicPremium() {
        return basicPremium;
    }

    public void setBasicPremium(double basicPremium) {
        this.basicPremium = basicPremium;
    }

    public double getServiceTaxAmount() {
        return serviceTaxAmount;
    }

    public void setServiceTaxAmount(double serviceTaxAmount) {
        this.serviceTaxAmount = serviceTaxAmount;
    }

    public double getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(double totalPremium) {
        this.totalPremium = totalPremium;
    }
}