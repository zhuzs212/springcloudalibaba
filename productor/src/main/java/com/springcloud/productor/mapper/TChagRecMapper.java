package com.springcloud.productor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.productor.domain.TChagRec;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author zhuzishuang
* @description 针对表【t_chag_rec202011】的数据库操作Mapper
* @createDate 2022-08-23 16:21:17
* @Entity generator.domain.TChagRec202011
*/
@Repository
@Mapper
public interface TChagRecMapper extends BaseMapper<TChagRec> {


}
