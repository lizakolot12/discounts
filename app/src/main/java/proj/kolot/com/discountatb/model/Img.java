package proj.kolot.com.discountatb.model;

import com.google.gson.annotations.SerializedName;

public class Img {

    @SerializedName("hires")
    private String hires;

    @SerializedName("hires_preview")
    private String hiresPreview;

    public String getHires() {
        return hires;
    }

    public void setHires(String hires) {
        this.hires = hires;
    }

    public String getHiresPreview() {
        return hiresPreview;
    }

    public void setHiresPreview(String hiresPreview) {
        this.hiresPreview = hiresPreview;
    }

}