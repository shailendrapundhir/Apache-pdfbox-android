package com.joshsoftware.googlepdf.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shekhar on 03/07/16.
 */
public class VendorQuotation implements Parcelable {

    private String vendorId;
    private String vendorName;
    private String discount;
    private String addOnAmount;
    private String ageWisePrice;
    private String perPassengerRate;
    private double externalCngKitRate;
    private double companyCngKitRate;

    private QuotationPremiumDetail premiumDetail;

    public VendorQuotation() {

    }

    protected VendorQuotation(Parcel in) {
        vendorId = in.readString();
        vendorName = in.readString();
        discount = in.readString();
        addOnAmount = in.readString();
        ageWisePrice = in.readString();
        perPassengerRate = in.readString();
        externalCngKitRate = in.readDouble();
        companyCngKitRate = in.readDouble();
        premiumDetail = in.readParcelable(QuotationPremiumDetail.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vendorId);
        dest.writeString(vendorName);
        dest.writeString(discount);
        dest.writeString(addOnAmount);
        dest.writeString(ageWisePrice);
        dest.writeString(perPassengerRate);
        dest.writeDouble(externalCngKitRate);
        dest.writeDouble(companyCngKitRate);
        dest.writeParcelable(premiumDetail, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VendorQuotation> CREATOR = new Creator<VendorQuotation>() {
        @Override
        public VendorQuotation createFromParcel(Parcel in) {
            return new VendorQuotation(in);
        }

        @Override
        public VendorQuotation[] newArray(int size) {
            return new VendorQuotation[size];
        }
    };

    public double getExternalCngKitRate() {
        return externalCngKitRate;
    }

    public void setExternalCngKitRate(double externalCngKitRate) {
        this.externalCngKitRate = externalCngKitRate;
    }

    public double getCompanyCngKitRate() {
        return companyCngKitRate;
    }

    public void setCompanyCngKitRate(double companyCngKitRate) {
        this.companyCngKitRate = companyCngKitRate;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getPerPassengerRate() {
        return perPassengerRate;
    }

    public void setPerPassengerRate(String perPassengerRate) {
        this.perPassengerRate = perPassengerRate;
    }

    public QuotationPremiumDetail getPremiumDetail() {
        return premiumDetail;
    }

    public void setPremiumDetail(QuotationPremiumDetail premiumDetail) {
        this.premiumDetail = premiumDetail;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getAddOnAmount() {
        return addOnAmount;
    }

    public void setAddOnAmount(String addOnAmount) {
        this.addOnAmount = addOnAmount;
    }

    public String getAgeWisePrice() {
        return ageWisePrice;
    }

    public void setAgeWisePrice(String ageWisePrice) {
        this.ageWisePrice = ageWisePrice;
    }
}
