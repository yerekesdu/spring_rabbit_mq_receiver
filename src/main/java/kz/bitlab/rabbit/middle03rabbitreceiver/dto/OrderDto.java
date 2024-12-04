package kz.bitlab.rabbit.middle03rabbitreceiver.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {

    private String orderName;
    private String orderNo;
    private Long sentTime;
    private ArrayList<String> users;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getSentTime() {
        return sentTime;
    }

    public void setSentTime(Long sentTime) {
        this.sentTime = sentTime;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderName='" + orderName + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", sentTime=" + sentTime +
                ", users=" + users +
                '}';
    }
}
