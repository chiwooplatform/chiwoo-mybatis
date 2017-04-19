package org.chiwooplatform.sample.model;

import java.io.Serializable;
import java.sql.Date;

import org.apache.ibatis.type.Alias;

@SuppressWarnings("serial")
@Alias("code")
public class Code
    implements Serializable {

    private Integer cdId;

    private Integer upCdId;

    private String cdVal;

    private String cdName;

    private String cdEngName;

    private String cdPath;

    private String descr;

    private Boolean useYn;

    private Integer ordno;

    private Integer registerId;

    private Date regDtm;

    private Integer modifierId;

    private Date updDtm;

    private String extVal;

    private String extVal2;

    private Boolean existsYn;

    /**
     * @return the cdId
     */
    public Integer getCdId() {
        return cdId;
    }

    /**
     * @param cdId the cdId to set
     */
    public void setCdId( Integer cdId ) {
        this.cdId = cdId;
    }

    /**
     * @return the upCdId
     */
    public Integer getUpCdId() {
        return upCdId;
    }

    /**
     * @param upCdId the upCdId to set
     */
    public void setUpCdId( Integer upCdId ) {
        this.upCdId = upCdId;
    }

    /**
     * @return the cdVal
     */
    public String getCdVal() {
        return cdVal;
    }

    /**
     * @param cdVal the cdVal to set
     */
    public void setCdVal( String cdVal ) {
        this.cdVal = cdVal;
    }

    /**
     * @return the cdName
     */
    public String getCdName() {
        return cdName;
    }

    /**
     * @param cdName the cdName to set
     */
    public void setCdName( String cdName ) {
        this.cdName = cdName;
    }

    /**
     * @return the cdEngName
     */
    public String getCdEngName() {
        return cdEngName;
    }

    /**
     * @param cdEngName the cdEngName to set
     */
    public void setCdEngName( String cdEngName ) {
        this.cdEngName = cdEngName;
    }

    /**
     * @return the cdPath
     */
    public String getCdPath() {
        return cdPath;
    }

    /**
     * @param cdPath the cdPath to set
     */
    public void setCdPath( String cdPath ) {
        this.cdPath = cdPath;
    }

    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }

    /**
     * @param descr the descr to set
     */
    public void setDescr( String descr ) {
        this.descr = descr;
    }

    /**
     * @return the useYn
     */
    public Boolean getUseYn() {
        return useYn;
    }

    /**
     * @param useYn the useYn to set
     */
    public void setUseYn( Boolean useYn ) {
        this.useYn = useYn;
    }

    /**
     * @return the ordno
     */
    public Integer getOrdno() {
        return ordno;
    }

    /**
     * @param ordno the ordno to set
     */
    public void setOrdno( Integer ordno ) {
        this.ordno = ordno;
    }

    /**
     * @return the registerId
     */
    public Integer getRegisterId() {
        return registerId;
    }

    /**
     * @param registerId the registerId to set
     */
    public void setRegisterId( Integer registerId ) {
        this.registerId = registerId;
    }

    /**
     * @return the regDtm
     */
    public Date getRegDtm() {
        return regDtm;
    }

    /**
     * @param regDtm the regDtm to set
     */
    public void setRegDtm( Date regDtm ) {
        this.regDtm = regDtm;
    }

    /**
     * @return the modifierId
     */
    public Integer getModifierId() {
        return modifierId;
    }

    /**
     * @param modifierId the modifierId to set
     */
    public void setModifierId( Integer modifierId ) {
        this.modifierId = modifierId;
    }

    /**
     * @return the updDtm
     */
    public Date getUpdDtm() {
        return updDtm;
    }

    /**
     * @param updDtm the updDtm to set
     */
    public void setUpdDtm( Date updDtm ) {
        this.updDtm = updDtm;
    }

    /**
     * @return the extVal
     */
    public String getExtVal() {
        return extVal;
    }

    /**
     * @param extVal the extVal to set
     */
    public void setExtVal( String extVal ) {
        this.extVal = extVal;
    }

    /**
     * @return the extVal2
     */
    public String getExtVal2() {
        return extVal2;
    }

    /**
     * @param extVal2 the extVal2 to set
     */
    public void setExtVal2( String extVal2 ) {
        this.extVal2 = extVal2;
    }

    /**
     * @return the existsYn
     */
    public Boolean getExistsYn() {
        return existsYn;
    }

    /**
     * @param existsYn the existsYn to set
     */
    public void setExistsYn( Boolean existsYn ) {
        this.existsYn = existsYn;
    }
}
