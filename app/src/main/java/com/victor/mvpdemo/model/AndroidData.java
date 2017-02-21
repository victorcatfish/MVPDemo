package com.victor.mvpdemo.model;

import java.util.ArrayList;

/** 具体数据javabean
 * Created by Victor on 2017/2/14.
 */

public class AndroidData {

    public boolean error;
    public ArrayList<Field> results;

    public class Field {
        public String _id;       //: "58a2780e421aa901f7902c7d",
        public String createdAt; //: "2017-02-14T11:22:54.87Z",
        public String desc;      //: "Material Design 风格的 About 页面",
        public ArrayList<String> images; //: [
        public String publishedAt; //: "2017-02-14T11:42:37.303Z",
        public String source;     //"chrome",
        public String type;       //: "Android",
        public String url;        //: "https://github.com/jrvansuita/MaterialAbout",
        public boolean used;       //: true,
        public String who;        //: "代码家"
    }

}
