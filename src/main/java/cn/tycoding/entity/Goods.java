package cn.tycoding.entity;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @auther TyCoding
 * @date 2018/9/19
 */
public class Goods implements Serializable {


    //配合Solr的使用，为schema.xml中定义的域添加注解。如果schema.xml中域名称和实体类属性名称不同的话在@Field中标注
    @Field
    private Long id; //商品ID
    @Field("item_title")
    private String title; //商品标题
    @Field("item_price")
    private BigDecimal price; //商品价格
    @Field("item_image")
    private String image; //商品图片
    @Field("item_category")
    private String category; //商品类别
    @Field("item_brand")
    private String brand; //商品品牌
    @Field("item_seller")
    private String seller; //商品卖家

    public Goods() {
    }

    public Goods(Long id, String title, BigDecimal price, String category, String brand, String seller) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.seller = seller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
