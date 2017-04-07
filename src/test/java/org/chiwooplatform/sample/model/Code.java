package org.chiwooplatform.sample.model;

import java.io.Serializable;
import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("code")
public class Code
    implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cd_id;

    private Integer up_cd_id;

    private String cd_val;

    private String cd_name;

    private String cd_eng_name;

    private String cd_path;

    private String cd_descr;

    private Boolean use_yn;

    private Integer ordno;

    private Integer register_id;

    private Date reg_dtm;

    private Integer modifier_id;

    private Date upd_dtm;

    private Boolean open_yn;

    private String standard_spec;

    private String ext_val;

    private String ext_val2;

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Code [cd_id=" + cd_id + ", up_cd_id=" + up_cd_id + ", cd_val=" + cd_val + ", cd_name=" + cd_name
            + ", cd_eng_name=" + cd_eng_name + ", cd_path=" + cd_path + ", cd_descr=" + cd_descr + ", use_yn=" + use_yn
            + ", ordno=" + ordno + ", register_id=" + register_id + ", reg_dtm=" + reg_dtm + ", modifier_id="
            + modifier_id + ", upd_dtm=" + upd_dtm + ", open_yn=" + open_yn + ", standard_spec=" + standard_spec
            + ", ext_val=" + ext_val + ", ext_val2=" + ext_val2 + "]";
    }

    /**
     * @return the cd_id
     */
    public Integer getCd_id() {
        return cd_id;
    }

    /**
     * @param cd_id the cd_id to set
     */
    public void setCd_id( Integer cd_id ) {
        this.cd_id = cd_id;
    }

    /**
     * @return the up_cd_id
     */
    public Integer getUp_cd_id() {
        return up_cd_id;
    }

    /**
     * @param up_cd_id the up_cd_id to set
     */
    public void setUp_cd_id( Integer up_cd_id ) {
        this.up_cd_id = up_cd_id;
    }

    /**
     * @return the cd_val
     */
    public String getCd_val() {
        return cd_val;
    }

    /**
     * @param cd_val the cd_val to set
     */
    public void setCd_val( String cd_val ) {
        this.cd_val = cd_val;
    }

    /**
     * @return the cd_name
     */
    public String getCd_name() {
        return cd_name;
    }

    /**
     * @param cd_name the cd_name to set
     */
    public void setCd_name( String cd_name ) {
        this.cd_name = cd_name;
    }

    /**
     * @return the cd_eng_name
     */
    public String getCd_eng_name() {
        return cd_eng_name;
    }

    /**
     * @param cd_eng_name the cd_eng_name to set
     */
    public void setCd_eng_name( String cd_eng_name ) {
        this.cd_eng_name = cd_eng_name;
    }

    /**
     * @return the cd_path
     */
    public String getCd_path() {
        return cd_path;
    }

    /**
     * @param cd_path the cd_path to set
     */
    public void setCd_path( String cd_path ) {
        this.cd_path = cd_path;
    }

    /**
     * @return the cd_descr
     */
    public String getCd_descr() {
        return cd_descr;
    }

    /**
     * @param cd_descr the cd_descr to set
     */
    public void setCd_descr( String cd_descr ) {
        this.cd_descr = cd_descr;
    }

    /**
     * @return the use_yn
     */
    public Boolean getUse_yn() {
        return use_yn;
    }

    /**
     * @param use_yn the use_yn to set
     */
    public void setUse_yn( Boolean use_yn ) {
        this.use_yn = use_yn;
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
     * @return the register_id
     */
    public Integer getRegister_id() {
        return register_id;
    }

    /**
     * @param register_id the register_id to set
     */
    public void setRegister_id( Integer register_id ) {
        this.register_id = register_id;
    }

    /**
     * @return the reg_dtm
     */
    public Date getReg_dtm() {
        return reg_dtm;
    }

    /**
     * @param reg_dtm the reg_dtm to set
     */
    public void setReg_dtm( Date reg_dtm ) {
        this.reg_dtm = reg_dtm;
    }

    /**
     * @return the modifier_id
     */
    public Integer getModifier_id() {
        return modifier_id;
    }

    /**
     * @param modifier_id the modifier_id to set
     */
    public void setModifier_id( Integer modifier_id ) {
        this.modifier_id = modifier_id;
    }

    /**
     * @return the upd_dtm
     */
    public Date getUpd_dtm() {
        return upd_dtm;
    }

    /**
     * @param upd_dtm the upd_dtm to set
     */
    public void setUpd_dtm( Date upd_dtm ) {
        this.upd_dtm = upd_dtm;
    }

    /**
     * @return the open_yn
     */
    public Boolean getOpen_yn() {
        return open_yn;
    }

    /**
     * @param open_yn the open_yn to set
     */
    public void setOpen_yn( Boolean open_yn ) {
        this.open_yn = open_yn;
    }

    /**
     * @return the standard_spec
     */
    public String getStandard_spec() {
        return standard_spec;
    }

    /**
     * @param standard_spec the standard_spec to set
     */
    public void setStandard_spec( String standard_spec ) {
        this.standard_spec = standard_spec;
    }

    /**
     * @return the ext_val
     */
    public String getExt_val() {
        return ext_val;
    }

    /**
     * @param ext_val the ext_val to set
     */
    public void setExt_val( String ext_val ) {
        this.ext_val = ext_val;
    }

    /**
     * @return the ext_val2
     */
    public String getExt_val2() {
        return ext_val2;
    }

    /**
     * @param ext_val2 the ext_val2 to set
     */
    public void setExt_val2( String ext_val2 ) {
        this.ext_val2 = ext_val2;
    }
}
