package cha.pao.fan.blogs.extentions;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by pfchang
 * on 2020/2/28.
 */
//@DisplayNameGeneration(JunitTest.IndicativeSentences.class)
public class CsvTests {
    TestService service=new TestService();

    @CsvTest({ "点评列表, 1,2,1",
            "点评标签, 2,34,2",
            "我的点评, 3,23,3" })
    void demo(String[] param) {
        assertEquals(service.exc(new TestEntity(param[1],param[2])),param[3]);
    }


    class TestEntity{
        private String a;
        private String b;


        public TestEntity(String a, String b) {
            this.a = a;
            this.b = b;

        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

    }

    class TestService{
        public String exc(TestEntity entity){
            return entity.getA();
        }
    }


}
