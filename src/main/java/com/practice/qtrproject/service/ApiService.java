package com.practice.qtrproject.service;

import com.practice.qtrproject.dto.request.PageParamDto;
import com.practice.qtrproject.dto.request.ProductDto;
import com.practice.qtrproject.dto.request.SearchParamDto;
import com.practice.qtrproject.dto.response.ResultProductDetailInfoDto;
import com.practice.qtrproject.mapper.ProductMapper;
import com.practice.qtrproject.model.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ApiService {

    private final ProductMapper mapper;

//생성자 주입
    public ApiService(ProductMapper mapper){
        this.mapper = mapper;
    }
    @Transactional(rollbackFor = Exception.class)
    public void saveProduct(ProductDto dto) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(dto,product);
        log.info("product : {}", product);

        mapper.saveProduct(product);

    }
    public List<Product> getList(SearchParamDto searchParamDto, PageParamDto pageParamDto) {

        return mapper.getList(searchParamDto,pageParamDto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateDelDate(Integer productNo){
        try {
            mapper.updateDelDate(productNo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("error : {}",e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(Integer productNo)  {
        try {
            mapper.deleteProduct(productNo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("error : {}",e.getMessage());
        }
    }

    public ResultProductDetailInfoDto getDetailInfo(Integer productNo) {
        ResultProductDetailInfoDto result = ResultProductDetailInfoDto.builder().build();
        try {
            result = mapper.getDetailInfo(productNo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("error : {}", e.getMessage());
        }

        return result;
    }
}
