package com.application.tchapj.my.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018\9\7 0007.
 */

public class MoneyInfoListBean implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果

    private MoneyInfoListBeanResult data;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MoneyInfoListBeanResult getData() {
        return data;
    }

    public void setData(MoneyInfoListBeanResult data) {
        this.data = data;
    }

    public static class MoneyInfoListBeanResult {

        private List<MoneyInfoTaskLogs> taskLogs; // 收益

        private List<MoneyInfoTasks> tasks;       // 花费

        public List<AppWallet> getAppWallets() {
            return appWallet;
        }

        public void setAppWallets(List<AppWallet> appWallet) {
            this.appWallet = appWallet;
        }

        private List<AppWallet> appWallet;

        public List<With> getWiths() {
            return with;
        }

        public void setWiths(List<With> with) {
            this.with = with;
        }

        private List<With> with;

        public List<MoneyInfoTaskLogs> getTaskLogs() {
            return taskLogs;
        }

        public void setTaskLogs(List<MoneyInfoTaskLogs> taskLogs) {
            this.taskLogs = taskLogs;
        }

        public List<MoneyInfoTasks> getTasks() {
            return tasks;
        }

        public void setTasks(List<MoneyInfoTasks> tasks) {
            this.tasks = tasks;
        }

        public class MoneyInfoTaskLogs {

            private String name;     // 活动名称
            private String taskType; // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
            private double salary;      // 收益

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTaskType() {
                return taskType;
            }

            public void setTaskType(String taskType) {
                this.taskType = taskType;
            }

            public double getSalary() {
                return salary;
            }

            public void setSalary(double salary) {
                this.salary = salary;
            }
        }

        public class MoneyInfoTasks {

            private String name;     // 活动名称
            private String taskType; // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
            private double money;      // 付款金额

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTaskType() {
                return taskType;
            }

            public void setTaskType(String taskType) {
                this.taskType = taskType;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }
        }

        /*        appWallet	createTime	string	充值时间
                money	double	充值金额
                appWalletLogId	string	交易流水帐号
                with	createTime	string	申请时间
                money	String	提现金额
                status	string	状态
                accountNumber	string	到账账户*/
        public class AppWallet {

            /**
             * appWalletLogId : 2019080222001483710573063687
             * memberId : d330ba9341704ee1bbf35cde559b21ef
             * money : 0.01
             * createTime : 1564713415000
             * index : 0
             * rowCountPerPage : 0
             */

            private String appWalletLogId;
            private String memberId;
            private double money;
            private long createTime;
            private int index;
            private int rowCountPerPage;

            public String getAppWalletLogId() {
                return appWalletLogId;
            }

            public void setAppWalletLogId(String appWalletLogId) {
                this.appWalletLogId = appWalletLogId;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getRowCountPerPage() {
                return rowCountPerPage;
            }

            public void setRowCountPerPage(int rowCountPerPage) {
                this.rowCountPerPage = rowCountPerPage;
            }
        }

/*        with	createTime	string	申请时间
        money	String	提现金额
        status	string	状态
        accountNumber	string	到账账户*/

        public class With{

            /**
             * id : c9b449f3559647d1b7145090cd28cf7f
             * memberId : d330ba9341704ee1bbf35cde559b21ef
             * money : 60
             * name : 发条语音
             * accountNumber : 2556655
             * bankOutlets : vv给花花
             * createTime : 1565256514000
             * type : 1
             * status : 0
             * index : 0
             * rowCountPerPage : 0
             */

            private String id;
            private String memberId;
            private String money;
            private String name;
            private String accountNumber;
            private String bankOutlets;
            private long createTime;
            private String type;
            private String status;
            private int index;
            private int rowCountPerPage;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMemberId() {
                return memberId;
            }

            public void setMemberId(String memberId) {
                this.memberId = memberId;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAccountNumber() {
                return accountNumber;
            }

            public void setAccountNumber(String accountNumber) {
                this.accountNumber = accountNumber;
            }

            public String getBankOutlets() {
                return bankOutlets;
            }

            public void setBankOutlets(String bankOutlets) {
                this.bankOutlets = bankOutlets;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getRowCountPerPage() {
                return rowCountPerPage;
            }

            public void setRowCountPerPage(int rowCountPerPage) {
                this.rowCountPerPage = rowCountPerPage;
            }
        }

    }

}
