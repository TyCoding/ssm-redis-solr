import cn.tycoding.entity.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther TyCoding
 * @date 2018/9/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-solr.xml")
public class TestSolrTemplate {

    @Autowired
    private SolrTemplate solrTemplate;

    //添加
    @Test
    public void testAdd() {
        Goods goods = new Goods(1L, "IPhone SE", BigDecimal.valueOf(120), "手机", "Apple", "Apple");
        solrTemplate.saveBean(goods);
        solrTemplate.commit(); //提交
    }

    //按主键查询
    @Test
    public void testFindById() {
        Goods goods = solrTemplate.getById(1, Goods.class);
        System.out.println("--------" + goods.getTitle());
    }

    //按主键删除
    @Test
    public void testDeleteById() {
        solrTemplate.deleteById("1");
        solrTemplate.commit(); //提交
    }

    @Test
    public void testDelete() {
        for (int i = 0; i <= 100; i++) {
            solrTemplate.deleteById(String.valueOf(i));
            solrTemplate.commit();
        }
    }

    //批量插入值
    @Test
    public void testAddList() {
        List<Goods> list = new ArrayList<Goods>();

        //循环插入100条数据
        for (int i = 0; i < 100; i++) {
            Goods goods = new Goods(i + 1L, "华为Mate" + i, BigDecimal.valueOf(2000 + i), "手机", "手机", "华为专卖店");
            list.add(goods);
        }

        solrTemplate.saveBeans(list); //添加集合对象，调用saveBeans()；添加普通对象类型数据，使用saveBean();
        solrTemplate.commit(); //提交
    }

    //分页查询
    @Test
    public void testPageQuery() {
        Query query = new SimpleQuery("*:*");
        query.setOffset(20); //开始索引（默认0）
        query.setRows(20); //每页记录数（默认10）
        ScoredPage<Goods> page = solrTemplate.queryForPage(query, Goods.class);
        System.out.println("总记录数：" + page.getTotalElements());
        List<Goods> list = page.getContent();
        showList(list);
    }

    //显示每页的数据
    private void showList(List<Goods> list) {
        System.out.println("------------开始--------------");
        for (Goods goods : list) {
            System.out.println(goods.getTitle() + goods.getPrice());
        }
        System.out.println("------------结束--------------");
    }

    //条件查询
    @Test
    public void testPageQueryMutil() {
        Query query = new SimpleQuery("*:*");
        Criteria criteria = new Criteria("item_title").contains("2");
        criteria = criteria.and("item_title").contains("5");
        query.addCriteria(criteria);

        ScoredPage<Goods> page = solrTemplate.queryForPage(query, Goods.class);
        System.out.println("总记录数：" + page.getTotalElements());
        List<Goods> list = page.getContent();
        showList(list);
    }

    @Test
    public void testFilterQuery() {
        Query query = new SimpleQuery();
        Map<String, Object> map = new HashMap();
        map.put("keywords", "华为");
//        map.put("keywords","");
//        map.put("brand","苹果");
        map.put("brand", "");

        //空格处理
//        String keywords= (String)map.get("keywords");
//        map.put("keywords", keywords.replace(" ", ""));//关键字去掉空格
//        System.out.println(map.get("keywords"));
//        if (map.get("keywords"))
        Criteria criteria = new Criteria("item_keywords");
        if (!map.get("keywords").equals("") && map.get("keywords") != null){
            criteria.is(map.get("keywords"));
        }
        query.addCriteria(criteria);
//        Criteria criteria = new Criteria("item_keywords").is(map.get("keywords"));
//        query.addCriteria(criteria);

        if (!map.get("brand").equals("") && map.get("brand") != null) {
            System.out.println("进入了....");
            FilterQuery filterQuery = new SimpleFilterQuery();
            Criteria filterCriteria = new Criteria("item_brand").is(map.get("brand"));
            filterQuery.addCriteria(filterCriteria);
            query.addFilterQuery(filterQuery);
        }


        ScoredPage<Goods> page = solrTemplate.queryForPage(query, Goods.class);
        List<Goods> content = page.getContent();
        showList(content);
    }


    //查询所有，默认10条数据
    @Test
    public void findAll(){
        Query query = new SimpleQuery("*:*");
        ScoredPage<Goods> goods = solrTemplate.queryForPage(query, Goods.class);
        List<Goods> content = goods.getContent();
        showList(content);
    }

    //删除所有
    @Test
    public void deleteAll(){
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
    }
}
