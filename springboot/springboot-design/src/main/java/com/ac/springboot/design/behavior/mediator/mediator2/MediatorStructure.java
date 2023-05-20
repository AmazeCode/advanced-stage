package com.ac.springboot.design.behavior.mediator.mediator2;

/**
 * 具体的中介者-中介机构
 * @Author: zhangyadong
 * @Date: 2022/12/25 15:45
 */
public class MediatorStructure extends Mediator {

    // 中介 知晓，房租出租人和承租人的信息
    private HouseOwner houseOwner;// 房主

    private Tenant tenant; // 租房者

    public HouseOwner getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(HouseOwner houseOwner) {
        this.houseOwner = houseOwner;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @Override
    public void contact(String message, Person person) {
        if (person == houseOwner) {
            // 如果是房主，则租房者获得信息
            tenant.getMessage(message);
        }else{
            // 如果是租房者,则房主获得信息
            houseOwner.getMessage(message);
        }
    }
}
