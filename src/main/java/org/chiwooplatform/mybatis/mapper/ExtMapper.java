package org.chiwooplatform.mybatis.mapper;

/**
 * <pre>
 * Mybatis mapper 에 구현해 주세요.
 * </pre>
 * 
 * @param <T> Model class against dbms table
 * 
 * @author aider
 */
public interface ExtMapper<T>
    extends BaseMapper<T> {

    /**
     * 신규 데이타 생성 또는 기존 데이타 갱신 처리
     * 
     * @param model 신규 데이타 생성에 필요한 모델 데이타
     * @throws RuntimeException while access dbms
     */
    void saveOrUpdate( T model )
        throws RuntimeException;

    /**
     * 활성화(사용함) 상태 처리
     * 
     * @param id 조회 조건에 사용될 Key 객체
     * @throws RuntimeException while access dbms
     */
    void enable( Object id )
        throws RuntimeException;

    /**
     * 비활성화(사용 안함) 상태 처리
     * 
     * @param id 조회 조건에 사용될 Key 객체
     * @throws RuntimeException while access dbms
     */
    void disable( Object id )
        throws RuntimeException;
}