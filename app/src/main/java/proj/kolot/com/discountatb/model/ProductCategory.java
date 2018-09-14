package proj.kolot.com.discountatb.model;


import android.os.Parcel;
import android.os.Parcelable;

public class ProductCategory implements Parcelable {

    private int value;
    private String description;

    public ProductCategory(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(value);
        out.writeString(description);

    }

    public static final Parcelable.Creator<ProductCategory> CREATOR = new Parcelable.Creator<ProductCategory>(){
        public ProductCategory createFromParcel(Parcel in) {
            return new ProductCategory(in);
        }
        public ProductCategory[] newArray(int size) {
            return new ProductCategory[size];
        }
    };

    private ProductCategory(Parcel in) {
        value = in.readInt();
        description = in.readString();
    }
}
