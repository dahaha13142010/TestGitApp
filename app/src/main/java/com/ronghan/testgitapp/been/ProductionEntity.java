package com.ronghan.testgitapp.been;

import com.admin.control.quickRv.inter.RvQuickInterface;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/30 0030 16:58
 * 修改人：Michael
 * 修改时间：2016/5/30 0030 16:58
 * 修改备注：
 */
public class ProductionEntity implements Serializable {
    /**
     * count : 6
     * data : [{"createdDate":1463383929000,"id":"4028813454b84f620154b87ab0a1000b","name":"测试产品","productCost":0,"productPics":[{"id":"4028813454b8ac9f0154b90dfb740004","name":"1463383925384.jpg","size":"21.94 KB","url":"http://192.168.1.180:8888/img/300.500.1463383925384.jpg","version":0}],"productType":{"createdDate":1463383852000,"id":"4028813454b84f620154b879847d000a","name":"测试产品类型","version":0},"remark":"用户升级","salePrice":100,"showPriority":1,"stock":100000,"version":1},{"createdDate":1464318680000,"id":"4028813454f026d20154f031dd720001","name":"马上添加","productCost":100,"productPics":[{"id":"4028813454f026d20154f031dd730002","name":"1464318676463.jpg","size":"21.94 KB","url":"http://192.168.1.180:8888/img/300.500.1464318676463.jpg","version":0},{"id":"4028813454f026d20154f031dd730003","name":"1464318676473.jpg","size":"161.47 KB","url":"http://192.168.1.180:8888/img/300.500.1464318676473.jpg","version":0}],"productType":{"createdDate":1464318620000,"id":"4028813454f026d20154f030f1920000","name":"好的","version":0},"remark":"这个商品是随时可以添加的","salePrice":1000,"showPriority":2,"stock":10000000,"version":0},{"createdDate":1464330285000,"id":"4028813454f034db0154f0e2eef2000a","name":"工作牌","productCost":1,"productPics":[{"id":"4028813454f034db0154f0e2eef2000b","name":"1464330281387.jpg","size":"171.18 KB","url":"http://192.168.1.180:8888/img/300.500.1464330281387.jpg","version":0}],"productType":{"createdDate":1463383852000,"id":"4028813454b84f620154b879847d000a","name":"测试产品类型","version":0},"remark":"就是工作牌","salePrice":10,"showPriority":2,"stock":100,"version":0},{"createdDate":1464330070000,"id":"4028813454f034db0154f0dfa7480004","name":"新加产品","productCost":100,"productPics":[{"id":"4028813454f034db0154f0dfa7490005","name":"1464330059216.jpg","size":"80.03 KB","url":"http://192.168.1.180:8888/img/300.500.1464330059216.jpg","version":0},{"id":"4028813454f034db0154f0dfa7490006","name":"1464330066779.jpg","size":"70.16 KB","url":"http://192.168.1.180:8888/img/300.500.1464330066779.jpg","version":0}],"productType":{"createdDate":1464318620000,"id":"4028813454f026d20154f030f1920000","name":"好的","version":0},"remark":"很好的","salePrice":200,"showPriority":3,"stock":1000,"version":0},{"createdDate":1464330240000,"id":"4028813454f034db0154f0e23f030007","name":"iphone","productCost":2000,"productPics":[{"id":"4028813454f034db0154f0e23f040008","name":"1464330222780.jpg","size":"110.28 KB","url":"http://192.168.1.180:8888/img/300.500.1464330222780.jpg","version":0},{"id":"4028813454f034db0154f0e23f040009","name":"1464330222797.jpg","size":"21.94 KB","url":"http://192.168.1.180:8888/img/300.500.1464330222797.jpg","version":0}],"productType":{"createdDate":1463383852000,"id":"4028813454b84f620154b879847d000a","name":"测试产品类型","version":0},"remark":"好产品","salePrice":6000,"showPriority":3,"stock":10000,"version":0},{"createdDate":1464330021000,"id":"4028813454f034db0154f0dee98a0002","name":"测试产品1","productCost":10,"productPics":[{"id":"4028813454f034db0154f0dee9a20003","name":"1464330018400.jpg","size":"39.95 KB","url":"http://192.168.1.180:8888/img/300.500.1464330018400.jpg","version":0}],"productType":{"createdDate":1464329968000,"id":"4028813454f034db0154f0de19700000","name":"测试产品类型1","version":0},"remark":"产品还是很好的","salePrice":100,"showPriority":4,"stock":1000,"version":0}]
     * page : 1
     * pageSize : 8
     */

    private int count;
    private int page;
    private int pageSize;
    /**
     * createdDate : 1463383929000
     * id : 4028813454b84f620154b87ab0a1000b
     * name : 测试产品
     * productCost : 0
     * productPics : [{"id":"4028813454b8ac9f0154b90dfb740004","name":"1463383925384.jpg","size":"21.94 KB","url":"http://192.168.1.180:8888/img/300.500.1463383925384.jpg","version":0}]
     * productType : {"createdDate":1463383852000,"id":"4028813454b84f620154b879847d000a","name":"测试产品类型","version":0}
     * remark : 用户升级
     * salePrice : 100
     * showPriority : 1
     * stock : 100000
     * version : 1
     */

    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProductionEntity{" +
                "count=" + count +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", data=" + data.toString() +
                '}';
    }

    public static class DataBean implements RvQuickInterface{
        private long createdDate;
        private String id;
        private String name;
        private int productCost;
        /**
         * createdDate : 1463383852000
         * id : 4028813454b84f620154b879847d000a
         * name : 测试产品类型
         * version : 0
         */

        private ProductTypeBean productType;
        private String remark;
        private int salePrice;
        private int showPriority;
        private int stock;
        private int version;
        /**
         * id : 4028813454b8ac9f0154b90dfb740004
         * name : 1463383925384.jpg
         * size : 21.94 KB
         * url : http://192.168.1.180:8888/img/300.500.1463383925384.jpg
         * version : 0
         */

        private List<ProductPicsBean> productPics;

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

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

        public int getProductCost() {
            return productCost;
        }

        public void setProductCost(int productCost) {
            this.productCost = productCost;
        }

        public ProductTypeBean getProductType() {
            return productType;
        }

        public void setProductType(ProductTypeBean productType) {
            this.productType = productType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(int salePrice) {
            this.salePrice = salePrice;
        }

        public int getShowPriority() {
            return showPriority;
        }

        public void setShowPriority(int showPriority) {
            this.showPriority = showPriority;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public List<ProductPicsBean> getProductPics() {
            return productPics;
        }

        public void setProductPics(List<ProductPicsBean> productPics) {
            this.productPics = productPics;
        }

        @Override
        public int getRvType() {
            return 0;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "createdDate=" + createdDate +
                    ", id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", productCost=" + productCost +
                    ", productType=" + productType.toString() +
                    ", remark='" + remark + '\'' +
                    ", salePrice=" + salePrice +
                    ", showPriority=" + showPriority +
                    ", stock=" + stock +
                    ", version=" + version +
                    ", productPics=" + productPics.toString() +
                    '}';
        }

        public static class ProductTypeBean {
            private long createdDate;
            private String id;
            private String name;
            private int version;

            public long getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(long createdDate) {
                this.createdDate = createdDate;
            }

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

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            @Override
            public String toString() {
                return "ProductTypeBean{" +
                        "createdDate=" + createdDate +
                        ", id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", version=" + version +
                        '}';
            }
        }

        public static class ProductPicsBean {
            private String id;
            private String name;
            private String size;
            private String url;
            private int version;

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

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            @Override
            public String toString() {
                return "ProductPicsBean{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", size='" + size + '\'' +
                        ", url='" + url + '\'' +
                        ", version=" + version +
                        '}';
            }
        }
    }
}
