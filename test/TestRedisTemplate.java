import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

/**
 * @auther TyCoding
 * @date 2018/9/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring*.xml"})
public class TestRedisTemplate {

    @Autowired
    private RedisTemplate redisTemplate;



    //=================值类型=================
    //设置值
    @Test
    public void setValue(){
        redisTemplate.boundValueOps("name").set("tycoding");
    }

    //查询值
    @Test
    public void getValue(){
        Object name = redisTemplate.boundValueOps("name").get();
        System.out.println(name);
    }

    //删除值
    @Test
    public void deleteValue(){
        redisTemplate.delete("name");
    }

    //==================Set类型=================
    //插入值
    @Test
    public void setValueBySet(){
        redisTemplate.boundSetOps("nameset").add("tycoding");
        redisTemplate.boundSetOps("nameset").add("涂陌");
    }

    //查询值
    @Test
    public void getValueBySet(){
        Set nameset = redisTemplate.boundSetOps("nameset").members();
        System.out.println(nameset);
    }

    //删除集合中的某一个值
    @Test
    public void deleteValueBySet(){
        redisTemplate.boundSetOps("nameset").remove("涂陌");
    }

    //删除整个集合
    @Test
    public void deleteAllValueByset(){
        redisTemplate.delete("nameset");
    }


    //==================List类型操作=================
    //右压栈：后添加的对象排在后边
    @Test
    public void setRightValueByList(){
        redisTemplate.boundListOps("namelist").rightPush("tycoding");
        redisTemplate.boundListOps("namelist").rightPush("涂陌");
    }

    //显示右压栈的集合
    @Test
    public void getRightValueByListI(){
        List namelist = redisTemplate.boundListOps("namelist").range(0, 10);
        System.out.println(namelist);
    }

    //左压栈：后添加的对象排在前面
    @Test
    public void setLeftValueByList(){
        redisTemplate.boundListOps("namelist2").leftPush("tycoding");
        redisTemplate.boundListOps("namelist2").leftPush("涂陌");
    }

    //显示左压栈的集合
    @Test
    public void getLeftValueByList(){
        List name2 = redisTemplate.boundListOps("namelist2").range(0, 10);
        System.out.println(name2);
    }

    //根据索引查询集合中的元素
    @Test
    public void searchByIndex(){
        Object namelist = redisTemplate.boundListOps("namelist").index(1);
        System.out.println(namelist);
    }

    @Test
    public void find(){
        BoundListOperations namelist = redisTemplate.boundListOps("namelist");
        System.out.println(namelist);
    }

    //===================Hash类型操作================
    //插入值
    @Test
    public void setValueByHash(){
        redisTemplate.boundHashOps("namehash").put("a","tycoding");
        redisTemplate.boundHashOps("namehash").put("b","涂陌");
    }

    //提取所有的KEY
    @Test
    public void getKeysByHash(){
        Set namehash = redisTemplate.boundHashOps("namehash").keys();
        System.out.println(namehash);
    }

    //提取所有的值
    @Test
    public void getValuesByHash(){
        List namehash = redisTemplate.boundHashOps("namehash").values();
        System.out.println(namehash);
    }

    //根据key取值
    @Test
    public void getValueByHash(){
        Object o = redisTemplate.boundHashOps("namehash").get("a");
        System.out.println(o);
    }

    //根据key移除值
    @Test
    public void deleteValueByHash(){
        redisTemplate.boundHashOps("namehash").delete("a");
    }

    @Test
    public void getValueTest(){
        Set goods = redisTemplate.boundHashOps("goods").keys();
        System.out.println(goods);
//        redisTemplate.boundHashOps("goods").delete("all");
    }

}
