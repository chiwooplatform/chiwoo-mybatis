package org.chiwooplatform.sample.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.RowBounds;
import org.chiwooplatform.mybatis.AbstractMybatisTests;
import org.chiwooplatform.mybatis.mapper.BaseMapper;
import org.chiwooplatform.sample.model.Code;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class CodeMapperTests
    extends AbstractMybatisTests<Code> {

    //    @ClassRule
    //    public static OutputCapture out = new OutputCapture();
    @Autowired
    private CodeMapper mapper;

    private Integer cd_id = -9999; // 키

    protected Code model() {
        Integer cd_id = this.cd_id;
        Integer up_cd_id = -9999;
        String cd_val = "TEST";
        String cd_name = "코드 명";
        String cd_eng_name = "코드 영어 명";
        String cd_descr = "코드 설명";
        Boolean use_yn = true;
        Integer ordno = 0;
        Integer register_id = userid();
        Boolean open_yn = false;
        String standard_spec = "테스트 기본 사항";
        String ext_val = "테스트 추가";
        String ext_val2 = "테스트 추가";
        Code model = new Code();
        model.setCd_id( cd_id );
        model.setUp_cd_id( up_cd_id );
        model.setCd_val( cd_val );
        model.setCd_name( cd_name );
        model.setCd_eng_name( cd_eng_name );
        model.setCd_descr( cd_descr );
        model.setUse_yn( use_yn );
        model.setOrdno( ++ordno );
        model.setRegister_id( register_id );
        model.setModifier_id( register_id );
        model.setOpen_yn( open_yn );
        model.setStandard_spec( standard_spec );
        model.setExt_val( ext_val );
        model.setExt_val2( ext_val2 );
        return model;
    }

    @Test
    public void mapperIsNotNullTest() {
        System.out.printf( "mapper: %s", mapper );
        assertNotNull( mapper );
    }

    @Rollback(true)
    @Test
    public void test_CRUD() {
        Code model = model();
        mapper.saveOrUpdate( model );
        Code result = mapper.get( cd_id );
        logger.info( "result: {}", result );
        HashMap<String, Object> param = new HashMap<>();
        List<ResultMap> results = mapper.queryForMap( param, BaseMapper.rowBounds( param ) );
        logger.info( "results: {}", results );
        mapper.remove( model );
        Code result2 = mapper.get( cd_id );
        logger.info( "result2: {}", result2 );
    }

    @Test
    public void test_listRowBounds()
        throws Exception {
        Code model = model();
        mapper.saveOrUpdate( model );
        Map<String, Object> param = new HashMap<>();
        param.put( "cd_id", this.cd_id );
        param.put( BaseMapper.ROWBOUNDS_OFFSET, 1 );
        param.put( BaseMapper.ROWBOUNDS_SCALE, 10 );
        RowBounds bounds = BaseMapper.rowBounds( param );
        List<Code> list = mapper.query( param, bounds );
        assertNotNull( list );
        logger.info( "size: {}", list.size() );
    }

    @Test
    public void test_listTotalCount()
        throws Exception {
        Code model = model();
        mapper.saveOrUpdate( model );
        Map<String, Object> param = new HashMap<>();
        param.put( BaseMapper.TOTAL_ROWCOUNT, 1 );
        param.put( "cd_id", this.cd_id );
        param.put( BaseMapper.ROWBOUNDS_OFFSET, 0 );
        param.put( BaseMapper.ROWBOUNDS_SCALE, 50 );
        List<Code> list = mapper.query( param, BaseMapper.rowBounds( param ) );
        assertNotNull( list );
        logger.info( "BaseMapper.TOTAL_ROWCOUNT: {}", param.get( BaseMapper.TOTAL_ROWCOUNT ) );
        logger.info( "size: {}", list.size() );
    }
}
