package com.ronghan.testgitapp.been;

import com.admin.control.quickRv.inter.RvQuickInterface;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class GoodsEntities implements Serializable, RvQuickInterface {

    @Override
    public int getRvType() {
        return 0;
    }
    /**
     * showPriority : 3
     * createdDate : 1464330070000
     * productCost : 100.0
     * salePrice : 200.0
     * name : 新加产品
     * remark : 很好的
     * id : 4028813454f034db0154f0dfa7480004
     * stock : 991
     * productPics : [{"size":"80.03 KB","name":"1464330059216.jpg","id":"4028813454f034db0154f0dfa7490005","version":0,"url":"http://192.168.1.180:8888/img/300.500.1464330059216.jpg"},{"size":"70.16 KB","name":"1464330066779.jpg","id":"4028813454f034db0154f0dfa7490006","version":0,"url":"http://192.168.1.180:8888/img/300.500.1464330066779.jpg"}]
     * version : 9
     * productType : {"createdDate":1464318620000,"name":"好的","id":"4028813454f026d20154f030f1920000","version":0}
     */
    private int showPriority;
    private String createdDate;
    private double productCost;
    private double salePrice;
    private String name;
    private String remark;
    private String id;
    private int stock;
    private List<ProductPicsEntity> productPics;
    private int version;
    private ProductTypeEntity productType;

    public void setShowPriority(int showPriority) {
        this.showPriority = showPriority;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setProductPics(List<ProductPicsEntity> productPics) {
        this.productPics = productPics;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setProductType(ProductTypeEntity productType) {
        this.productType = productType;
    }

    public int getShowPriority() {
        return showPriority;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public double getProductCost() {
        return productCost;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public String getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public List<ProductPicsEntity> getProductPics() {
        return productPics;
    }

    public int getVersion() {
        return version;
    }

    public ProductTypeEntity getProductType() {
        return productType;
    }


    public class ProductPicsEntity  implements Serializable {
        /**
         * size : 80.03 KB
         * name : 1464330059216.jpg
         * id : 4028813454f034db0154f0dfa7490005
         * version : 0
         * url : http://192.168.1.180:8888/img/300.500.1464330059216.jpg
         */
        private String size;
        private String name;
        private String id;
        private int version;
        private String url;

        public void setSize(String size) {
            this.size = size;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSize() {
            return size;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public int getVersion() {
            return version;
        }

        public String getUrl() {
            return url;
        }
    }

    public class ProductTypeEntity  implements Serializable {
        /**
         * createdDate : 1464318620000
         * name : 好的
         * id : 4028813454f026d20154f030f1920000
         * version : 0
         */
        private String createdDate;
        private String name;
        private String id;
        private int version;

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public int getVersion() {
            return version;
        }
    }

//     [{"createdDate":1464330070000,"id":"4028813454f034db0154f0dfa7480004","name":"新加产品","productCost":100.00,"productPics":[{"id":"4028813454f034db0154f0dfa7490005","name":"1464330059216.jpg","size":"80.03 KB","url":"http://192.168.1.180:8888/img/300.500.1464330059216.jpg","version":0},{"id":"4028813454f034db0154f0dfa7490006","name":"1464330066779.jpg","size":"70.16 KB","url":"http://192.168.1.180:8888/img/300.500.1464330066779.jpg","version":0}],"productType":{"createdDate":1464318620000,"id":"4028813454f026d20154f030f1920000","name":"好的","version":0},"remark":"很好的","salePrice":200.00,"showPriority":3,"stock":991,"version":9}]


}
