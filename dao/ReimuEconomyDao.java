package com.yikuni.mc.reimueconomy.dao;

import com.yikuni.mc.reimueconomy.domain.ReimuEconomy;
import com.yikuni.mc.reimueconomy.domain.ReimuEconomyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReimuEconomyDao {
    long countByExample(ReimuEconomyExample example);

    int deleteByExample(ReimuEconomyExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(ReimuEconomy record);

    int insertSelective(ReimuEconomy record);

    List<ReimuEconomy> selectByExample(ReimuEconomyExample example);

    ReimuEconomy selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") ReimuEconomy record, @Param("example") ReimuEconomyExample example);

    int updateByExample(@Param("record") ReimuEconomy record, @Param("example") ReimuEconomyExample example);

    int updateByPrimaryKeySelective(ReimuEconomy record);

    int updateByPrimaryKey(ReimuEconomy record);
}