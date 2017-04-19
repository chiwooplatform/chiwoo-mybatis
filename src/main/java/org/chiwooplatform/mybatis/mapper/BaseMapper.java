package org.chiwooplatform.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import org.chiwooplatform.mybatis.model.ResultMap;

/**
 * <pre>
 * Mybatis mapper 에 구현해 주세요.
 * </pre>
 * 
 * @param <T> Model class against dbms table
 * 
 * @author aider
 */
@Mapper
public interface BaseMapper<T> {

    String ROWBOUNDS_SCALE = "scale";

    String ROWBOUNDS_OFFSET = "page";

    String TOTAL_ROWCOUNT = "TOTAL_CNT";

    /**
     * 신규 데이타 생성 처리
     * 
     * @param model 신규 데이타 생성에 필요한 모델 데이타
     * @throws RuntimeException while access dbms
     */
    void add( T model )
        throws RuntimeException;

    /**
     * 데이타 갱신 처리
     * 
     * @param model 데이타 갱신에 필요한 모델 데이타
     * @throws RuntimeException while access dbms
     */
    void modify( T model )
        throws RuntimeException;

    /**
     * 데이타 삭제 처리
     * 
     * @param id 데이타 삭제 조건에 사용될 Key 객체
     * @throws RuntimeException while access dbms
     */
    void remove( Object id )
        throws RuntimeException;

    /**
     * 모델 T 에 관한 Key 에 해당하는 데이타 조회
     * 
     * @param id 조회 조건에 사용될 Key 객체
     * @return 데이타 조회
     * @throws RuntimeException while access dbms
     */
    T get( Object id )
        throws RuntimeException;

    List<T> query( Map<String, ?> map );

    /**
     * 모델 T 에 관한 페이징 목록 조회
     * 
     * @param map 조회 조건에 사용 될 파라미터 Map
     * @param bounds 페이징 처리시 offset 과 limit 을 설정
     * @return 모델 T 에 관한 목록 데이타셋
     */
    List<T> query( Map<String, ?> map, RowBounds bounds );

    /**
     * 조회 조건에 해당하는 Map 데이타 조회
     * 
     * @param id 조회 조건에 사용될 Key 객체
     * @return Map 데이타
     * @throws RuntimeException while access dbms
     */
    ResultMap getForMap( Object id )
        throws RuntimeException;

    List<ResultMap> queryForMap( Map<String, ?> map );

    /**
     * 목록형 Map 데이타에 관한 페이징 목록 조회
     * 
     * @param map 조회 조건에 사용 될 파라미터 Map
     * @param bounds 페이징 처리시 offset 과 limit 을 설정
     * @return 목록형 Map 데이타
     */
    List<ResultMap> queryForMap( Map<String, ?> map, RowBounds bounds );

    /**
     * 페이징 쿼리를 위한 RowBounds 객체를 반환 한다.
     *
     * <pre>
     * RDBMS 의 페이징 쿼리 정보를 처리 하기 위해 Map&#60;K, V&#62; 파라미터 중
     * scale, offset 속성과 값을 처리하여 RowBounds 객체를 반환 한다.
     * </pre>
     *
     * @param params Map&#60;K, V&#62; 페이징 쿼리 정보를 위한 파라미터 식별 및 값 바인딩을 위한 파라미터 맵
     * @return RDBMS 의 페이징 쿼리 정보를 관리하는 RowBounds 객체
     */
    static public RowBounds rowBounds( Map<String, Object> params ) {
        if ( params == null ) {
            return null;
        }
        int offset = 0;
        int scale = 0;
        if ( params.containsKey( ROWBOUNDS_SCALE ) ) {
            Object val = params.get( ROWBOUNDS_SCALE );
            if ( val instanceof Integer ) {
                scale = (Integer) val;
            } else {
                scale = Integer.parseInt( (String) val );
            }
        }
        if ( params.containsKey( ROWBOUNDS_OFFSET ) ) {
            Object val = params.get( ROWBOUNDS_OFFSET );
            if ( val instanceof Integer ) {
                offset = (Integer) val;
            } else {
                offset = Integer.parseInt( (String) val );
            }
        }
        if ( scale > 0 ) {
            return new RowBounds( offset, scale );
        }
        return null;
    }
}