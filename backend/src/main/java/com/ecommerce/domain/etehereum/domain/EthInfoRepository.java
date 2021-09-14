package com.ecommerce.domain.etehereum.domain;

import com.ecommerce.domain.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class EthInfoRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public EthInfo get(String ethUrl) {
        String sql = "SELECT * FROM ETH_INFO WHERE net_url=?";
        try {
            return this.jdbcTemplate.queryForObject(sql,
                    new Object[]{ethUrl}, (rs, rowNum) -> new EthInfo(rs.getString("net_url"),
                            rs.getString("latest_bno")));
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }

    public void put(String ethUrl, String blockNumber) {
        String sql = "UPDATE ETH_INFO SET latest_bno=? WHERE net_url=?";
        try {
            this.jdbcTemplate.update(sql,
                    new Object[]{blockNumber, ethUrl});
        } catch (Exception e) {
            throw new RepositoryException(e, e.getMessage());
        }
    }
}
