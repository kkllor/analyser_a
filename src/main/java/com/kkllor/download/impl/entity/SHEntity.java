package com.kkllor.download.impl.entity;

import java.util.List;

/**
 * @author kkllor
 * @date 2020/4/20 下午6:07
 */
public class SHEntity {


    /**
     * beginDate : 2015-01-01
     * endDate : 2016-01-01
     * isNew : null
     * isPagination : true
     * jsonCallBack : jsonpCallback52715
     * keyWord :
     * pageHelp : {"beginPage":1,"cacheSize":1,"data":[{"BULLETIN_HEADING":"定期报告","BULLETIN_TYPE":"年报","BULLETIN_YEAR":"2014","INDEXCLASS":null,"PLAN_Date":null,"PLAN_Year":null,"ROWNUM":null,"ROWNUM_":null,"SECURITY_CODE":"600258","SSEDATE":"2015-03-31","SSEDate":null,"SSETime":null,"SSETimeStr":null,"TITLE":"首旅酒店年报","URL":"/disclosure/listedinfo/announcement/c/2015-03-30/600258_2014_n.pdf","author":null,"book_Name":null,"bulletinHeading":null,"bulletinType":null,"bulletin_No":null,"bulletin_Type":null,"bulletin_Year":null,"category_A":null,"category_B":null,"category_C":null,"category_D":null,"chapter_No":null,"companyAbbr":null,"dispatch_Organ":null,"file_Serial":null,"finish_Time":null,"initial_Date":null,"isChangeFlag":null,"journal_Issue":null,"journal_Name":null,"journal_Section":null,"journal_Year":null,"keyWord":null,"key_Word":null,"language":null,"lemma_CN":null,"lemma_EN":null,"publishing_Comp":null,"question":null,"question_Class":null,"read_Status":null,"save_Time":null,"section":null,"security_Code":null,"source":null,"spareVolEnd":null,"title":null,"title_ETC":null,"title_PY":null,"unit_Code":null,"unit_Type":null},{"BULLETIN_HEADING":"定期报告","BULLETIN_TYPE":"年报摘要","BULLETIN_YEAR":"2014","INDEXCLASS":null,"PLAN_Date":null,"PLAN_Year":null,"ROWNUM":null,"ROWNUM_":null,"SECURITY_CODE":"600258","SSEDATE":"2015-03-31","SSEDate":null,"SSETime":null,"SSETimeStr":null,"TITLE":"首旅酒店年报摘要","URL":"/disclosure/listedinfo/announcement/c/2015-03-30/600258_2014_nzy.pdf","author":null,"book_Name":null,"bulletinHeading":null,"bulletinType":null,"bulletin_No":null,"bulletin_Type":null,"bulletin_Year":null,"category_A":null,"category_B":null,"category_C":null,"category_D":null,"chapter_No":null,"companyAbbr":null,"dispatch_Organ":null,"file_Serial":null,"finish_Time":null,"initial_Date":null,"isChangeFlag":null,"journal_Issue":null,"journal_Name":null,"journal_Section":null,"journal_Year":null,"keyWord":null,"key_Word":null,"language":null,"lemma_CN":null,"lemma_EN":null,"publishing_Comp":null,"question":null,"question_Class":null,"read_Status":null,"save_Time":null,"section":null,"security_Code":null,"source":null,"spareVolEnd":null,"title":null,"title_ETC":null,"title_PY":null,"unit_Code":null,"unit_Type":null}],"endDate":null,"endPage":5,"objectResult":null,"pageCount":1,"pageNo":1,"pageSize":25,"searchDate":null,"sort":null,"startDate":null,"total":2}
     * productId : 600258
     * reportType : YEARLY
     * reportType2 : DQBG
     * result : [{"BULLETIN_HEADING":"定期报告","BULLETIN_TYPE":"年报","BULLETIN_YEAR":"2014","INDEXCLASS":null,"PLAN_Date":null,"PLAN_Year":null,"ROWNUM":null,"ROWNUM_":null,"SECURITY_CODE":"600258","SSEDATE":"2015-03-31","SSEDate":null,"SSETime":null,"SSETimeStr":null,"TITLE":"首旅酒店年报","URL":"/disclosure/listedinfo/announcement/c/2015-03-30/600258_2014_n.pdf","author":null,"book_Name":null,"bulletinHeading":null,"bulletinType":null,"bulletin_No":null,"bulletin_Type":null,"bulletin_Year":null,"category_A":null,"category_B":null,"category_C":null,"category_D":null,"chapter_No":null,"companyAbbr":null,"dispatch_Organ":null,"file_Serial":null,"finish_Time":null,"initial_Date":null,"isChangeFlag":null,"journal_Issue":null,"journal_Name":null,"journal_Section":null,"journal_Year":null,"keyWord":null,"key_Word":null,"language":null,"lemma_CN":null,"lemma_EN":null,"publishing_Comp":null,"question":null,"question_Class":null,"read_Status":null,"save_Time":null,"section":null,"security_Code":null,"source":null,"spareVolEnd":null,"title":null,"title_ETC":null,"title_PY":null,"unit_Code":null,"unit_Type":null},{"BULLETIN_HEADING":"定期报告","BULLETIN_TYPE":"年报摘要","BULLETIN_YEAR":"2014","INDEXCLASS":null,"PLAN_Date":null,"PLAN_Year":null,"ROWNUM":null,"ROWNUM_":null,"SECURITY_CODE":"600258","SSEDATE":"2015-03-31","SSEDate":null,"SSETime":null,"SSETimeStr":null,"TITLE":"首旅酒店年报摘要","URL":"/disclosure/listedinfo/announcement/c/2015-03-30/600258_2014_nzy.pdf","author":null,"book_Name":null,"bulletinHeading":null,"bulletinType":null,"bulletin_No":null,"bulletin_Type":null,"bulletin_Year":null,"category_A":null,"category_B":null,"category_C":null,"category_D":null,"chapter_No":null,"companyAbbr":null,"dispatch_Organ":null,"file_Serial":null,"finish_Time":null,"initial_Date":null,"isChangeFlag":null,"journal_Issue":null,"journal_Name":null,"journal_Section":null,"journal_Year":null,"keyWord":null,"key_Word":null,"language":null,"lemma_CN":null,"lemma_EN":null,"publishing_Comp":null,"question":null,"question_Class":null,"read_Status":null,"save_Time":null,"section":null,"security_Code":null,"source":null,"spareVolEnd":null,"title":null,"title_ETC":null,"title_PY":null,"unit_Code":null,"unit_Type":null}]
     * secCodes : null
     * securityType : 0101
     */

    private String beginDate;
    private String endDate;
    private Object isNew;
    private String isPagination;
    private String jsonCallBack;
    private String keyWord;
    private PageHelpBean pageHelp;
    private String productId;
    private String reportType;
    private String reportType2;
    private Object secCodes;
    private String securityType;
    private List<ResultBean> result;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Object getIsNew() {
        return isNew;
    }

    public void setIsNew(Object isNew) {
        this.isNew = isNew;
    }

    public String getIsPagination() {
        return isPagination;
    }

    public void setIsPagination(String isPagination) {
        this.isPagination = isPagination;
    }

    public String getJsonCallBack() {
        return jsonCallBack;
    }

    public void setJsonCallBack(String jsonCallBack) {
        this.jsonCallBack = jsonCallBack;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public PageHelpBean getPageHelp() {
        return pageHelp;
    }

    public void setPageHelp(PageHelpBean pageHelp) {
        this.pageHelp = pageHelp;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportType2() {
        return reportType2;
    }

    public void setReportType2(String reportType2) {
        this.reportType2 = reportType2;
    }

    public Object getSecCodes() {
        return secCodes;
    }

    public void setSecCodes(Object secCodes) {
        this.secCodes = secCodes;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class PageHelpBean {
        /**
         * beginPage : 1
         * cacheSize : 1
         * data : [{"BULLETIN_HEADING":"定期报告","BULLETIN_TYPE":"年报","BULLETIN_YEAR":"2014","INDEXCLASS":null,"PLAN_Date":null,"PLAN_Year":null,"ROWNUM":null,"ROWNUM_":null,"SECURITY_CODE":"600258","SSEDATE":"2015-03-31","SSEDate":null,"SSETime":null,"SSETimeStr":null,"TITLE":"首旅酒店年报","URL":"/disclosure/listedinfo/announcement/c/2015-03-30/600258_2014_n.pdf","author":null,"book_Name":null,"bulletinHeading":null,"bulletinType":null,"bulletin_No":null,"bulletin_Type":null,"bulletin_Year":null,"category_A":null,"category_B":null,"category_C":null,"category_D":null,"chapter_No":null,"companyAbbr":null,"dispatch_Organ":null,"file_Serial":null,"finish_Time":null,"initial_Date":null,"isChangeFlag":null,"journal_Issue":null,"journal_Name":null,"journal_Section":null,"journal_Year":null,"keyWord":null,"key_Word":null,"language":null,"lemma_CN":null,"lemma_EN":null,"publishing_Comp":null,"question":null,"question_Class":null,"read_Status":null,"save_Time":null,"section":null,"security_Code":null,"source":null,"spareVolEnd":null,"title":null,"title_ETC":null,"title_PY":null,"unit_Code":null,"unit_Type":null},{"BULLETIN_HEADING":"定期报告","BULLETIN_TYPE":"年报摘要","BULLETIN_YEAR":"2014","INDEXCLASS":null,"PLAN_Date":null,"PLAN_Year":null,"ROWNUM":null,"ROWNUM_":null,"SECURITY_CODE":"600258","SSEDATE":"2015-03-31","SSEDate":null,"SSETime":null,"SSETimeStr":null,"TITLE":"首旅酒店年报摘要","URL":"/disclosure/listedinfo/announcement/c/2015-03-30/600258_2014_nzy.pdf","author":null,"book_Name":null,"bulletinHeading":null,"bulletinType":null,"bulletin_No":null,"bulletin_Type":null,"bulletin_Year":null,"category_A":null,"category_B":null,"category_C":null,"category_D":null,"chapter_No":null,"companyAbbr":null,"dispatch_Organ":null,"file_Serial":null,"finish_Time":null,"initial_Date":null,"isChangeFlag":null,"journal_Issue":null,"journal_Name":null,"journal_Section":null,"journal_Year":null,"keyWord":null,"key_Word":null,"language":null,"lemma_CN":null,"lemma_EN":null,"publishing_Comp":null,"question":null,"question_Class":null,"read_Status":null,"save_Time":null,"section":null,"security_Code":null,"source":null,"spareVolEnd":null,"title":null,"title_ETC":null,"title_PY":null,"unit_Code":null,"unit_Type":null}]
         * endDate : null
         * endPage : 5
         * objectResult : null
         * pageCount : 1
         * pageNo : 1
         * pageSize : 25
         * searchDate : null
         * sort : null
         * startDate : null
         * total : 2
         */

        private int beginPage;
        private int cacheSize;
        private Object endDate;
        private int endPage;
        private Object objectResult;
        private int pageCount;
        private int pageNo;
        private int pageSize;
        private Object searchDate;
        private Object sort;
        private Object startDate;
        private int total;
        private List<DataBean> data;

        public int getBeginPage() {
            return beginPage;
        }

        public void setBeginPage(int beginPage) {
            this.beginPage = beginPage;
        }

        public int getCacheSize() {
            return cacheSize;
        }

        public void setCacheSize(int cacheSize) {
            this.cacheSize = cacheSize;
        }

        public Object getEndDate() {
            return endDate;
        }

        public void setEndDate(Object endDate) {
            this.endDate = endDate;
        }

        public int getEndPage() {
            return endPage;
        }

        public void setEndPage(int endPage) {
            this.endPage = endPage;
        }

        public Object getObjectResult() {
            return objectResult;
        }

        public void setObjectResult(Object objectResult) {
            this.objectResult = objectResult;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public Object getSearchDate() {
            return searchDate;
        }

        public void setSearchDate(Object searchDate) {
            this.searchDate = searchDate;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public Object getStartDate() {
            return startDate;
        }

        public void setStartDate(Object startDate) {
            this.startDate = startDate;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * BULLETIN_HEADING : 定期报告
             * BULLETIN_TYPE : 年报
             * BULLETIN_YEAR : 2014
             * INDEXCLASS : null
             * PLAN_Date : null
             * PLAN_Year : null
             * ROWNUM : null
             * ROWNUM_ : null
             * SECURITY_CODE : 600258
             * SSEDATE : 2015-03-31
             * SSEDate : null
             * SSETime : null
             * SSETimeStr : null
             * TITLE : 首旅酒店年报
             * URL : /disclosure/listedinfo/announcement/c/2015-03-30/600258_2014_n.pdf
             * author : null
             * book_Name : null
             * bulletinHeading : null
             * bulletinType : null
             * bulletin_No : null
             * bulletin_Type : null
             * bulletin_Year : null
             * category_A : null
             * category_B : null
             * category_C : null
             * category_D : null
             * chapter_No : null
             * companyAbbr : null
             * dispatch_Organ : null
             * file_Serial : null
             * finish_Time : null
             * initial_Date : null
             * isChangeFlag : null
             * journal_Issue : null
             * journal_Name : null
             * journal_Section : null
             * journal_Year : null
             * keyWord : null
             * key_Word : null
             * language : null
             * lemma_CN : null
             * lemma_EN : null
             * publishing_Comp : null
             * question : null
             * question_Class : null
             * read_Status : null
             * save_Time : null
             * section : null
             * security_Code : null
             * source : null
             * spareVolEnd : null
             * title : null
             * title_ETC : null
             * title_PY : null
             * unit_Code : null
             * unit_Type : null
             */

            private String BULLETIN_HEADING;
            private String BULLETIN_TYPE;
            private String BULLETIN_YEAR;
            private Object INDEXCLASS;
            private Object PLAN_Date;
            private Object PLAN_Year;
            private Object ROWNUM;
            private Object ROWNUM_;
            private String SECURITY_CODE;
            private String SSEDATE;
            private Object SSEDate;
            private Object SSETime;
            private Object SSETimeStr;
            private String TITLE;
            private String URL;
            private Object author;
            private Object book_Name;
            private Object bulletinHeading;
            private Object bulletinType;
            private Object bulletin_No;
            private Object bulletin_Type;
            private Object bulletin_Year;
            private Object category_A;
            private Object category_B;
            private Object category_C;
            private Object category_D;
            private Object chapter_No;
            private Object companyAbbr;
            private Object dispatch_Organ;
            private Object file_Serial;
            private Object finish_Time;
            private Object initial_Date;
            private Object isChangeFlag;
            private Object journal_Issue;
            private Object journal_Name;
            private Object journal_Section;
            private Object journal_Year;
            private Object keyWord;
            private Object key_Word;
            private Object language;
            private Object lemma_CN;
            private Object lemma_EN;
            private Object publishing_Comp;
            private Object question;
            private Object question_Class;
            private Object read_Status;
            private Object save_Time;
            private Object section;
            private Object security_Code;
            private Object source;
            private Object spareVolEnd;
            private Object title;
            private Object title_ETC;
            private Object title_PY;
            private Object unit_Code;
            private Object unit_Type;

            public String getBULLETIN_HEADING() {
                return BULLETIN_HEADING;
            }

            public void setBULLETIN_HEADING(String BULLETIN_HEADING) {
                this.BULLETIN_HEADING = BULLETIN_HEADING;
            }

            public String getBULLETIN_TYPE() {
                return BULLETIN_TYPE;
            }

            public void setBULLETIN_TYPE(String BULLETIN_TYPE) {
                this.BULLETIN_TYPE = BULLETIN_TYPE;
            }

            public String getBULLETIN_YEAR() {
                return BULLETIN_YEAR;
            }

            public void setBULLETIN_YEAR(String BULLETIN_YEAR) {
                this.BULLETIN_YEAR = BULLETIN_YEAR;
            }

            public Object getINDEXCLASS() {
                return INDEXCLASS;
            }

            public void setINDEXCLASS(Object INDEXCLASS) {
                this.INDEXCLASS = INDEXCLASS;
            }

            public Object getPLAN_Date() {
                return PLAN_Date;
            }

            public void setPLAN_Date(Object PLAN_Date) {
                this.PLAN_Date = PLAN_Date;
            }

            public Object getPLAN_Year() {
                return PLAN_Year;
            }

            public void setPLAN_Year(Object PLAN_Year) {
                this.PLAN_Year = PLAN_Year;
            }

            public Object getROWNUM() {
                return ROWNUM;
            }

            public void setROWNUM(Object ROWNUM) {
                this.ROWNUM = ROWNUM;
            }

            public Object getROWNUM_() {
                return ROWNUM_;
            }

            public void setROWNUM_(Object ROWNUM_) {
                this.ROWNUM_ = ROWNUM_;
            }

            public String getSECURITY_CODE() {
                return SECURITY_CODE;
            }

            public void setSECURITY_CODE(String SECURITY_CODE) {
                this.SECURITY_CODE = SECURITY_CODE;
            }

            public String getSSEDATE() {
                return SSEDATE;
            }

            public void setSSEDATE(String SSEDATE) {
                this.SSEDATE = SSEDATE;
            }

            public Object getSSEDate() {
                return SSEDate;
            }

            public void setSSEDate(Object SSEDate) {
                this.SSEDate = SSEDate;
            }

            public Object getSSETime() {
                return SSETime;
            }

            public void setSSETime(Object SSETime) {
                this.SSETime = SSETime;
            }

            public Object getSSETimeStr() {
                return SSETimeStr;
            }

            public void setSSETimeStr(Object SSETimeStr) {
                this.SSETimeStr = SSETimeStr;
            }

            public String getTITLE() {
                return TITLE;
            }

            public void setTITLE(String TITLE) {
                this.TITLE = TITLE;
            }

            public String getURL() {
                return URL;
            }

            public void setURL(String URL) {
                this.URL = URL;
            }

            public Object getAuthor() {
                return author;
            }

            public void setAuthor(Object author) {
                this.author = author;
            }

            public Object getBook_Name() {
                return book_Name;
            }

            public void setBook_Name(Object book_Name) {
                this.book_Name = book_Name;
            }

            public Object getBulletinHeading() {
                return bulletinHeading;
            }

            public void setBulletinHeading(Object bulletinHeading) {
                this.bulletinHeading = bulletinHeading;
            }

            public Object getBulletinType() {
                return bulletinType;
            }

            public void setBulletinType(Object bulletinType) {
                this.bulletinType = bulletinType;
            }

            public Object getBulletin_No() {
                return bulletin_No;
            }

            public void setBulletin_No(Object bulletin_No) {
                this.bulletin_No = bulletin_No;
            }

            public Object getBulletin_Type() {
                return bulletin_Type;
            }

            public void setBulletin_Type(Object bulletin_Type) {
                this.bulletin_Type = bulletin_Type;
            }

            public Object getBulletin_Year() {
                return bulletin_Year;
            }

            public void setBulletin_Year(Object bulletin_Year) {
                this.bulletin_Year = bulletin_Year;
            }

            public Object getCategory_A() {
                return category_A;
            }

            public void setCategory_A(Object category_A) {
                this.category_A = category_A;
            }

            public Object getCategory_B() {
                return category_B;
            }

            public void setCategory_B(Object category_B) {
                this.category_B = category_B;
            }

            public Object getCategory_C() {
                return category_C;
            }

            public void setCategory_C(Object category_C) {
                this.category_C = category_C;
            }

            public Object getCategory_D() {
                return category_D;
            }

            public void setCategory_D(Object category_D) {
                this.category_D = category_D;
            }

            public Object getChapter_No() {
                return chapter_No;
            }

            public void setChapter_No(Object chapter_No) {
                this.chapter_No = chapter_No;
            }

            public Object getCompanyAbbr() {
                return companyAbbr;
            }

            public void setCompanyAbbr(Object companyAbbr) {
                this.companyAbbr = companyAbbr;
            }

            public Object getDispatch_Organ() {
                return dispatch_Organ;
            }

            public void setDispatch_Organ(Object dispatch_Organ) {
                this.dispatch_Organ = dispatch_Organ;
            }

            public Object getFile_Serial() {
                return file_Serial;
            }

            public void setFile_Serial(Object file_Serial) {
                this.file_Serial = file_Serial;
            }

            public Object getFinish_Time() {
                return finish_Time;
            }

            public void setFinish_Time(Object finish_Time) {
                this.finish_Time = finish_Time;
            }

            public Object getInitial_Date() {
                return initial_Date;
            }

            public void setInitial_Date(Object initial_Date) {
                this.initial_Date = initial_Date;
            }

            public Object getIsChangeFlag() {
                return isChangeFlag;
            }

            public void setIsChangeFlag(Object isChangeFlag) {
                this.isChangeFlag = isChangeFlag;
            }

            public Object getJournal_Issue() {
                return journal_Issue;
            }

            public void setJournal_Issue(Object journal_Issue) {
                this.journal_Issue = journal_Issue;
            }

            public Object getJournal_Name() {
                return journal_Name;
            }

            public void setJournal_Name(Object journal_Name) {
                this.journal_Name = journal_Name;
            }

            public Object getJournal_Section() {
                return journal_Section;
            }

            public void setJournal_Section(Object journal_Section) {
                this.journal_Section = journal_Section;
            }

            public Object getJournal_Year() {
                return journal_Year;
            }

            public void setJournal_Year(Object journal_Year) {
                this.journal_Year = journal_Year;
            }

            public Object getKeyWord() {
                return keyWord;
            }

            public void setKeyWord(Object keyWord) {
                this.keyWord = keyWord;
            }

            public Object getKey_Word() {
                return key_Word;
            }

            public void setKey_Word(Object key_Word) {
                this.key_Word = key_Word;
            }

            public Object getLanguage() {
                return language;
            }

            public void setLanguage(Object language) {
                this.language = language;
            }

            public Object getLemma_CN() {
                return lemma_CN;
            }

            public void setLemma_CN(Object lemma_CN) {
                this.lemma_CN = lemma_CN;
            }

            public Object getLemma_EN() {
                return lemma_EN;
            }

            public void setLemma_EN(Object lemma_EN) {
                this.lemma_EN = lemma_EN;
            }

            public Object getPublishing_Comp() {
                return publishing_Comp;
            }

            public void setPublishing_Comp(Object publishing_Comp) {
                this.publishing_Comp = publishing_Comp;
            }

            public Object getQuestion() {
                return question;
            }

            public void setQuestion(Object question) {
                this.question = question;
            }

            public Object getQuestion_Class() {
                return question_Class;
            }

            public void setQuestion_Class(Object question_Class) {
                this.question_Class = question_Class;
            }

            public Object getRead_Status() {
                return read_Status;
            }

            public void setRead_Status(Object read_Status) {
                this.read_Status = read_Status;
            }

            public Object getSave_Time() {
                return save_Time;
            }

            public void setSave_Time(Object save_Time) {
                this.save_Time = save_Time;
            }

            public Object getSection() {
                return section;
            }

            public void setSection(Object section) {
                this.section = section;
            }

            public Object getSecurity_Code() {
                return security_Code;
            }

            public void setSecurity_Code(Object security_Code) {
                this.security_Code = security_Code;
            }

            public Object getSource() {
                return source;
            }

            public void setSource(Object source) {
                this.source = source;
            }

            public Object getSpareVolEnd() {
                return spareVolEnd;
            }

            public void setSpareVolEnd(Object spareVolEnd) {
                this.spareVolEnd = spareVolEnd;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getTitle_ETC() {
                return title_ETC;
            }

            public void setTitle_ETC(Object title_ETC) {
                this.title_ETC = title_ETC;
            }

            public Object getTitle_PY() {
                return title_PY;
            }

            public void setTitle_PY(Object title_PY) {
                this.title_PY = title_PY;
            }

            public Object getUnit_Code() {
                return unit_Code;
            }

            public void setUnit_Code(Object unit_Code) {
                this.unit_Code = unit_Code;
            }

            public Object getUnit_Type() {
                return unit_Type;
            }

            public void setUnit_Type(Object unit_Type) {
                this.unit_Type = unit_Type;
            }
        }
    }

    public static class ResultBean {
        /**
         * BULLETIN_HEADING : 定期报告
         * BULLETIN_TYPE : 年报
         * BULLETIN_YEAR : 2014
         * INDEXCLASS : null
         * PLAN_Date : null
         * PLAN_Year : null
         * ROWNUM : null
         * ROWNUM_ : null
         * SECURITY_CODE : 600258
         * SSEDATE : 2015-03-31
         * SSEDate : null
         * SSETime : null
         * SSETimeStr : null
         * TITLE : 首旅酒店年报
         * URL : /disclosure/listedinfo/announcement/c/2015-03-30/600258_2014_n.pdf
         * author : null
         * book_Name : null
         * bulletinHeading : null
         * bulletinType : null
         * bulletin_No : null
         * bulletin_Type : null
         * bulletin_Year : null
         * category_A : null
         * category_B : null
         * category_C : null
         * category_D : null
         * chapter_No : null
         * companyAbbr : null
         * dispatch_Organ : null
         * file_Serial : null
         * finish_Time : null
         * initial_Date : null
         * isChangeFlag : null
         * journal_Issue : null
         * journal_Name : null
         * journal_Section : null
         * journal_Year : null
         * keyWord : null
         * key_Word : null
         * language : null
         * lemma_CN : null
         * lemma_EN : null
         * publishing_Comp : null
         * question : null
         * question_Class : null
         * read_Status : null
         * save_Time : null
         * section : null
         * security_Code : null
         * source : null
         * spareVolEnd : null
         * title : null
         * title_ETC : null
         * title_PY : null
         * unit_Code : null
         * unit_Type : null
         */

        private String BULLETIN_HEADING;
        private String BULLETIN_TYPE;
        private String BULLETIN_YEAR;
        private Object INDEXCLASS;
        private Object PLAN_Date;
        private Object PLAN_Year;
        private Object ROWNUM;
        private Object ROWNUM_;
        private String SECURITY_CODE;
        private String SSEDATE;
        private Object SSEDate;
        private Object SSETime;
        private Object SSETimeStr;
        private String TITLE;
        private String URL;
        private Object author;
        private Object book_Name;
        private Object bulletinHeading;
        private Object bulletinType;
        private Object bulletin_No;
        private Object bulletin_Type;
        private Object bulletin_Year;
        private Object category_A;
        private Object category_B;
        private Object category_C;
        private Object category_D;
        private Object chapter_No;
        private Object companyAbbr;
        private Object dispatch_Organ;
        private Object file_Serial;
        private Object finish_Time;
        private Object initial_Date;
        private Object isChangeFlag;
        private Object journal_Issue;
        private Object journal_Name;
        private Object journal_Section;
        private Object journal_Year;
        private Object keyWord;
        private Object key_Word;
        private Object language;
        private Object lemma_CN;
        private Object lemma_EN;
        private Object publishing_Comp;
        private Object question;
        private Object question_Class;
        private Object read_Status;
        private Object save_Time;
        private Object section;
        private Object security_Code;
        private Object source;
        private Object spareVolEnd;
        private Object title;
        private Object title_ETC;
        private Object title_PY;
        private Object unit_Code;
        private Object unit_Type;

        public String getBULLETIN_HEADING() {
            return BULLETIN_HEADING;
        }

        public void setBULLETIN_HEADING(String BULLETIN_HEADING) {
            this.BULLETIN_HEADING = BULLETIN_HEADING;
        }

        public String getBULLETIN_TYPE() {
            return BULLETIN_TYPE;
        }

        public void setBULLETIN_TYPE(String BULLETIN_TYPE) {
            this.BULLETIN_TYPE = BULLETIN_TYPE;
        }

        public String getBULLETIN_YEAR() {
            return BULLETIN_YEAR;
        }

        public void setBULLETIN_YEAR(String BULLETIN_YEAR) {
            this.BULLETIN_YEAR = BULLETIN_YEAR;
        }

        public Object getINDEXCLASS() {
            return INDEXCLASS;
        }

        public void setINDEXCLASS(Object INDEXCLASS) {
            this.INDEXCLASS = INDEXCLASS;
        }

        public Object getPLAN_Date() {
            return PLAN_Date;
        }

        public void setPLAN_Date(Object PLAN_Date) {
            this.PLAN_Date = PLAN_Date;
        }

        public Object getPLAN_Year() {
            return PLAN_Year;
        }

        public void setPLAN_Year(Object PLAN_Year) {
            this.PLAN_Year = PLAN_Year;
        }

        public Object getROWNUM() {
            return ROWNUM;
        }

        public void setROWNUM(Object ROWNUM) {
            this.ROWNUM = ROWNUM;
        }

        public Object getROWNUM_() {
            return ROWNUM_;
        }

        public void setROWNUM_(Object ROWNUM_) {
            this.ROWNUM_ = ROWNUM_;
        }

        public String getSECURITY_CODE() {
            return SECURITY_CODE;
        }

        public void setSECURITY_CODE(String SECURITY_CODE) {
            this.SECURITY_CODE = SECURITY_CODE;
        }

        public String getSSEDATE() {
            return SSEDATE;
        }

        public void setSSEDATE(String SSEDATE) {
            this.SSEDATE = SSEDATE;
        }

        public Object getSSEDate() {
            return SSEDate;
        }

        public void setSSEDate(Object SSEDate) {
            this.SSEDate = SSEDate;
        }

        public Object getSSETime() {
            return SSETime;
        }

        public void setSSETime(Object SSETime) {
            this.SSETime = SSETime;
        }

        public Object getSSETimeStr() {
            return SSETimeStr;
        }

        public void setSSETimeStr(Object SSETimeStr) {
            this.SSETimeStr = SSETimeStr;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public Object getAuthor() {
            return author;
        }

        public void setAuthor(Object author) {
            this.author = author;
        }

        public Object getBook_Name() {
            return book_Name;
        }

        public void setBook_Name(Object book_Name) {
            this.book_Name = book_Name;
        }

        public Object getBulletinHeading() {
            return bulletinHeading;
        }

        public void setBulletinHeading(Object bulletinHeading) {
            this.bulletinHeading = bulletinHeading;
        }

        public Object getBulletinType() {
            return bulletinType;
        }

        public void setBulletinType(Object bulletinType) {
            this.bulletinType = bulletinType;
        }

        public Object getBulletin_No() {
            return bulletin_No;
        }

        public void setBulletin_No(Object bulletin_No) {
            this.bulletin_No = bulletin_No;
        }

        public Object getBulletin_Type() {
            return bulletin_Type;
        }

        public void setBulletin_Type(Object bulletin_Type) {
            this.bulletin_Type = bulletin_Type;
        }

        public Object getBulletin_Year() {
            return bulletin_Year;
        }

        public void setBulletin_Year(Object bulletin_Year) {
            this.bulletin_Year = bulletin_Year;
        }

        public Object getCategory_A() {
            return category_A;
        }

        public void setCategory_A(Object category_A) {
            this.category_A = category_A;
        }

        public Object getCategory_B() {
            return category_B;
        }

        public void setCategory_B(Object category_B) {
            this.category_B = category_B;
        }

        public Object getCategory_C() {
            return category_C;
        }

        public void setCategory_C(Object category_C) {
            this.category_C = category_C;
        }

        public Object getCategory_D() {
            return category_D;
        }

        public void setCategory_D(Object category_D) {
            this.category_D = category_D;
        }

        public Object getChapter_No() {
            return chapter_No;
        }

        public void setChapter_No(Object chapter_No) {
            this.chapter_No = chapter_No;
        }

        public Object getCompanyAbbr() {
            return companyAbbr;
        }

        public void setCompanyAbbr(Object companyAbbr) {
            this.companyAbbr = companyAbbr;
        }

        public Object getDispatch_Organ() {
            return dispatch_Organ;
        }

        public void setDispatch_Organ(Object dispatch_Organ) {
            this.dispatch_Organ = dispatch_Organ;
        }

        public Object getFile_Serial() {
            return file_Serial;
        }

        public void setFile_Serial(Object file_Serial) {
            this.file_Serial = file_Serial;
        }

        public Object getFinish_Time() {
            return finish_Time;
        }

        public void setFinish_Time(Object finish_Time) {
            this.finish_Time = finish_Time;
        }

        public Object getInitial_Date() {
            return initial_Date;
        }

        public void setInitial_Date(Object initial_Date) {
            this.initial_Date = initial_Date;
        }

        public Object getIsChangeFlag() {
            return isChangeFlag;
        }

        public void setIsChangeFlag(Object isChangeFlag) {
            this.isChangeFlag = isChangeFlag;
        }

        public Object getJournal_Issue() {
            return journal_Issue;
        }

        public void setJournal_Issue(Object journal_Issue) {
            this.journal_Issue = journal_Issue;
        }

        public Object getJournal_Name() {
            return journal_Name;
        }

        public void setJournal_Name(Object journal_Name) {
            this.journal_Name = journal_Name;
        }

        public Object getJournal_Section() {
            return journal_Section;
        }

        public void setJournal_Section(Object journal_Section) {
            this.journal_Section = journal_Section;
        }

        public Object getJournal_Year() {
            return journal_Year;
        }

        public void setJournal_Year(Object journal_Year) {
            this.journal_Year = journal_Year;
        }

        public Object getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(Object keyWord) {
            this.keyWord = keyWord;
        }

        public Object getKey_Word() {
            return key_Word;
        }

        public void setKey_Word(Object key_Word) {
            this.key_Word = key_Word;
        }

        public Object getLanguage() {
            return language;
        }

        public void setLanguage(Object language) {
            this.language = language;
        }

        public Object getLemma_CN() {
            return lemma_CN;
        }

        public void setLemma_CN(Object lemma_CN) {
            this.lemma_CN = lemma_CN;
        }

        public Object getLemma_EN() {
            return lemma_EN;
        }

        public void setLemma_EN(Object lemma_EN) {
            this.lemma_EN = lemma_EN;
        }

        public Object getPublishing_Comp() {
            return publishing_Comp;
        }

        public void setPublishing_Comp(Object publishing_Comp) {
            this.publishing_Comp = publishing_Comp;
        }

        public Object getQuestion() {
            return question;
        }

        public void setQuestion(Object question) {
            this.question = question;
        }

        public Object getQuestion_Class() {
            return question_Class;
        }

        public void setQuestion_Class(Object question_Class) {
            this.question_Class = question_Class;
        }

        public Object getRead_Status() {
            return read_Status;
        }

        public void setRead_Status(Object read_Status) {
            this.read_Status = read_Status;
        }

        public Object getSave_Time() {
            return save_Time;
        }

        public void setSave_Time(Object save_Time) {
            this.save_Time = save_Time;
        }

        public Object getSection() {
            return section;
        }

        public void setSection(Object section) {
            this.section = section;
        }

        public Object getSecurity_Code() {
            return security_Code;
        }

        public void setSecurity_Code(Object security_Code) {
            this.security_Code = security_Code;
        }

        public Object getSource() {
            return source;
        }

        public void setSource(Object source) {
            this.source = source;
        }

        public Object getSpareVolEnd() {
            return spareVolEnd;
        }

        public void setSpareVolEnd(Object spareVolEnd) {
            this.spareVolEnd = spareVolEnd;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getTitle_ETC() {
            return title_ETC;
        }

        public void setTitle_ETC(Object title_ETC) {
            this.title_ETC = title_ETC;
        }

        public Object getTitle_PY() {
            return title_PY;
        }

        public void setTitle_PY(Object title_PY) {
            this.title_PY = title_PY;
        }

        public Object getUnit_Code() {
            return unit_Code;
        }

        public void setUnit_Code(Object unit_Code) {
            this.unit_Code = unit_Code;
        }

        public Object getUnit_Type() {
            return unit_Type;
        }

        public void setUnit_Type(Object unit_Type) {
            this.unit_Type = unit_Type;
        }
    }
}
