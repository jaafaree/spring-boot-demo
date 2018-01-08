package com.github.jaafar.l.common.VO;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/2 13:57
 */
public class TableRestResponse<T> extends BaseResponse {
    TableData<T> data;

    public TableRestResponse(long total, List<T> rows) {
        this.data = new TableData<T>(total, rows);
    }

    public TableRestResponse() {
        this.data = new TableData<T>();
    }

    TableRestResponse<T> total(int total) {
        this.data.setTotal(total);
        return this;
    }

    TableRestResponse<T> total(List<T> rows) {
        this.data.setRows(rows);
        return this;
    }

    public TableData<T> getData() {
        return data;
    }

    public void setData(TableData<T> data) {
        this.data = data;
    }

    class TableData<T> {
        long total;
        List<T> rows;

        public TableData(long total, List<T> rows) {
            this.total = total;
            this.rows = rows;
        }

        public TableData() {
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public List<T> getRows() {
            return rows;
        }

        public void setRows(List<T> rows) {
            this.rows = rows;
        }
    }

}
