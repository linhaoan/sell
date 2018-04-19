package com.linhao.sell.repository;

import com.linhao.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void testRun(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void testAdd(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("好吃的桂林米粉");
        productCategory.setCategoryType(1);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public  void testSave(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(2);
        productCategory.setCategoryName("奇怪的桂林米粉111");
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void testFindByCategoryTypeIn() {
        List list = Arrays.asList(2,3,4);
        productCategoryRepository.findByCategoryTypeIn(list);

    }
}