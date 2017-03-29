package org.chiwooplatform.mybatis.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.RowBounds;

/**
 * <pre>
 * Mybatis mapper 에 구현해 주세요.
 * </pre>
 * 
 * @author aider
 */
@Mapper
public interface BaseMapper<T> {

    String TOTAL_ROWCOUNT = "TOTAL_CNT";

    /**
     * 신규 데이타 생성 처리
     * 
     * @param model 신규 데이타 생성에 필요한 모델 데이타
     * @throws RuntimeException
     */
    void add( T model )
        throws RuntimeException;

    /**
     * 데이타 갱신 처리
     * 
     * @param model 데이타 갱신에 필요한 모델 데이타
     * @throws RuntimeException
     */
    void modify( T model )
        throws RuntimeException;

    /**
     * 데이타 삭제 처리
     * 
     * @param id 데이타 삭제 조건에 사용될 Key 객체
     * @throws RuntimeException
     */
    void remove( Serializable id )
        throws RuntimeException;

    /**
     * 모델 T 에 관한 Key 에 해당하는 데이타 조회
     * 
     * @param id 조회 조건에 사용될 Key 객체
     * @return 데이타 조회
     * @throws RuntimeException
     */
    T get( Serializable id )
        throws RuntimeException;

    /**
     * 모델 T 에 관한 페이징 목록 조회
     * 
     * @param 조회 조건에 사용 될 파라미터 Map
     * @param bounds 페이징 처리시 offset 과 limit 을 설정
     * @return 모델 T 에 관한 목록 데이타셋
     * @throws RuntimeException
     */
    List<T> query( Map<String, ?> map, RowBounds bounds );

    /**
     * 조회 조건에 해당하는 Map 데이타 조회
     * 
     * @param id 조회 조건에 사용될 Key 객체
     * @return Map 데이타
     * @throws RuntimeException
     */
    ResultMap getForMap( Object id )
        throws RuntimeException;

    /**
     * 목록형 Map 데이타에 관한 페이징 목록 조회
     * 
     * @param map 조회 조건에 사용 될 파라미터 Map
     * @param bounds 페이징 처리시 offset 과 limit 을 설정
     * @return 목록형 Map 데이타
     */
    List<ResultMap> queryForMap( Map<String, ?> map, RowBounds bounds );
}