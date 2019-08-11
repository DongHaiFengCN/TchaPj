package com.application.tchapj.my.bean;

import java.io.Serializable;

/**
 * Created by 安卓开发 on 2018/7/30.
 */

public class PostDamuInfo implements Serializable {

    private String method;     //接口名
    private String level;      //接口信息
    private String code;       //接口返回结果
    private String description;//接口返回信息

    private PostDamuInfoResult data;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PostDamuInfoResult getData() {
        return data;
    }

    public void setData(PostDamuInfoResult data) {
        this.data = data;
    }

    public static class PostDamuInfoResult {

        private PostDamuInfOpinions opinions;

        public PostDamuInfOpinions getOpinions() {
            return opinions;
        }

        public void setOpinions(PostDamuInfOpinions opinions) {
            this.opinions = opinions;
        }

        public static class PostDamuInfOpinions {

            private String id;
            private String memberId;
            private String opinions;  // 内容
            private long createTime;
            private String nickName; // 名字

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

            public String getOpinions() {
                return opinions;
            }

            public void setOpinions(String opinions) {
                this.opinions = opinions;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }
        }

    }

}
