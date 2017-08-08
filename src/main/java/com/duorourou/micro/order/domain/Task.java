package com.duorourou.micro.order.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Task {

    private String taskId;
    private String taskName;
    private String userId;
    private String userName;
    private Date start;
    private Date end;
    private boolean repeat;


}
