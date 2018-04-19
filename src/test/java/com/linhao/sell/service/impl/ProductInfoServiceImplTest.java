package com.linhao.sell.service.impl;

import com.linhao.sell.dataobject.ProductInfo;
import com.linhao.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("123123");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() {
        int page = 1,size = 20;
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = new PageRequest(page,size,sort);
        Page<ProductInfo> result = productInfoService.findAll(pageable);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = productInfoService.findByProductStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123123123");
        productInfo.setProductName("皮蛋瘦肉粥小份");
        productInfo.setProductPrice(new BigDecimal(9.9));
        productInfo.setProductStock(999);
        productInfo.setProductDescription("健康营养");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }
}