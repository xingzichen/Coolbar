package com.cmbchina.coolbar.models;

/**
 * Created by liang on 7/9/15.
 */
public class Commodity {

    /**
     * 商品id
     */
    private String id;
    /**
     * 物品名称
     */
    private String name;
    /**
     * 单价
     */
    private Float price;
    /**
     * 度量单位
     */
    private String classifier;
    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 图片资源
     */
    private int imageResource;

    //getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
