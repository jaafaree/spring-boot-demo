package com.github.jaafar.l.common.VO;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/2 10:54
 */
public class ObjectRestResponse<T> extends BaseResponse {
    T data;
    boolean rel;

    public boolean isRel(){
        return rel;
    }

    public void setRel(boolean rel){
        this.rel = rel;
    }

    public ObjectRestResponse rel(boolean rel) {
        this.rel = rel;
        return this;
    }

    public ObjectRestResponse data(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
