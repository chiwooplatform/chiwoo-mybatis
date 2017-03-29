/**
 * @author seonbo.shim
 * @version 1.0, 2017-03-28
 * @copyright BESPIN GLOBAL
 */
package org.chiwooplatform.sample.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chiwooplatform.mybatis.mapper.BaseMapper;
import org.chiwooplatform.sample.model.Code;

@Mapper
public interface CodeMapper
    extends BaseMapper<Code> {
}
